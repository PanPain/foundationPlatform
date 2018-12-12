package edu.xd.ridelab.foundationplatform.operation;

import edu.xd.ridelab.foundationplatform.dao.DaoUtil;
import edu.xd.ridelab.foundationplatform.record.StationMacRecord;
import edu.xd.ridelab.foundationplatform.record.TransferRecord;
import edu.xd.ridelab.foundationplatform.tool.TimeCalculate;
import edu.xd.ridelab.foundationplatform.vomapper.MapperUtil;
import lombok.Data;
import org.apache.hadoop.hbase.client.ResultScanner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 轨迹重现
 * @author Zhangxin
 * @date 2018/5/7
 */
@Data
public class TrackBack {
    /** hbaseconf.properties 中描述列族的属性名称 */
    private static final String CF_Property = "COLUMN_FAMILY";

    /** hbaseconf.properties 分隔符属性名称 */
    private static final String SEPARATOR = "SEPARATOR";

    /**
      * @Description: 根据mac，时间段获取该时间段内的所有记录
      * @Author: Zhangxin
      * @Date: 18/5/18
      * @Return:
      */
    public List<StationMacRecord> getTrackBackList(String mac, LocalDateTime start, LocalDateTime end){
        List<String> timePostfix = TimeCalculate.getTimeCalculate().getDatePostfixList(start, end);
        List<ResultScanner> scanners = DaoUtil.getStationMacDao().getTrackBackResult(mac, timePostfix);
        List<StationMacRecord> result = MapperUtil.getStationMacMapper().resultToStaionMac(scanners);
        return result;
    }



}
