package edu.xd.ridelab.foundationplatform;

import edu.xd.ridelab.foundationplatform.RedisUtils.DateUtil;
import edu.xd.ridelab.foundationplatform.RedisUtils.RedisMapper;
import edu.xd.ridelab.foundationplatform.RedisUtils.TaskConfig;
import edu.xd.ridelab.foundationplatform.mysql.vo.RecordPerDayStatistics;
import edu.xd.ridelab.foundationplatform.mysqlAlone.SqlUtils;

import java.text.ParseException;
import java.util.concurrent.*;

/**
 * @Author ChenXiang
 * @Date 2018/05/10,19:05
 * 将spark缓存到redis中的数据解析后更新RECORD_PER_DAY_STATISTICS表
 * 主键为key，机具和是否压缩（0不压，1压缩）
 * redis中中key为日期，格式如：20180509
 * field格式: 机具:压缩:MAC，机具:压缩:AP_MAC,机具:压缩:VI:QQ（后面的都是虚拟身份，格式与QQ相同）
 * value全部是数量
 */
public class SyncToMysqlRunnable implements Runnable {
    public static String oldKey;
    DateUtil format = new DateUtil();

    @Override
    public void run() {
        String newKey = format.getNowString();
        try {
            if (!newKey.equals(oldKey)){
            for (RecordPerDayStatistics recordPerDayStatistics : new RedisMapper(oldKey).toRecordPerDayStatistics()) {
                SqlUtils.updateRecordPerDayStatisticsToMysql(recordPerDayStatistics);
            }
            for (RecordPerDayStatistics recordPerDayStatistics:new RedisMapper(newKey).toRecordPerDayStatistics()){
                SqlUtils.insertRecordPerDayStatisticsToMysql(recordPerDayStatistics);
            }
            oldKey = newKey;
            }else {
                for (RecordPerDayStatistics recordPerDayStatistics : new RedisMapper(oldKey).toRecordPerDayStatistics()) {
                    SqlUtils.updateRecordPerDayStatisticsToMysql(recordPerDayStatistics);
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService timedSyncToMysql = Executors.newSingleThreadScheduledExecutor();
        SyncToMysqlRunnable syncToMysqlRunnable = new SyncToMysqlRunnable();
        timedSyncToMysql.scheduleAtFixedRate(syncToMysqlRunnable,0,TaskConfig.PERIOD_OF_SYNCTOMTSQL,TimeUnit.MINUTES);
    }
}
