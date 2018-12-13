package edu.xd.ridelab.foundationplatform.sparkstreaming

import edu.xd.ridelab.foundationplatform.definition.MacDataType
import edu.xd.ridelab.foundationplatform.mysql.vo._
import edu.xd.ridelab.foundationplatform.mysqlAlone.SqlUtils
import edu.xd.ridelab.foundationplatform.properties.PropertyFileReader
import kafka.serializer.{DefaultDecoder, StringDecoder}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Milliseconds, Seconds, StreamingContext}

/**
  * 更新Oracle数据表中基本信息
  * @author cwz
  * @date 2018/05/10
  * @since 0.0.0
  */
object BasicUpdate {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\hadoop-common-2.2.0-bin-master\\hadoop-common-2.2.0-bin-master")

//    val ssc = StreamingContext.getOrCreate("/app/checkpoint/bu", functionToCreateContext)
    val ssc = StreamingContext.getOrCreate("./bu", functionToCreateContext)

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
      .setAppName("BasicUpdate")
    val batchInterval = conf.getTimeAsSeconds("spark.batch.interval", "10")
    val ssc = new StreamingContext(conf, Seconds(batchInterval))
    ssc.checkpoint("./bu")
//    ssc.checkpoint("/app/checkpoint/bu")

    // 读取Kafka配置
    val prop = PropertyFileReader.readProperties("kafka-bu.properties")
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

    // 根据记录进行Oracle数据库更新
    sources.foreachRDD(rdd => {
      if(!rdd.isEmpty()) {
        rdd.foreach { case (key, value) => writeToOracle(key, value) }
      }
    })

    ssc
  }

  /**
    * 根据记录类型，进行对应更新操作
    * @param key 记录类型
    * @param value 记录的序列化形式
    */
  def writeToOracle(key: String, value: AnyRef) = {
    println("-----------------------  "  + key)
    MacDataType.valueOf(key) match {
      case MacDataType.PLACE_BASIC => {
        val info: PlaceInfo = value.asInstanceOf[PlaceInfo]
        SqlUtils.writePlaceToOracle(info)
      }
      case MacDataType.RECEIVER_BASIC  => {
        val mac: MacMachine = value.asInstanceOf[MacMachine]
        SqlUtils.writeMacMachineBasicToOracle(mac)
      }
      case MacDataType.MANUFACTURER_BASIC => {
        val manufacturer: Manufacturer = value.asInstanceOf[Manufacturer]
        SqlUtils.writeManufactureToOracle(manufacturer)
      }
    }
  }

}
