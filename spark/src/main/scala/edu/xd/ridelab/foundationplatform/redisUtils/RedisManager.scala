package edu.xd.ridelab.foundationplatform.redisUtils

import java.util.Properties

import edu.xd.ridelab.foundationplatform.properties.PropertyFileReader
import org.slf4j.{Logger, LoggerFactory}
import redis.clients.jedis.{Jedis, JedisPool, JedisPoolConfig}

/**
  * Redis线程池
  * @author cwz
  * @date 2018/05/10
  * @since 0.0.0
  */
class RedisManager extends Serializable{
  val prop: Properties = PropertyFileReader.readProperties("redis.properties")
  var pool: JedisPool = _
  var heartbeatHash: String = _
  var rules: String = _

  {
    pool = new JedisPool(new JedisPoolConfig, prop.getProperty("redis.host"), prop.getProperty("redis.port").toInt)
    heartbeatHash = prop.getProperty("redis.heartbeatHash")
    rules = prop.getProperty("redis.rules")
  }

  def getConnection(): Jedis = pool.getResource

}

object RedisManager {
  val logger: Logger = LoggerFactory.getLogger("RedisManager")
  @volatile var manager: RedisManager = _

  def getRedisManager: RedisManager = {
    if(manager == null) {
      synchronized{
        if (manager == null) {
          manager = new RedisManager()
        }
      }
    }
    manager
  }

  def getConnection: Jedis = getRedisManager.getConnection()

  /**
    * 对指定表指定字段进行增加操作
    * @param key
    * @param field
    * @param value
    */
  def incBy(key: String, field: String, value: Integer): Unit ={
    val jedis = getConnection
    jedis.hincrBy(key, field, value.toLong)
    jedis.close
  }

  /**
    * 判断指定hash表中字段是否存在
    * @param key
    * @return
    */
  def isKeyExists(key: String, filed: String): Boolean = {
    val jedis = getConnection
    val result = jedis.hexists(key, filed)
    jedis.close()
    result
  }

  /**
    * 判断心跳表中指定机具心跳是否存在
    * @param field
    * @return
    */
  def isHearbeatKeyExists(field: String) = isKeyExists(getRedisManager.heartbeatHash, field)

  /**
    * 更新心跳表
    * @param field
    * @param value
    */
  def setHearbeat(field: String, value: String): Unit ={
    val jedis = getConnection
    jedis.hset(getRedisManager.heartbeatHash, field, value)
    jedis.close
  }

  /**
    * 获取名为key的hash表中对应字段值
    * @param key
    * @param field
    * @return
    */
  def get(key: String, field: String): String ={
    val jedis = getConnection
    val value = jedis.hget(key, field)
    value
  }

  /**
    * 向指定channel发送消息
    * @param channel
    * @param value
    */
  def publish(channel:String, value: String): Unit = {
    val jedis = getConnection
    jedis.publish(channel, value)
    jedis.close
  }

  /**
    * 启动监控规则订阅线程
    */
  def subscribeRules(): Unit ={
    new SubscriberThread(new RuleSubscriber(), getRedisManager.rules, getConnection).start()
  }

  def main(args: Array[String]): Unit = {
    RedisManager.incBy("test", "key", 1)
  }
}
