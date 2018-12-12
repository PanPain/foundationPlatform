package edu.xd.ridelab.foundationplatform.hbaseconf;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;

/**
 * HBase配置类，用于获取HBase配置，连接
 *
 * @author QiHaiyang
 * @date 2018/5/7
 */
public class HBaseConfig {

    private static Configuration conf = null;
    private static Connection conn = null;

    static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.master", ReadHBaseConf.getProperty("HBASE_MASTER"));
        conf.set("hbase.zookeeper.quorum", ReadHBaseConf.getProperty("HBASE_ZOOKEEPER_QUORUM"));
        conf.set("hbase.zookeeper.property.clientport", "2181");
        try {
            conn = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            System.out.println("HBase获取连接失败");
            e.printStackTrace();
        }
    }

    private HBaseConfig() {

    }

    /**
     * 获取HBase配置
     *
     * @return Configuration
     */
    public static Configuration getConfig() {
        return conf;
    }

    /**
     * 获取HBase连接
     *
     * @return Connection
     */
    public static Connection getConn() {
        return conn;
    }

    /**
     * 通过表名获取对应的HBase连接
     *
     * @param tableName
     * @return Table
     */
    public static Table getConnectionByTableName(String tableName) {
        try {
            return conn.getTable(TableName.valueOf(tableName));
        } catch (IOException e) {
            System.out.println("HBase获取表的连接失败");
            e.printStackTrace();
        }
        return null;
    }


}
