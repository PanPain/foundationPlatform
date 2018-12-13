package edu.xd.ridelab.foundationplatform.sparkstreaming

import com.google.gson.Gson
import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Milliseconds, Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.broadcast.Broadcast
import Utils._
import edu.xd.ridelab.foundationplatform.mysql.vo.Rule
import edu.xd.ridelab.foundationplatform.mysqlAlone.SqlUtils
import edu.xd.ridelab.foundationplatform.operation.{OperationUtil, TableInsert}
import edu.xd.ridelab.foundationplatform.properties.PropertyFileReader
import edu.xd.ridelab.foundationplatform.record.{TransferRecord, VIRecord}
import edu.xd.ridelab.foundationplatform.redisUtils.{BroadcastWrapper, RedisManager, RulesJudger}
import scala.collection.JavaConverters._

/**
  * 对原始数据进行解析，根据报警规则对每条记录进行判断，把每台机具每种类型的记录数量增量更新到Redis
  * @author cwz
  * @date 2018/5/6
  * @since 0.0.0
  */
object AlarmAndStatistics {
  def main(args: Array[String]): Unit = {

//    val ssc = StreamingContext.getOrCreate("/app/checkpoint/aas", functionToCreateContext)
    val ssc = StreamingContext.getOrCreate("./aas", functionToCreateContext)

    ssc.start()
    ssc.awaitTermination()

  }


  /**
    * 构建处理流程
    * @return 构建了所有处理流程的SparkStreaming 上下文
    */
  def functionToCreateContext(): StreamingContext ={
    val conf = new SparkConf()
      .setIfMissing("spark.master", "local[1]")
      .setAppName("AlarmAndStatistics")

    val batchInterval = conf.getTimeAsSeconds("spark.batch.interval", "10")
    val ssc = new StreamingContext(conf, Seconds(batchInterval))

//    ssc.checkpoint("/app/checkpoint/aas")
    ssc.checkpoint("./aas")


    // 读取kafka配置
    val prop = PropertyFileReader.readProperties("kafka-aas.properties")
    val topics = prop.getProperty("topics").split(",").toSet
    var kafkaParams = Map[String, String](
      "key.serializer" -> "org.apache.kafka.common.serialization.StringSerializer",
      "value.serializer" -> "edu.xd.ridelab.receiver.serialize.SimpleObjectSerializer",
      "auto.offset.reset" -> "smallest"
    )
    kafkaParams += ("bootstrap.servers" -> prop.getProperty("bootstrap.servers"))
    kafkaParams += ("group.id" -> prop.getProperty("group.id"))


    // 获取Kafka输入流
    val sources = KafkaUtils.createDirectStream[String, AnyRef, StringDecoder, ObjectDecoder](ssc, kafkaParams, topics)

    // 根据json串进行转换
    val records = sources.mapPartitions(messageIter => {
      messageIter.map{case (macType, record) => messageToRecord(macType, record)}
    })


    // 根据记录类型，机具，日期进行分组
    val macRecords = records.map{ case (macType, record) => ((macType, record.getDevmac, getDay(record.getFirstTime)), record)}.groupByKey()

    //把每台机具每种类型的记录数量增量更新到Redis，根据报警规则筛选每条记录，发布订阅到Redis，并把每条报警记录插入到HBase
    macRecords.foreachRDD(rdd => {
      // 更新监控规则
      val broadCast = BroadcastWrapper.getInstance.updateAndGet(rdd.context)
      rdd.foreach{case ((macType, devmac, day), records) => {
        val gson = new Gson()
        val monitorRules = broadCast.value
        val redisKey = devmac + ":uncompress:" + macType

        // 如果记录类型为虚拟身份，需要转化为具体对象，针对虚拟身份类型进行统计
        if(macType.equals("VI")){
          records
            .groupBy(record => record.asInstanceOf[VIRecord].getProtocol)
            .foreach{ case(protocol, groupRecords) => {
              val viKey = redisKey + ":" + protocol
              val count = checkRulesAndCount(monitorRules, groupRecords, gson)
              RedisManager.incBy(day, viKey, count)
            }}
        }else{
          val count = checkRulesAndCount(monitorRules, records, gson)
          RedisManager.incBy(day, redisKey, count)
        }

      }}
    })
    ssc
  }

  /**
    * 针对每条记录，如果符合监控规则，发布到redis，并插入到HBase
    * @param rules 监控规则
    * @param records mac记录
    * @param gson json化工具类
    * @return 统计条数
    */
  def checkRulesAndCount(rules: List[Rule], records: Iterable[TransferRecord], gson: Gson): Integer ={
    var count = 0
    records.foreach(record => {
      rules.foreach(rule => {
        // 针对每条记录，如果符合报警规则，发布到redis，并插入到HBase
        if (RulesJudger.judge(rule, record)) {
          val alarm = recordToAlarm(rule, record)
          OperationUtil.getTableInsert.insertToHBase(alarm)
          RedisManager.publish(rule.getUserId.toString, gson.toJson(alarm))
        }
      })
      count = count + 1
    })
    count
  }

}

