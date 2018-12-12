package edu.xd.ridelab.foundationplatform.insert;

import edu.xd.ridelab.foundationplatform.tool.FindColumnFamily;
import edu.xd.ridelab.foundationplatform.tool.FindRowKey;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class InsertFactory{
    public List<Put> makeInsert(Object object) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        byte[] family =  Bytes.toBytes(FindColumnFamily.findColumnFamily(object));
        byte[] rowKey = Bytes.toBytes(FindRowKey.findRowKey(object));
        List<Put> puts = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.get(object) == null) continue;  //如果该列为空，直接继续
            Put put = new Put(rowKey);
            switch(field.getType().getSimpleName()){
                case "Double" :  put.addColumn(family , Bytes.toBytes(field.getName()) , Bytes.toBytes( (Double)field.get(object)+ "")); break;
                case "Float":    put.addColumn(family , Bytes.toBytes(field.getName()) , Bytes.toBytes( (Float)field.get(object)+ "")); break;
                case "Integer":  put.addColumn(family , Bytes.toBytes(field.getName()) , Bytes.toBytes( (Integer)field.get(object)+ "")); break;
                case "Long" :    put.addColumn(family , Bytes.toBytes(field.getName()) , Bytes.toBytes( (Long)field.get(object)+ "")); break;
                default :
                    put.addColumn(family , Bytes.toBytes(field.getName()) , Bytes.toBytes(field.get(object).toString())); break;
            }
            puts.add(put);
        }
        return puts;
    }

}
