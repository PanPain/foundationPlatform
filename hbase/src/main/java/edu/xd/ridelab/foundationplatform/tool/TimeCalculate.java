package edu.xd.ridelab.foundationplatform.tool;

import edu.xd.ridelab.foundationplatform.record.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * 计算时间获取表名
 *
 * @Author zhangxin
 * @Date 2018/5/7 19:41
 * @Since 1.0
 **/
public class TimeCalculate {

    /** 初始化开始时间 **/
    private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(1970,1,1,8,0,0);

    /** TimeCalculate类实例 **/
    private static TimeCalculate timeCalculate = null;

    /**
     * @Author zhangxin
     * @Date 2018/5/7 19:40
     * @Description 构造函数
     **/
    private TimeCalculate() {}

    public static TimeCalculate getTimeCalculate() {
        if(timeCalculate == null) {
            timeCalculate = new TimeCalculate();
        }
        return timeCalculate;
    }


    /**
     * @Author zhangxin
     * @Date 2018/5/7 19:26
     * @Description 返回表名
     * @param object vo实例
     **/
    public String getTableName(Object object){
        String type = null;
        String date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        if(object instanceof MonitorAlarmRecord){
            type = "Monitor";
            date =  ((MonitorAlarmRecord) object).getTime().format(formatter);
            return type + date;
        }
        if (object instanceof StationMacRecord){
            type = "StationMac";
            date = ((StationMacRecord) object).getFirstTime().format(formatter);
        }
        else if (object instanceof APMacRecord){
            type = "APMac";
            date = ((APMacRecord) object).getFirstTime().format(formatter);
        }
        else if (object instanceof VIRecord){
            type = "VIRecord";
            date = ((VIRecord) object).getFirstTime().format(formatter);
        }
        return type + date ;
    }

    /**
     * @Author zhangxin
     * @Date 2018/5/7 19:27
     * @Description 计算时间
     * @param object vo实例
     **/
    public long getTimeStamp(Object object){
        if(object instanceof MonitorAlarmRecord){
            return ((MonitorAlarmRecord) object).getTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        }
        return ((TransferRecord)object).getFirstTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    /**
      * @Description: 获取两个时间之间的时间段。
      * @Author: Zhangxin
      * @Date: 18/5/18
      * @Return:
      */
    public List<String> getDatePostfixList(LocalDateTime startTime, LocalDateTime endTime){
        int startYear = startTime.getYear();
        int startMonth = startTime.getMonthValue();
        int endYear = endTime.getYear();
        int endMonth = endTime.getMonthValue();
        int endMonthofEveryYear = 0;
        int startMonthofEveryYear = startMonth;
        List<String> result = new ArrayList<>();
        for(int year = startYear; year <= endYear; year++){
            if(year != endYear){
                endMonthofEveryYear = 12;
            }
            else {
                endMonthofEveryYear = endMonth;
            }
            if(year != startYear){
                startMonthofEveryYear = 1;
            }
            else {
                startMonthofEveryYear = startMonth;
            }
            for(int month = startMonthofEveryYear; month <= endMonthofEveryYear; month++){
                if(month < 10)
                    result.add(year + "0" + month);
                else {
                    result.add(year + "" + month);
                }
            }
        }
        return result;
    }

    /**
     * @Author zhangxin
     * @Date 18/5/21 16:49
     * @Description 将字符串转为LocalDateTime类型
     **/
    public LocalDateTime convertStringToDate(String time){
        LocalDateTime result = LocalDateTime.parse(time);
        return result;
    }

}
