package edu.xd.ridelab.foundationplatform.vomapper;

/**
 * Created by apple on 18/5/21.
 */
public class MapperUtil {
    /** StationMacMapper 实例 */
    private static StationMacMapper stationMacMapper = new StationMacMapper();

    /**
     * @Author zhangxin
     * @Date 18/5/21 17:04
     * @Description 返回StationMacMapper实例
     **/
    public static StationMacMapper getStationMacMapper(){
        return stationMacMapper;
    }
}
