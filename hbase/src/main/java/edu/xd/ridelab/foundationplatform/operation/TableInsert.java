package edu.xd.ridelab.foundationplatform.operation;

import edu.xd.ridelab.foundationplatform.cache.HBaseTableNameCache;
import edu.xd.ridelab.foundationplatform.hbaseconf.HBaseConfig;
import edu.xd.ridelab.foundationplatform.insert.InsertFactory;
import edu.xd.ridelab.foundationplatform.record.TransferRecord;
import edu.xd.ridelab.foundationplatform.tool.TimeCalculate;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

import java.util.List;

public class TableInsert {

    InsertFactory insertFactory = new InsertFactory();


    public void insertToHBase(Object object) throws Exception {
        String tableName = TimeCalculate.getTimeCalculate().getTableName(object);
        List<Put> puts = insertFactory.makeInsert(object);
        if(!HBaseTableNameCache.isTableExists(tableName)){
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
}
