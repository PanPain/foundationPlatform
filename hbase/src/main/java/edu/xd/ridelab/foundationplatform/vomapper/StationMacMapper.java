package edu.xd.ridelab.foundationplatform.vomapper;

import edu.xd.ridelab.foundationplatform.record.StationMacRecord;
import edu.xd.ridelab.foundationplatform.table.StationMacRecordInfo;
import edu.xd.ridelab.foundationplatform.tool.TimeCalculate;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangxin
 * @Date 18/5/21 15:44
 * @Description 将数据库scan的结果转为StationMacRecord
 **/
public class StationMacMapper {
    /** hbaseconf.properties 中描述列族的属性名称 */
    private static final String CF_Property = "COLUMN_FAMILY";

    /** hbaseconf.properties 分隔符属性名称 */
    private static final String SEPARATOR = "SEPARATOR";

    /**
     * @Author zhangxin
     * @Date 18/5/21 16:15
     * @Description 将一个scan操作的结果转为StationMacRecord集合
     **/
    public List<StationMacRecord> resultToStaionMac(List<ResultScanner> resultScanner){
        List<StationMacRecord> result = new ArrayList<>();
        for(ResultScanner resultScanner1 : resultScanner){
            for(Result result1 : resultScanner1){
                result.add(resultToStationMac(result1));
            }
        }
        return result;
    }

    /**
     * @Author zhangxin
     * @Date 18/5/21 16:19
     * @Description 将一行记录转为StationMacRecord
     **/
    public StationMacRecord resultToStationMac(Result result){
        StationMacRecord stationMacRecord = new StationMacRecord();
        convertColumnsToStationMac(result, stationMacRecord);
        return stationMacRecord;
    }

    public void convertColumnsToStationMac(Result result, StationMacRecord stationMacRecord){
        for(Cell cell : result.listCells()){
            String column = Bytes.toString(CellUtil.cloneQualifier(cell));
            String value = Bytes.toString(cell.getValue());
            if(!value.equals("") ){
                switch (column){
                    case StationMacRecordInfo.Mac : stationMacRecord.setMac(value); break;
                    case StationMacRecordInfo.Brand : stationMacRecord.setBrand(value); break;
                    case StationMacRecordInfo.SsidList : stationMacRecord.setSsid(value);break;
                    case StationMacRecordInfo.FirstTime : stationMacRecord.setFirstTime(TimeCalculate.getTimeCalculate().convertStringToDate(value));break;
                    case StationMacRecordInfo.Rssi : stationMacRecord.setRssi(Integer.getInteger(value));break;
                    case StationMacRecordInfo.IdType : stationMacRecord.setIdType(value);break;
                    case StationMacRecordInfo.IdContent : stationMacRecord.setIdContent(value);break;
                    case StationMacRecordInfo.Ssid : stationMacRecord.setSsid(value);break;
                    case StationMacRecordInfo.Bssid : stationMacRecord.setBssid(value);break;
                    case StationMacRecordInfo.Chan : stationMacRecord.setChan(Integer.getInteger(value));break;
                    case StationMacRecordInfo.Enctryption : stationMacRecord.setEncryption(value);break;
                    case StationMacRecordInfo.X : stationMacRecord.setX(Double.parseDouble(value));break;
                    case StationMacRecordInfo.Y : stationMacRecord.setY(Double.parseDouble(value));break;
                    case StationMacRecordInfo.PlaceNo : stationMacRecord.setPlaceNo(value);break;
                    case StationMacRecordInfo.Devmac : stationMacRecord.setDevmac(value);break;
                    case StationMacRecordInfo.Longitude : stationMacRecord.setLongitude(Double.parseDouble(value));break;
                    case StationMacRecordInfo.Latitude : stationMacRecord.setLatitude(Double.parseDouble(value));break;
                    case StationMacRecordInfo.LastTime : stationMacRecord.setLastTime(TimeCalculate.getTimeCalculate().convertStringToDate(value)); break;
                    case StationMacRecordInfo.Count : stationMacRecord.setCount(Integer.getInteger(value));break;
                }
            }

        }
    }

}
