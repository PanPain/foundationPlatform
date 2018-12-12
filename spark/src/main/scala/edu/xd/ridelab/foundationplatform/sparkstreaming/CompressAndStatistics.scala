package edu.xd.ridelab.foundationplatform.sparkstreaming


import edu.xd.ridelab.foundationplatform.record.{TransferRecord, VIRecord}
import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming._
import Utils._
import edu.xd.ridelab.foundationplatform.operation.{OperationUtil, TableInsert}
import edu.xd.ridelab.foundationplatform.properties.PropertyFileReader
import edu.xd.ridelab.foundationplatform.redisUtils.RedisManager


/**
  * 对原始数据进行压缩，压缩后的记录存入HBase，压缩后的每台机具每种类型的记录数量增量更新到Redis
  * @author cwz
  * @date 2018/5/8
  * @since 0.0.0
  */
object CompressAndStatistics {

  // 压缩时间间隔
  var compressInterval = 10
  // 根据离开时间和进入时间的间隔，判断是否还要继续压缩，避免一直压缩，没有记录
  var popInterval = 20

  def main(args: Array[String]): Unit = {

    val ssc = StreamingContext.getOrCreate("/app/checkpoint/cas", functionToCreateContext)
//    val ssc = StreamingContext.getOrCreate("./cas", functionToCreateContext)
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
      .setAppName("CompressAndStatistics")

    val batchInterval = conf.getTimeAsSeconds("spark.batch.interval", "10")
    val ssc = new StreamingContext(conf, Seconds(batchInterval))

    ssc.checkpoint("/app/checkpoint/cas")
//    ssc.checkpoint("./cas")

    // 读取kafka配置
    val prop = PropertyFileReader.readProperties("kafka-cas.properties")
    val topics = prop.getProperty("topics").split(",").toSet
    var kafkaParams = Map[String, String](
      "key.serializer" -> "org.apache.kafka.common.serialization.StringSerializer",
      "value.serializer" -> "edu.xd.ridelab.receiver.serialize.SimpleObjectSerializer",
      "auto.offset.reset" -> "smallest"
    )

    kafkaParams += ("bootstrap.servers" -> prop.getProperty("bootstrap.servers"))
    kafkaParams += ("group.id" -> prop.getProperty("group.id"))

    // 构建mapWithState的初始状态
    val initialRDD = ssc.sparkContext.parallelize(List[((String, String), TransferRecord)]())

    // 获取Kafka输入流
    val sources = KafkaUtils.createDirectStream[String, AnyRef, StringDecoder, ObjectDecoder](ssc, kafkaParams, topics)

    // 根据json串进行转换
    val records = sources.mapPartitions(messageIter => {
      messageIter.map{case (macType, record) => messageToRecord(macType, record)}
    })


    // 按照记录类型，设备mac进行分组
    val macRecords = records.map{ case (macType, record) => ((macType, record.getMac), record)}.groupByKey()


    // 进行合并操作
    macRecords.mapWithState(StateSpec.function(mappingFunc).initialState(initialRDD).timeout(Milliseconds(compressInterval * 1000)))
      .print(1)

    ssc
  }

  val mappingFunc = (key:(String, String), records: Option[Iterable[TransferRecord]], state: State[TransferRecord]) => {
    // 判断是否超过合并时间间隔，此mac都没有更新过，超过则插入HBase,更新Redis计数
    if(state.isTimingOut()){
      state.getOption().foreach(record => {
        val redisKey = getRedisKey(record, key._1)
        OperationUtil.getTableInsert.insertToHBase(record)
        RedisManager.incBy(redisKey._1, redisKey._2, 1)
      })
    }else {
      // 判断是否超过最长保留时间间隔，超过则插入HBase,更新Redis计数
      if (state.exists && java.time.Duration.between(state.get.getLastTime, state.get.getFirstTime).toMillis / 1000 >= popInterval) {
        val redisKey = getRedisKey(state.get, key._1)
        OperationUtil.getTableInsert.insertToHBase(state.get)
        RedisManager.incBy(redisKey._1, redisKey._2, 1)
      } else {
        // 根据记录进入时间进行排序
        val sortedRecords = (state.getOption().iterator ++ records.get.iterator).toList.sortBy(_.getFirstTime)(localDateTimeComparator)
        val lastRecord = sortedRecords.reduce((last, current) => {
          val interval = java.time.Duration.between(last.getLastTime, current.getFirstTime).toMillis / 1000

          // 判断相邻两条记录的时间是否不属于同一机具采集到或者时间间隔大于合并时间间隔，如果满足，插入HBase,更新Redis计数
          if (!last.getDevmac.equals(current.getDevmac) || interval > compressInterval) {
            val redisKey = getRedisKey(last, key._1)
            OperationUtil.getTableInsert.insertToHBase(last)
            RedisManager.incBy(redisKey._1, redisKey._2, 1)
            current
          }else {
            // 如果记录是虚拟身份记录类型
            if(key._1.equals("VI")){
              val lastVI = last.asInstanceOf[VIRecord]
              val currentVI = last.asInstanceOf[VIRecord]
              // 判断相邻两条记录是否属于同一类型虚拟身份，是否属于同一账号，不符合插入HBase,更新Redis计数
              if(lastVI.getProtocol.equals(currentVI.getProtocol) && lastVI.getAccount.equals(currentVI.getAccount)){
                // 更新上条记录的离开时间为当前记录的离开时间，返回上条记录
                last.setLastTime(current.getLastTime)
                last.addAndGetCount()
                last
              }else{
                val redisKey = getRedisKey(last, key._1)
                OperationUtil.getTableInsert.insertToHBase(last)
                RedisManager.incBy(redisKey._1, redisKey._2, 1)
                current
              }
            }else {
              // 更新上条记录的离开时间为当前记录的离开时间，返回上条记录
              last.setLastTime(current.getLastTime)
              last.addAndGetCount()
              last
            }
          }
        })
        state.update(lastRecord)
        (key, lastRecord)
      }
    }
  }
}
