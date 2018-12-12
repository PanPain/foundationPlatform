package edu.xd.ridelab.foundationplatform.RedisUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	DateFormat format;
	static Calendar calendar = Calendar.getInstance();

	public DateUtil() {
		// TODO Auto-generated constructor stub
		format = new SimpleDateFormat("yyyyMMdd");
	}

	/**
	 * @MethodName setDateFormat
	 * @Description 设置格式，默认是yyyyMMdd，之前的格式是yyyy-MM-dd HH:mm:ss，下面的一些方法使用时牵涉到格式的需要注意
	 * @author CaiShunda
	 * @data 2016-7-8 下午10:34:45
	 * @param pattern
	 */
	public void setDateFormat(String pattern) {
		format = new SimpleDateFormat(pattern);
	}

	/**
	 * @MethodName checkFormatString
	 * @Description 将传入的字符串如8：00：01转成规定格式的字符串，相当于去掉中文:换成英文：之类的操作
	 * @author CaiShunda
	 * @data 2016-7-8 下午10:35:22
	 * @param time
	 * @return
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
	 * @MethodName getTimestamp
	 * @Description 获取当前时间的时间戳
	 * @author CaiShunda
	 * @data 2016-7-8 下午10:37:17
	 * @return
	 */
	public Timestamp getTimestamp() {
		String nowStr = format.format(new Date());
		Timestamp timestamp = Timestamp.valueOf(nowStr);
		return timestamp;
	}

	/**
	 * @MethodName getTimestampBySecondString
	 * @Description 获取传入指定字符串对应的描述转换成时间戳，字符串为北京1970年1月1日08:00:00开始的绝对秒数
	 * @author CaiShunda
	 * @data 2016-7-8 下午10:37:43
	 * @param intime
	 * @return
	 */
	public Timestamp getTimestampBySecondString(String intime) {
		long time = Long.parseLong(intime);
		time = time * 1000;
		Timestamp timestamp = new Timestamp(time);
		return timestamp;
	}

	/**
	 * @MethodName: getCalendarFromLongString
	 * @Description: 从毫秒字符串获取日期
	 * @author CaiShunda
	 * @date 2017-1-9 上午10:10:36
	 * @param longTime
	 * @return
	 */
	public 	Calendar getCalendarFromMillisString(String longTime){
		String time = longTime.trim();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(Long.parseLong(time));
		return cal;
	}

	/**
	 * @MethodName: getFormatStringFromMillisString
	 * @Description: 将毫秒字符串转化成可视化字符串
	 * @author CaiShunda
	 * @date 2017-1-9 上午10:12:52
	 * @param longTime
	 * @return
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
	 * @MethodName getSecondFromTimestamp
	 * @Description 获取北京1970年1月1日08:00:00开始的绝对秒数,
	 * @author CaiShunda
	 * @data 2016-7-10 下午6:40:18
	 * @param timestamp
	 * @return 返回null表示转化不成功
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
	 * @MethodName getSecondFromDate
	 * @Description 通过Date获取秒数
	 * @author CaiShunda
	 * @data 2016-7-25 下午1:54:21
	 * @param date
	 * @return
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
	 * @MethodName getSecondFromCalendar
	 * @Description 通过Calendar获取秒数
	 * @author CaiShunda
	 * @data 2016-7-25 下午5:37:22
	 * @param cal
	 * @return
	 */
	public String getSecondFromCalendar(Calendar cal) {
		long time = cal.getTimeInMillis();
		time = time / 1000;
		return String.valueOf(time);
	}

	/**
	 * @MethodName getSecondCurrent
	 * @Description 当前时间秒数
	 * @author CaiShunda
	 * @data 2016-7-25 下午1:54:10
	 * @return
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
	 * @MethodName getNow
	 * @Description 获取此时的日期
	 * @author CaiShunda
	 * @data 2016-7-21 下午1:22:14
	 * @return
	 */
	public Calendar getNow() {
		Calendar now = Calendar.getInstance();
		return now;
	}

	/**
	 * @MethodName getNow
	 * @Description 获取此时的日期
	 * @author CaiShunda
	 * @data 2016-7-21 下午1:22:14
	 * @return
	 */
	public String getNowString() {
		Calendar now = Calendar.getInstance();
		return getFormatDate(now);
	}

	/**
	 * @MethodName getTheDayBefore
	 * @Description 获取cal对应日期的前period的日期
	 * @author CaiShunda
	 * @data 2016-7-21 下午1:23:42
	 * @param cal
	 * @param period
	 * @return
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
	 * @MethodName getMillisToNext
	 * @Description 忽略date的年月日，假设date表示的时间是1：00：00，则返回值表示到下一个凌晨一点需要多少毫秒
	 * @author CaiShunda
	 * @data 2016-7-10 下午6:43:19
	 * @param date
	 *            例子1：30：00
	 * @return
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
	 * @MethodName getFormatDate
	 * @Description 根据传入时间获取指定格式的时间字符串
	 * @author CaiShunda
	 * @data 2016-7-8 下午10:39:41
	 * @param date
	 * @return
	 */
	public String getFormatDate(Date date) {
		return format.format(date);
	}

	public String getFormatDate(Timestamp timestamp) {
		return format.format(timestamp);
	}

	/**
	 * @MethodName getFormatDate
	 * @Description 根据传入时间获取指定格式的时间字符串
	 * @author CaiShunda
	 * @data 2016-7-8 下午10:40:12
	 * @param calendar
	 * @return
	 */
	public String getFormatDate(Calendar calendar) {
		return format.format(calendar.getTime());
	}

	/**
	 * @MethodName getFormatDate
	 * @Description 根据传入的北京1970年1月1日08:00:00开始的绝对毫秒数转换成指定格式的日期字符串
	 * @author CaiShunda
	 * @data 2016-7-8 下午10:40:18
	 * @param timeInMilllis
	 * @return
	 */
	public String getFormatDate(Long timeInMilllis) {
		calendar.setTimeInMillis(timeInMilllis);
		return format.format(calendar.getTime());
	}

	/**
	 * @MethodName getTimeInMilllis
	 * @Description 根据传入时间转换成毫秒数
	 * @author CaiShunda
	 * @data 2016-7-8 下午10:41:14
	 * @param calendar
	 * @return
	 */
	public String getTimeInMilllis(Calendar calendar) {
		return String.valueOf(calendar.getTimeInMillis());
	}

	/**
	 * @MethodName getCalendarFromyyyyMMdd
	 * @Description 根据传入时间字符串转成yyyyMMdd格式的日期
	 * @author CaiShunda
	 * @data 2016-7-8 下午10:41:39
	 * @param dateStr
	 * @return
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
	 * @Title: getDateByFormat
	 * @Description: 根据设定格式转换String为Date
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public Date getDateByFormat(String dateStr) throws ParseException {
		Date date = format.parse(dateStr);
		return date;
	}

	/**
	 * @Title: getCalendarByFormat
	 * @Description: 根据设定格式转换String为Calendar
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public Calendar getCalendarByFormat(String dateStr) throws ParseException {
		Date date = format.parse(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * @MethodName getCalendarFromyyyyMMdd
	 * @Description 根据传入的dateStr例如20160606和hour、minute、second获得对应准确的Calendar实例
	 * @author QiHaiyang
	 * @data 2016年6月12日 下午4:37:48
	 * @param dateStr
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
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
	 * @MethodName getTimeInSecond
	 * @Description 根据传入日期转换成北京1970年1月1日08:00:00开始的绝对秒数
	 * @author CaiShunda
	 * @data 2016-7-8 下午10:42:19
	 * @param date
	 * @return
	 */
	public String getTimeInSecond(Date date) {
		long currentMillis = date.getTime();
		currentMillis = currentMillis / 1000;
		String currentSecond = String.valueOf(currentMillis);
		return currentSecond;
	}

	/**
	 * @MethodName getDaysBetweenDate
	 * @Description 计算给出的两个日期之间的天数，向上取整，入2016/07/17-8:00和2016/07/19-0:00相差的天数为2天
	 * @author LP
	 * @data 2016年7月28日 上午11:54:45
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int getDaysBetweenDate(Date startTime, Date endTime) {
		double ddays = (((double) endTime.getTime() - (double) startTime
				.getTime()) / 86400000);
		double days = Math.ceil(ddays);
		return (int) days;
	}

	/**
	 * @MethodName getVisibleTimeWithMillis
	 * @Description 根据传入的北京1970年1月1日08:00:00开始的绝对毫秒数转换成中文的可视化字符串
	 * @author CaiShunda
	 * @data 2016-7-8 下午10:43:21
	 * @param time
	 * @return
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
	 * @MethodName getPeriodOfDay
	 * @Description 获取daynum的间隔，毫秒
	 * @author CaiShunda
	 * @data 2016-7-10 下午7:13:00
	 * @param daynum
	 * @return
	 */
	public long getPeriodOfDay(int daynum) {
		long aDay = 24 * 3600000;
		return aDay * daynum;
	}

	/**
	 * @MethodName getNextDate
	 * @Description 获取date的下一天
	 * @author CaiShunda
	 * @data 2016-7-21 下午8:51:51
	 * @param date
	 * @return
	 */
	public Date getNextDate(Date date) {
		Calendar nextDate = Calendar.getInstance();
		nextDate.setTime(date);
		nextDate.add(Calendar.DAY_OF_MONTH, 1);
		return nextDate.getTime();
	}

	/**
	 * @MethodName getDateByAmount
	 * @Description 获取date加上amount天的date，可为负数
	 * @author CaiShunda
	 * @data 2016-7-24 下午3:51:29
	 * @param date
	 * @param amount
	 * @return
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

	public static void main(String[] args) {
		DateUtil format = new DateUtil();
		// String tableDate = "20160612";
		// Calendar startCalendar =
		// format.getCalendarFromyyyyMMdd(tableDate,22,59,59);
		// startCalendar.add(Calendar.DATE, -181);
		// System.out.println(format.getFormatDate(startCalendar));
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// long t = System.currentTimeMillis();
		// t = t / (1000 * 3600 * 24) * (1000 * 3600 * 24);
		// System.out.println(sdf.format(new Timestamp(t)));
		// t = System.currentTimeMillis();
		// t = t / (1000 * 3600 * 24) * (1000 * 3600 * 24) -
		// TimeZone.getDefault().getRawOffset();
		// System.out.println(sdf.format(new Timestamp(t)));
		Calendar now = format.getNow();
		System.out.println(format.getFormatDate(now));
		Calendar before = format.getTheDayBefore(now, 2);
		System.out.println(format.getFormatDate(before));
	}

}
