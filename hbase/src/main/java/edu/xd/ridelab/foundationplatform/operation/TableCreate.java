package edu.xd.ridelab.foundationplatform.operation;

import edu.xd.ridelab.foundationplatform.cache.HBaseTableNameCache;
import edu.xd.ridelab.foundationplatform.hbaseconf.HBaseConfig;
import edu.xd.ridelab.foundationplatform.hbaseconf.ReadHBaseConf;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableExistsException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * HBase表创建操作
 *
 * @author QiHaiyang
 * @date 2018/5/7
 */
public class TableCreate {

    private static final String CF_Property = "COLUMN_FAMILY"; //hbaseconf.properties 中描述列族的属性名称
    private static final String SEPARATOR = "SEPARATOR";               //hbaseconf.properties 分隔符属性名称

    protected TableCreate() {

    }

    /**
     * 根据表名创建类，列族是配置文件中指定好的
     *
     * @param tableName
     */
    public static void createTableByName(String tableName) {
        try {
            Admin admin = HBaseConfig.getConn().getAdmin();
            if (!HBaseTableNameCache.isTableExists(tableName)) {
                synchronized (TableCreate.class) {
                    if (HBaseTableNameCache.isTableExists(tableName)) return;    //双重判断，避免重复建表，应对多线程情况
                    String[] columnFamilies = ReadHBaseConf.getProperty(CF_Property).split(ReadHBaseConf.getProperty(SEPARATOR));
                    HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
                    for (String cf : columnFamilies) {
                        tableDescriptor.addFamily(new HColumnDescriptor(Bytes.toBytes(cf)));
                    }
                    admin.createTable(tableDescriptor);
                }
                HBaseTableNameCache.addTableName(tableName); //向缓存中加入
            }
        }catch(TableExistsException e){
            System.out.println("HBase创建表——" + tableName + "——已经存在");
            HBaseTableNameCache.addTableName(tableName); //向缓存中加入
            e.printStackTrace();
        }catch (IOException e) {
            System.out.println("HBase创建表——" + tableName + "——创建失败");
            e.printStackTrace();
        }
    }
}
