package edu.xd.ridelab.foundationplatform.RedisPool;

import edu.xd.ridelab.foundationplatform.RedisPool.RedisPool;
import kotlin.jvm.functions.Function1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.concurrent.atomic.AtomicLong;

public class RedisUtils {

  private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

  public static <A> A withJedis(Function1<Jedis, A> consumer) {
    Jedis jedis = null;
    try {
      jedis = getJedis();
      return consumer.invoke(jedis);
    } finally {
      if (jedis != null) releaseJedis(jedis);
    }
  }

  // 获取 Redis 连接
  public static Jedis getJedis() {
    Jedis jedis = RedisPool.poolForWrite.getResource();
    // Redis 访问密码, 目前只有阿里云上的 Redis 有密码
    // jedis.auth("187b6799-fc88-4dc7-bd33-138a001c9da9");
    logStatistics(count.incrementAndGet(), "get resource");
    return jedis;
  }

  public static void releaseJedis(Jedis jedis) {
    logStatistics(count.decrementAndGet(), "return resource");
    RedisPool.poolForWrite.returnResource(jedis);
  }

  public static void returnBrokenJedis(Jedis jedis) {
    logStatistics(count.decrementAndGet(), "return broken resource");
    RedisPool.poolForWrite.returnBrokenResource(jedis);
  }

  private static void logStatistics(long c, String m) {
    if (c >= 4900) {
      // 98 % used
      logger.warn("redis links amount becomes {} after {}", c, m);

    } else {
      // multiple of 100
      logger.debug("redis links amount becomes {} after {}", c, m);
    }
  }

  private static final AtomicLong count = new AtomicLong(0);

}
