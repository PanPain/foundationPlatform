/*
package edu.xd.ridelab.foundationplatform.redisUtils;
*/
/**
 * @ClassName Publisher
 * @Author：WW
 * @Description  发布者测试demo
 * @Date: 11:30$ 2018/5/10$
 **//*

import com.google.gson.Gson;
import edu.xd.ridelab.foundationplatform.mysql.vo.Rule;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Date;


public class Publisher extends Thread {

    private final Gson gson=new Gson();

    private final JedisPool jedisPool;

    public Publisher(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Jedis jedis = jedisPool.getResource();  //连接池中取出一个连接
        int i=0;

        Rule rule=new Rule();
        rule.setRuleId((long)i);
        rule.setUserId(1001L+i);
        rule.setRuleType("123");
        rule.setStatus("11");
        rule.setRuleName("1234");
        rule.setRuleArea("213");
        rule.setRuleExpireTime(new Date());
        rule.setRuleCreateTime(new Date());
        rule.setTargetMac("123");

        Message o1 = new Message("insert", rule);
        String line = gson.toJson(o1);
        jedis.publish("mychannel", line);

        rule.setStatus("testtest");
        Message o2 = new Message("modify", rule);
        line = gson.toJson(o2);
        jedis.publish("mychannel", line);


        Message o3 = new Message("delete", rule);
        line = gson.toJson(o3);
        jedis.publish("mychannel", line);
//        while (true && i < 10) {
//
//            i++;
//
//        }
    }

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.0.150", 6379);
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.ping());
        Publisher publisher=new Publisher(jedisPool);
        publisher.start();
    }
}

*/
