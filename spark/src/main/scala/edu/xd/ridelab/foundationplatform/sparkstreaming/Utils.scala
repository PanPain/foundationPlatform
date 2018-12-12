package edu.xd.ridelab.foundationplatform.sparkstreaming

import java.time.{LocalDateTime, ZoneId}

import com.google.gson.Gson
import edu.xd.ridelab.foundationplatform.definition.MacDataType
import edu.xd.ridelab.foundationplatform.mysql.vo.Rule
import edu.xd.ridelab.foundationplatform.record._

/**
  * 工具基础类，Spark Streaming流处理进行调用
  * @author cwz
  * @date 2018/5/6
  * @since 0.0.0
  */
object Utils {

  val localDateTimeComparator = new Ordering[LocalDateTime] {
    override def compare(o1: LocalDateTime, o2: LocalDateTime): Int = o1.compareTo(o2)
  }

  /**
    * 根据mac记录的类型，把json格式的记录转换为对应类型的对象
    * @param macType mac记录类型【StationMac，ApMac，VI】
    * @param record 记录
    * @return （mac记录类型，记录对象）
    * @since 0.0.0
    */
  def messageToRecord(macType: String, record: Object): (String, TransferRecord) = {
    MacDataType.valueOf(macType) match {
      case MacDataType.STATION_MAC => ("StationMac", record.asInstanceOf[StationMacRecord])
      case MacDataType.AP_MAC => ("ApMac", record.asInstanceOf[APMacRecord])
      case MacDataType.VIRTUAL_IDENTITY => ("VI", record.asInstanceOf[VIRecord])
    }
  }

  /**
    * 根据记录的进入时间转换为对应日期
    * @param time mac记录的进入时间
    * @return 20180507
    * @since 0.0.0
    */
  def getDay(time: LocalDateTime): String = {
    val year = time.getYear
    val month = if (time.getMonthValue < 10) { "0" + time.getMonthValue } else { time.getMonthValue.toString }
    val day = if(time.getDayOfMonth < 10) { "0" + time.getDayOfMonth } else { time.getDayOfMonth.toString }

    year + month + day
  }

  /**
    * 根据记录进入时间的所属日期，记录的机具，来构造Redis键值
    * @param record mac记录，StationMac，APMac，VI的一种
    * @param macType mac数据类型
    * @return 键值格式 20180507:30123234:compress:StationMac
    * @since 0.0.0
    */
  def getRedisKey(record: TransferRecord, macType: String): (String, String) ={
    if(macType.equals("VI")){
      (getDay(record.getFirstTime), record.getDevmac + ":compress:" + macType + ":" + record.asInstanceOf[VIRecord].getProtocol)
    }else {
      (getDay(record.getFirstTime), record.getDevmac + ":compress:" + macType)
    }
  }

  /**
    * 根据监控规则和记录来生成报警记录
    * @param rule 监控规则
    * @param record 记录
    * @return 报警记录
    * @since 0.0.0
    */
  def recordToAlarm(rule: Rule, record: TransferRecord): MonitorAlarmRecord = {
    val alarm = new MonitorAlarmRecord
    alarm.setMac(record.getMac)
    alarm.setDevmac(record.getDevmac)
    alarm.setRuleId(rule.getRuleId)
    alarm.setTime(LocalDateTime.now(ZoneId.of("Asia/Shanghai")))
    alarm.setAudioPath("")
    alarm
  }

}
