package edu.xd.ridelab.foundationplatform.dao;

/**
 * Created by apple on 18/5/21.
 */
public class DaoUtil {
    /** StationMacDao实例 */
    private static StationMacDao stationMacDao = new StationMacDao();

    public static StationMacDao getStationMacDao(){
        return  stationMacDao;
    }

}
