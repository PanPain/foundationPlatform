package edu.xd.ridelab.foundationplatform.redisUtils


import edu.xd.ridelab.foundationplatform.mysql.vo.Rule
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.streaming.StreamingContext
import edu.xd.ridelab.foundationplatform.mysqlAlone.SqlUtils
import org.apache.spark.SparkContext
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.JavaConverters._


/**
  * 布控规则广播类，通过redis发布订阅实时更新，每批数据判断是否需要重新广播报警规则
  * @author cwz
  * @date 2018/5/6
  * @since 0.0.0
  */
class BroadcastWrapper {
  var broadcastVar: Broadcast[List[Rule]] = _
  var updateFlag: Boolean = true
  var monitorRules: List[Rule] = _

  {
    monitorRules = SqlUtils.getRules.asScala.toList
    RedisManager.subscribeRules
  }


  /**
    * 供Redis订阅者线程更新 monitorRules
    */
  def updateRules(action: Message): Unit ={
    this.synchronized{
      updateFlag = true
      monitorRules = action.modifyType match {
        case "delete" => monitorRules.filter(_.getRuleId != action.rule.getRuleId)
        case "insert" => monitorRules.+:(action.rule)
        case "modify" => monitorRules.map(rule => {if(rule.getRuleId == action.rule.getRuleId) action.rule else rule})
      }
    }
  }

  /**
    * 判断布控规则是否更新过，并进行重新广播
    * @param sc
    * @return 布控规则广播变量
    */
  def updateAndGet(sc: SparkContext) = {
    this.synchronized{
      if(updateFlag){
        if(broadcastVar != null){
          broadcastVar.unpersist()
        }

        broadcastVar = sc.broadcast(monitorRules)
        updateFlag = false
      }
    }
    broadcastVar
  }

}

/**
  * 单例模式，获取布控规则单例
  */
object BroadcastWrapper{
  val wrapper = new BroadcastWrapper
  def getInstance(): BroadcastWrapper = wrapper
}