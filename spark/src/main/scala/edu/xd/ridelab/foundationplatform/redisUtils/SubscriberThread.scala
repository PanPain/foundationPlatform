package edu.xd.ridelab.foundationplatform.redisUtils

import redis.clients.jedis.{Jedis, JedisPubSub}

/**
  * 订阅者线程
  * @author cwz
  * @date 2018/5/6
  * @since 0.0.0
  * @param subscriber 订阅者处理逻辑
  * @param channel  订阅的主题名
  * @param jedis  redis连接
  */
class SubscriberThread(subscriber: JedisPubSub, channel: String, jedis: Jedis) extends Thread{

  override def run() {
    System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel))
    try {
      jedis.subscribe(subscriber, channel) //通过subscribe 的api去订阅，入参是订阅者和频道名
    } catch {
      case e: Exception => {
        System.out.println(String.format("subsrcibe channel error, %s", e))
      }
    } finally {
      if (jedis != null) jedis.close()
    }
  }
}
