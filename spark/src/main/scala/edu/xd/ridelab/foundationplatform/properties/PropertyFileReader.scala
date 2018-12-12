package edu.xd.ridelab.foundationplatform.properties

import java.io.IOException
import java.util.Properties

import org.slf4j.{Logger, LoggerFactory}

/**
  * 配置文件读取工具类
  * @author cwz
  * @date 2018/05/07
  * @since 0.0.0
  */
object PropertyFileReader {
  val logger: Logger = LoggerFactory.getLogger("PropertyFileReader")

  /**
    * 从jar包中指定配置文件读取配置
    * @param fileName 配置文件名字
    * @return 配置
    * @since 0.0.0
    */
  def readProperties(fileName: String): Properties = {
    val prop = new Properties()
    val input = PropertyFileReader.getClass.getClassLoader.getResourceAsStream(fileName)
    try{
      prop.load(input)
    } catch {
      case e: IOException => {
        logger.error("fail to read properties -> {}", e.getMessage)
        System.exit(1)
      }
    }finally {
      if(input != null) {
        input.close()
      }
    }
    prop
  }
}
