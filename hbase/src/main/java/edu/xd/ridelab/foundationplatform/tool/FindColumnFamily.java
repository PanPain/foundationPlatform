package edu.xd.ridelab.foundationplatform.tool;

import edu.xd.ridelab.foundationplatform.record.APMacRecord;
import edu.xd.ridelab.foundationplatform.record.MonitorAlarmRecord;
import edu.xd.ridelab.foundationplatform.record.StationMacRecord;
import edu.xd.ridelab.foundationplatform.record.VIRecord;

public class FindColumnFamily {
    protected FindColumnFamily(){}

    public static String findColumnFamily(APMacRecord apMacRecord) {
        if(apMacRecord == null || apMacRecord.getPlaceNo() == null)
            throw new IllegalArgumentException();
        return apMacRecord.getPlaceNo().substring(0,6);
    }

    public static String findColumnFamily(StationMacRecord stationMacRecord) {
        if(stationMacRecord == null || stationMacRecord.getPlaceNo() == null)
            throw new IllegalArgumentException();
        return stationMacRecord.getPlaceNo().substring(0,6);
    }

    public static String findColumnFamily(VIRecord viRecord) {
        if(viRecord == null || viRecord.getPlaceNo() == null)
            throw new IllegalArgumentException();
        return viRecord.getPlaceNo().substring(0,6);
    }

    public static String findColumnFamily(MonitorAlarmRecord monitorAlarmRecord){
        /** MonitorAlarmRecord列族为warn */
        return "warn";
    }

    public static String findColumnFamily(Object object){
        String className = object.getClass().getSimpleName();
        switch(className){
            case "StationMacRecord" : return findColumnFamily((StationMacRecord) object);
            case "VIRecord" : return  findColumnFamily((VIRecord) object);
            case "APMacRecord" : return findColumnFamily((APMacRecord) object);
            case "MonitorAlarmRecord" : return findColumnFamily((MonitorAlarmRecord) object);
            default :
                throw new IllegalArgumentException();
        }
    }
}
