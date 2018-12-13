package edu.xd.ridelab.foundationplatform.operation;

import edu.xd.ridelab.foundationplatform.cache.HBaseTableNameCache;
import edu.xd.ridelab.foundationplatform.hbaseconf.HBaseConfig;
import edu.xd.ridelab.foundationplatform.insert.InsertFactory;
import edu.xd.ridelab.foundationplatform.record.StationMacRecord;
import edu.xd.ridelab.foundationplatform.record.TransferRecord;
import edu.xd.ridelab.foundationplatform.tool.TimeCalculate;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

import java.time.LocalDateTime;
import java.util.List;

public class TableInsert {

    InsertFactory insertFactory = new InsertFactory();


    public void insertToHBase(Object object) throws Exception {
        String tableName = TimeCalculate.getTimeCalculate().getTableName(object);
        List<Put> puts = insertFactory.makeInsert(object);
        boolean flag = HBaseTableNameCache.isTableExists(tableName);
        System.out.println(flag);
        if(!flag){
            //如果表不在
            TableCreate.createTableByName(tableName);
        }
        Table table = null;
        try {
            table = HBaseConfig.getConnectionByTableName(tableName);
            table.put(puts);
        }finally {
            table.close();
        }
    }

    public static void main(String[] args) {
        StationMacRecord macRecord = new StationMacRecord();
        macRecord.setDevmac("EE");
        macRecord.setFirstTime(LocalDateTime.now());
        macRecord.setLastTime(LocalDateTime.now());
        macRecord.setPlaceNo("00334111");
        macRecord.setMac("EE-FF");
        TableInsert insert = new TableInsert();
        try {
            insert.insertToHBase(macRecord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
