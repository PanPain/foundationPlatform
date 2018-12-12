package edu.xd.ridelab.foundationplatform.redisUtils

import com.google.gson.Gson
import edu.xd.ridelab.foundationplatform.mysql.vo.Rule
import org.slf4j.{Logger, LoggerFactory}
import redis.clients.jedis.JedisPubSub

/**
  * 监控规则更新定义
  * @param modifyType 动作类型 insert：新增，modify：修改，delete：删除
  * @param rule 监控规则
  */
case class Message(modifyType: String, rule: Rule)

/**
  * 订阅redis特定频道，收到消息后判断更新规则
  * @author VV
  * @date 9:49 2018/5/10
  * @since 0.0.0
  */
class RuleSubscriber extends JedisPubSub{
  private val LOGGER: Logger = LoggerFactory.getLogger("RuleSubscriber")
  private val gson: Gson = new Gson


  override def onMessage(channel: String, message: String) {
    //收到消息会调用
    val o: Message = gson.fromJson(message, classOf[Message])
    val broadcastWrapper: BroadcastWrapper = BroadcastWrapper.getInstance
    broadcastWrapper.updateRules(o)
    LOGGER.info("receive redis published message, {}", channel + " : " + message)
    super.onMessage(channel, message)
  }

  override def onSubscribe(channel: String, subscribedChannels: Int) {
    //订阅了频道会调用
    LOGGER.info("subscribe redis channel success, {}", channel + " : "+ subscribedChannels)
    super.onSubscribe(channel, subscribedChannels)
  }

  override def onUnsubscribe(channel: String, subscribedChannels: Int) {
    //取消订阅 会调用
    LOGGER.info("unsubscribe redis channel,{}", channel + " : " + subscribedChannels)
    super.onUnsubscribe(channel, subscribedChannels)
  }
}
