package edu.xd.ridelab.foundationplatform.sparkstreaming

import edu.xd.ridelab.foundationplatform.mysql.vo.MacMachineHeartbeat
import edu.xd.ridelab.foundationplatform.mysqlAlone.SqlUtils
import edu.xd.ridelab.foundationplatform.properties.PropertyFileReader
import edu.xd.ridelab.foundationplatform.redisUtils.RedisManager
import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 接收心跳数据，更新redis表中对应机具心跳时间，如果redis表中没有对应机具心跳信息，则插入一条机具上线日志到mysql，
  * @author cwz
  * @date 2018/05/19
  * @since 0.0.0
  */
object Heartbeat {
  def main(args: Array[String]): Unit = {

//    val ssc = StreamingContext.getOrCreate("/app/checkpoint/hb", functionToCreateContext)
    val ssc = StreamingContext.getOrCreate("./hb", functionToCreateContext)

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
      .setAppName("Heartbeat")
    val batchInterval = conf.getTimeAsSeconds("spark.batch.interval", "1")
    val ssc = new StreamingContext(conf, Seconds(batchInterval))
//    ssc.checkpoint("/app/checkpoint/hb")
    ssc.checkpoint("./hb")

    // 读取Kafka配置
    val prop = PropertyFileReader.readProperties("kafka-hb.properties")
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

    // 根据记录进行Redis和Mysql机具日志表更新
    sources.foreachRDD(rdd => {
      if(!rdd.isEmpty()) {
        rdd.foreach { case (key, value) => {
          val heartbeat = value.asInstanceOf[MacMachineHeartbeat]
          if(! RedisManager.isHearbeatKeyExists(heartbeat.getMacMachineNo)){
            SqlUtils.insertMachineStatusLog(heartbeat)
          }
          RedisManager.setHearbeat(heartbeat.getMacMachineNo, heartbeat.getHeartbeatTime.toEpochMilli.toString)
        }}
      }
    })

    ssc
  }

}
