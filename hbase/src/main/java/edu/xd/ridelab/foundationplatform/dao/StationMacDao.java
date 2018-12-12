package edu.xd.ridelab.foundationplatform.dao;

import edu.xd.ridelab.foundationplatform.hbaseconf.HBaseConfig;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangxin
 * @Date 18/5/21 15:46
 * @Description StationMacRecord表的相关操作
 **/
public class StationMacDao {



    /**
      * @Description: 根据mac地址和起始结束时间查找记录
      * @Author: Zhangxin
      * @Date: 18/5/21
      * @Param: mac轨迹重现的Mac地址  timePostfix表名时间后缀集合
      * @Return: 结果的集合
      */
    public List<ResultScanner> getTrackBackResult(String mac, List<String> timePostfix){
        Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(mac));
        Scan scan = new Scan();
        scan.setFilter(filter);
        List<ResultScanner> result = new ArrayList<>();
        Admin admin = null;
        try {
            admin =  HBaseConfig.getConn().getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String tableName : timePostfix){
            Table table = null;
            try {
                if(admin.tableExists(TableName.valueOf("StationMac" + tableName))) {
                    table = HBaseConfig.getConnectionByTableName("StationMac" + tableName);
                    ResultScanner rs = table.getScanner(scan);
                    if(rs != null){
                        result.add(rs);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
