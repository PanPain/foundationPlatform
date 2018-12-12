package edu.xd.ridelab.foundationplatform.cache;

import edu.xd.ridelab.foundationplatform.hbaseconf.HBaseConfig;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HBase中表名的缓存，用于查询和插入时进行表是否存在的判断
 *
 * @author QiHaiyang
 * @date 2018/5/7
 */
public class HBaseTableNameCache {

    private static final Object CONSTANT_VALUE = new Object();
    private static ConcurrentHashMap<String, Object> existsTables = new ConcurrentHashMap<>(); //解决多线程问题

    static {
        try {
            Admin admin = HBaseConfig.getConn().getAdmin();
            TableName[] tableNames = admin.listTableNames();
            for (TableName name : tableNames)
                existsTables.put(name.toString(), CONSTANT_VALUE);
        } catch (IOException e) {
            System.out.println("初始化HBase已存在表失败");
            e.printStackTrace();
        }
    }

    private HBaseTableNameCache() {

    }

    /**
     * 根据表名判断表是否存在
     *
     * @param tableName
     * @return boolean
     */
    public static boolean isTableExists(String tableName) {
        return existsTables.containsKey(tableName);
    }

    /**
     * 向缓存中加入一个新的表名，因为存储适用的ConcurrentHashMap，所以这里不用顾及线程安全问题
     *
     * @param tableName
     */
    public static void addTableName(String tableName) {
        existsTables.put(tableName, CONSTANT_VALUE);
    }
}
