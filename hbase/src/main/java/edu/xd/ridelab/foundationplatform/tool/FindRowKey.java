package edu.xd.ridelab.foundationplatform.tool;

import edu.xd.ridelab.foundationplatform.record.APMacRecord;
import edu.xd.ridelab.foundationplatform.record.MonitorAlarmRecord;
import edu.xd.ridelab.foundationplatform.record.StationMacRecord;
import edu.xd.ridelab.foundationplatform.record.VIRecord;

/**
 * 通过实体对象找到找到一行数据的行键
 *
 * @author QiHaiyang
 * @date 2018/5/7
 * @since 使用该类的起始版本
 */
public class FindRowKey {

    protected FindRowKey() {

    }

    public static String findRowKey(APMacRecord apMacRecord) {
        if(apMacRecord == null || apMacRecord.getFirstTime() == null ||
                apMacRecord.getMac() == null || apMacRecord.getDevmac() == null)
            throw new IllegalArgumentException();
        return TimeCalculate.getTimeCalculate().getTimeStamp(apMacRecord) + "#" +
                apMacRecord.getMac() + "#" + apMacRecord.getDevmac();
    }

    public static String findRowKey(StationMacRecord stationMacRecord) {
        if(stationMacRecord == null || stationMacRecord.getFirstTime() == null ||
                stationMacRecord.getMac() == null || stationMacRecord.getDevmac() == null)
            throw new IllegalArgumentException();
        return TimeCalculate.getTimeCalculate().getTimeStamp(stationMacRecord) + "#" +
                stationMacRecord.getMac() + "#" + stationMacRecord.getDevmac();
    }

    public static String findRowKey(VIRecord viRecord) {
        if(viRecord == null || viRecord.getFirstTime() == null ||
                viRecord.getMac() == null || viRecord.getDevmac() == null)
            throw new IllegalArgumentException();
        return TimeCalculate.getTimeCalculate().getTimeStamp(viRecord) + "#" +
                viRecord.getMac() + "#" + viRecord.getDevmac();
    }

    public static String findRowKey(MonitorAlarmRecord monitorAlarmRecord){
        if(monitorAlarmRecord == null || monitorAlarmRecord.getTime() == null ||
                monitorAlarmRecord.getMac() == null || monitorAlarmRecord.getDevmac() == null)
            throw new IllegalArgumentException();
        return TimeCalculate.getTimeCalculate().getTimeStamp(monitorAlarmRecord) + "#" +
                monitorAlarmRecord.getMac() + "#" + monitorAlarmRecord.getDevmac();
    }

    public static String findRowKey(Object object){
        String className = object.getClass().getSimpleName();
        switch(className){
            case "StationMacRecord" : return findRowKey((StationMacRecord) object);
            case "VIRecord" : return  findRowKey((VIRecord) object);
            case "APMacRecord" : return findRowKey((APMacRecord) object);
            case "MonitorAlarmRecord" : return findRowKey((MonitorAlarmRecord) object);
            default :
                throw new IllegalArgumentException();
        }
    }
}
