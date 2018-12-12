package edu.xd.ridelab.receiver.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author PanTeng
 * @Description
 * @file DateUtils.java
 * @date 2018/5/10
 * @attention Copyright (C),2004-2018,RIDELab, SEI, XiDian University
 */
public class DateUtils {
    public DateFormat format;
    static Calendar calendar = Calendar.getInstance();

    public DateUtils() {
        // TODO Auto-generated constructor stub
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @description 设置格式，默认是yyyy-MM-dd HH:mm:ss
     * @author PanTeng
     * @date 下午7:05  2018/5/10
     * @para
     */
    public void setDateFormat(String pattern) {
        format = new SimpleDateFormat(pattern);
    }

    /**
     * @description 将传入的字符串如8：00：01转成规定格式的字符串，相当于去掉中文:换成英文：之类的操作
     * @author PanTeng
     * @date 下午7:06  2018/5/10
     * @para
     */
    public String checkFormatString(String time) {
        try {
            String[] times = time.split(":");
            if (times.length != 2 && times.length != 3) {
                times = time.split("：");
                if (times.length != 2 && times.length != 3) {
                    return null;
                }
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(times[1]));
            calendar.set(Calendar.SECOND, 0);
            String formatStr = format.format(calendar.getTime());
            return formatStr;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @description 获取当前时间的时间戳
     * @author PanTeng
     * @date 下午7:06  2018/5/10
     * @para
     */
    public Timestamp getTimestamp() {
        String nowStr = format.format(new Date());
        Timestamp timestamp = Timestamp.valueOf(nowStr);
        return timestamp;
    }

    /**
     * @description 获取传入指定字符串对应的描述转换成时间戳，字符串为北京1970年1月1日08:00:00开始的绝对秒数
     * @author PanTeng
     * @date 下午7:06  2018/5/10
     * @para
     */
    public Timestamp getTimestampBySecondString(String intime) {
        long time = Long.parseLong(intime);
        time = time * 1000;
        Timestamp timestamp = new Timestamp(time);
        return timestamp;
    }

    /**
     * @description 从毫秒字符串获取日期
     * @author PanTeng
     * @date 下午7:07  2018/5/10
     * @para
     */
    public 	Calendar getCalendarFromMillisString(String longTime){
        String time = longTime.trim();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(time));
        return cal;
    }

    /**
     * @description 将毫秒字符串转化成可视化字符串
     * @author PanTeng
     * @date 下午7:07  2018/5/10
     * @para
     */
    public String getFormatStringFromMillisString(String longTime) {
        String time = longTime.trim();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(time));
        return getFormatDate(cal);
    }

    public Date getDateBySecondString(String intime) {
        long time = Long.parseLong(intime);
        time = time * 1000;
        Date date = new Date(time);
        return date;
    }

    /**
     * @MethodName getTimestampByDate
     * @Description 根据date获取Timestamp
     * @author CaiShunda
     * @data 2016-7-12 下午8:47:53
     * @param date
     * @return
     */
    public Timestamp getTimestampByDate(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * @description 获取北京1970年1月1日08:00:00开始的绝对秒数,
     * @author PanTeng
     * @date 下午7:07  2018/5/10
     * @para
     */
    public String getSecondFromTimestamp(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        long time = timestamp.getTime();
        time = time / 1000;
        return String.valueOf(time);
    }

    /**
     * @description 通过Date获取秒数
     * @author PanTeng
     * @date 下午7:08  2018/5/10
     * @para
     */
    public String getSecondFromDate(Date date) {
        if (date == null) {
            return null;
        }
        long time = date.getTime();
        time = time / 1000;
        return String.valueOf(time);
    }

    /**
     * @description 通过Calendar获取秒数
     * @author PanTeng
     * @date 下午7:08  2018/5/10
     * @para
     */
    public String getSecondFromCalendar(Calendar cal) {
        long time = cal.getTimeInMillis();
        time = time / 1000;
        return String.valueOf(time);
    }

    /**
     * @description 当前时间秒数
     * @author PanTeng
     * @date 下午7:08  2018/5/10
     * @para
     */
    public long getSecondCurrent() {
        return new Date().getTime() / 1000;
    }

    public long getSecondFromFormatString(String dateStr) {
        Date date;
        try {
            date = getDateByFormat(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        long result = date.getTime();
        return result/1000;
    }

    /**
     * @description 获取此时的日期
     * @author PanTeng
     * @date 下午7:08  2018/5/10
     * @para
     */
    public Calendar getNow() {
        Calendar now = Calendar.getInstance();
        return now;
    }

    /**
     * @description 获取此时的日期
     * @author PanTeng
     * @date 下午7:09  2018/5/10
     * @para
     */
    public String getNowString() {
        Calendar now = Calendar.getInstance();
        return getFormatDate(now);
    }

    /**
     * @description 获取cal对应日期的前period的日期
     * @author PanTeng
     * @date 下午7:09  2018/5/10
     * @para
     */
    public Calendar getTheDayBefore(Calendar cal, int period) {
        Calendar before = Calendar.getInstance();
        before.setTimeInMillis(cal.getTimeInMillis());
        before.add(Calendar.DAY_OF_MONTH, -period);
        return before;
    }
    public Calendar getTodayTime(int hour, int min, int sec, int millis){
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, hour);
        time.set(Calendar.MINUTE, min);
        time.set(Calendar.SECOND, sec);
        time.set(Calendar.MILLISECOND, millis);
        return time;
    }

    /**
     * @description 忽略date的年月日，假设date表示的时间是1：00：00，则返回值表示到下一个凌晨一点需要多少毫秒
     * @author PanTeng
     * @date 下午7:09  2018/5/10
     * @para
     */
    public long getMillisToNext(String date) {
        String[] times = date.split(":");
        if (times.length != 3) {
            return 0;
        }
        int hour = Integer.parseInt(times[0]);
        int min = Integer.parseInt(times[1]);
        int sec = Integer.parseInt(times[2]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, sec);
        Calendar now = Calendar.getInstance();
        if (calendar.before(now)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return calendar.getTimeInMillis() - now.getTimeInMillis();
    }

    /**
     * @description 根据传入时间获取指定格式的时间字符串
     * @author PanTeng
     * @date 下午7:10  2018/5/10
     * @para
     */
    public String getFormatDate(Date date) {
        return format.format(date);
    }

    public String getFormatDate(Timestamp timestamp) {
        return format.format(timestamp);
    }

    /**
     * @description 根据传入时间获取指定格式的时间字符串
     * @author PanTeng
     * @date 下午7:10  2018/5/10
     * @para
     */
    public String getFormatDate(Calendar calendar) {
        return format.format(calendar.getTime());
    }

    /**
     * @description 根据传入的北京1970年1月1日08:00:00开始的绝对毫秒数转换成指定格式的日期字符串
     * @author PanTeng
     * @date 下午7:10  2018/5/10
     * @para
     */
    public String getFormatDate(Long timeInMilllis) {
        calendar.setTimeInMillis(timeInMilllis);
        return format.format(calendar.getTime());
    }

    /**
     * @description 根据传入时间转换成毫秒数
     * @author PanTeng
     * @date 下午7:10  2018/5/10
     * @para
     */
    public String getTimeInMilllis(Calendar calendar) {
        return String.valueOf(calendar.getTimeInMillis());
    }

    /**
     * @description 根据传入时间字符串转成yyyyMMdd格式的日期
     * @author PanTeng
     * @date 下午7:10  2018/5/10
     * @para
     */
    public Calendar getCalendarFromyyyyMMdd(String dateStr) {
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(dateStr.substring(0, 4));
        int month = Integer.parseInt(dateStr.substring(4, 6));
        int date = Integer.parseInt(dateStr.substring(6));
        // System.out.println("year="+year);
        // System.out.println("month="+month);
        // System.out.println("date="+date);
        calendar.set(year, month - 1, date, 0, 0, 0);
        calendar.setTimeInMillis(calendar.getTimeInMillis() / 1000 * 1000);
        return calendar;
    }

    /**
     * @description 根据设定格式转换String为Date
     * @author PanTeng
     * @date 下午7:11  2018/5/10
     * @para
     */
    public Date getDateByFormat(String dateStr) throws ParseException {
        Date date = format.parse(dateStr);
        return date;
    }

    /**
     * @description 根据设定格式转换String为Calendar
     * @author PanTeng
     * @date 下午7:11  2018/5/10
     * @para
     */
    public Calendar getCalendarByFormat(String dateStr) throws ParseException {
        Date date = format.parse(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * @description 根据传入的dateStr例如20160606和hour、minute、second获得对应准确的Calendar实例
     * @author PanTeng
     * @date 下午7:11  2018/5/10
     * @para
     */
    public Calendar getCalendarFromyyyyMMdd(String dateStr, int hour,
                                            int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(dateStr.substring(0, 4));
        int month = Integer.parseInt(dateStr.substring(4, 6));
        int date = Integer.parseInt(dateStr.substring(6));
        calendar.set(year, month - 1, date, hour, minute, second);
        calendar.setTimeInMillis(calendar.getTimeInMillis() / 1000 * 1000);
        return calendar;
    }

    /**
     * @description 根据传入日期转换成北京1970年1月1日08:00:00开始的绝对秒数
     * @author PanTeng
     * @date 下午7:11  2018/5/10
     * @para
     */
    public String getTimeInSecond(Date date) {
        long currentMillis = date.getTime();
        currentMillis = currentMillis / 1000;
        String currentSecond = String.valueOf(currentMillis);
        return currentSecond;
    }

    /**
     * @description 计算给出的两个日期之间的天数，向上取整，入2016/07/17-8:00和2016/07/19-0:00相差的天数为2天
     * @author PanTeng
     * @date 下午7:12  2018/5/10
     * @para
     */
    public int getDaysBetweenDate(Date startTime, Date endTime) {
        double ddays = (((double) endTime.getTime() - (double) startTime
            .getTime()) / 86400000);
        double days = Math.ceil(ddays);
        return (int) days;
    }

    /**
     * @description 根据传入的北京1970年1月1日08:00:00开始的绝对毫秒数转换成中文的可视化字符串
     * @author PanTeng
     * @date 下午7:12  2018/5/10
     * @para
     */
    public String getVisibleTimeWithMillis(long time) {
        long millis = time % 1000;
        time = time / 1000;
        long second = time % 60;
        time = time / 60;
        long minute = time % 60;
        time = time / 60;
        long hour = time % 24;
        time = time / 24;
        long day = time;
        long[] times = new long[] { day, hour, minute, second, millis };
        String[] units = new String[] { "天", "小时", "分", "秒", "毫秒" };
        String result = "";
        for (int i = 0; i < times.length - 1; i++) {
            if (times[i] != 0) {
                result = result + times[i] + units[i];
            }
        }

        return result + times[times.length - 1] + units[units.length - 1];
    }

    /**
     * @description 获取daynum的间隔，毫秒
     * @author PanTeng
     * @date 下午7:12  2018/5/10
     * @para
     */
    public long getPeriodOfDay(int daynum) {
        long aDay = 24 * 3600000;
        return aDay * daynum;
    }

    /**
     * @description 获取date的下一天
     * @author PanTeng
     * @date 下午7:12  2018/5/10
     * @para
     */
    public Date getNextDate(Date date) {
        Calendar nextDate = Calendar.getInstance();
        nextDate.setTime(date);
        nextDate.add(Calendar.DAY_OF_MONTH, 1);
        return nextDate.getTime();
    }

    /**
     * @description 获取date加上amount天的date，可为负数
     * @author PanTeng
     * @date 下午7:13  2018/5/10
     * @para
     */
    public Date getDateByAmount(Date date, int amount) {
        Calendar newDate = Calendar.getInstance();
        newDate.setTime(date);
        newDate.add(Calendar.DAY_OF_MONTH, amount);
        return newDate.getTime();
    }

    public Date getLongLongAgo() {
        return new Date(0);
    }

    public static Date createPrimaryDate(
        int year, int month, int date,
        int hourOfDay, int minute, int second
    ) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, hourOfDay, minute, second);
        return calendar.getTime();
    }
}
