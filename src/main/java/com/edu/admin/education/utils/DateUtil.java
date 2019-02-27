package com.edu.admin.education.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.Weeks;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public static final String date_pattern = "yyyy-MM-dd";

    public static final String time_pattern = "yyyy-MM-dd HH:mm:ss";

    public static final String miroSeconds_pattern = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String getDate_pattern_dot = "yyyy.MM.dd";

    public static final String time_pattern_dot = "yyyy.MM.dd HH:mm:ss";

    public static final String time_pattern_min = "yyyy.MM.dd HH:mm";

    public static final String date_s_pattern = "yyyyMMdd";

    public static final String date_st_pattern = "yyyy/MM/dd HH:mm:ss";

    public static final String date_st_pattern_yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static final String date_st_miroSeconds_pattern = "yyyyMMddHHmmssSSS";

    public static final String date_st_pattern_yyyyMMddHHmm = "yyyyMMddHHmm";

    public static final int DIFFNOWDATE_EQUAL = 3;

    public static final int CONVERT_TO_SECOND = 3600;

    public static final String TIME_PATTERN_MINUTE = "yyyy-MM-dd HH:mm";

    public static final String month_pattern = "yyyy-MM";

    public static final String month_s_pattern = "yyyyMM";

    public static final String BEGIN_TIME_FOR_DAY = "00:00:00";

    public static final String END_TIME_FOR_DAY = "23:59:59";

    public static String formatDate(Date date)
    {
        if (date == null)
            return null;
        return new SimpleDateFormat(date_pattern).format(date);
    }

    public static String formatSimDate(Date date)
    {
        if (date == null)
            return null;
        return new SimpleDateFormat(date_s_pattern).format(date);
    }

    public static String formatDateDot(Date date)
    {
        if (date == null)
            return null;
        return new SimpleDateFormat(getDate_pattern_dot).format(date);
    }

    public static String format(String pattern, Date date)
    {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static void main(String[] args)
    {
//        String offlineTime = DateUtil.parseQqDate("2017-11-07 00:00:00", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm", "HH:mm");
//        System.out.println(offlineTime);
        // System.out.printf(DateUtil.getNowDate().getTime() + "");
//        System.out.println(format(date_st_pattern, getNowDate(date_st_pattern)));
//        System.out.println(getZeroTime(1487809830000l));
        System.out.println(DateUtil.getNext_month());
    }

    public static String formatTime(Date date)
    {
        if (date == null)
            return "";
        return getDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static Date parseDate(String datestr)
    {
        try
        {
            return new SimpleDateFormat(date_pattern).parse(datestr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static Date parseDate(String datestr, String pattern)
    {
        try
        {
            return new SimpleDateFormat(pattern).parse(datestr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static Date parseDateDot(String datestr)
    {
        try
        {
            return new SimpleDateFormat(getDate_pattern_dot).parse(datestr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return null;
    }


    public static Date parseDateDots(String datestr)
    {
        try
        {
            return new SimpleDateFormat(date_pattern).parse(datestr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return null;
    }


    public static Date parseTime(String time)
    {
        Date result = null;
        if (time == null || "".equals(time))
        {
            return null;
        }

        try
        {
            result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public static Date parseTimeDot(String time)
    {
        Date result = null;
        if (time == null || "".equals(time))
        {
            return null;
        }

        try
        {
            result = new SimpleDateFormat(time_pattern_dot).parse(time);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public static Date add(Date date, int mount, int field)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, mount);
        return cal.getTime();
    }

    /**
     * date 2 - date1
     *
     * @param date1
     * @param date2
     * @param type {@link Calendar}
     * @return
     */
    public static int diff(Date date1, Date date2, int type)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        int d1 = cal.get(type);
        cal.setTime(date2);
        return cal.get(type) - d1;
    }

    /**
     * date 2 - date1
     *
     * @param date1-小
     * @param date2-大
     * @deprecated 跨年计算天数时返回值不正确，请不要使用该方法
     */
    @Deprecated
    public static int diffByDay(String date1, String date2)
    {
        int type = Calendar.DAY_OF_YEAR;
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.parseDateDot(date1));
        int d1 = cal.get(type);
        int year1 = cal.get(Calendar.YEAR);
        cal.setTime(DateUtil.parseDateDot(date2));
        int year2 = cal.get(Calendar.YEAR);
        if (year1 == year2)
        {
            return cal.get(type) - d1;
        }
        else
        {
            return (year2 - year1) * 365 + cal.get(type) - d1 + 2;
        }
    }

    /**
     * 计算date2与date1相隔天数
     * <pre>
     *    date2         date1
     * 2017-08-05 与 2017-08-04 相隔    1 天
     * 2017-08-05 与 2017-08-05 相隔    0 天
     * 2017-08-05 与 2015-08-05 相隔  731 天
     * 2017-08-05 与 2017-08-06 相隔   -1 天
     * 2017-08-05 与 2019-08-05 相隔 -730 天
     * </pre>
     *
     * @param date1     较小日期
     * @param date2     较大日期
     * @param connector 日期格式的连接符："-", ".", "", "/"等
     * @return 相隔天数，date2小于date1时返回负数。<b>注意：格式解析失败将返回0</b>
     */
    public static long diffByDay(String date1, String date2, String connector) {
        StringBuilder sb = new StringBuilder(date_s_pattern);
        sb.insert(4, connector);
        sb.insert(7, connector);
        String datePattern = sb.toString();
        Date d1 = parseDate(date1, datePattern);
        Date d2 = parseDate(date2, datePattern);
        long daysBetween = 0;
        if (d1 != null && d2 != null) {
            daysBetween = (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24);
        }
        return daysBetween;
    }

    /**
     * date自然周中的那一天
     *
     * @param date 时间YYYY.MM.DD
     * @return
     */
    public static int dayInWeek(String date)
    {
        int type = Calendar.DAY_OF_WEEK;
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.parseDateDot(date));
        int d1 = cal.get(type);
        return d1;
    }

    /**
     * date自然周中的那一天
     *
     * @param date
     * @return
     */
    public static String dayInWeek(Date date)
    {
        int type = Calendar.DAY_OF_WEEK;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int d = cal.get(type);
        String dayOfWeek = "";
        switch (d)
        {
            case 1:
                dayOfWeek = "周日";
                break;
            case 2:
                dayOfWeek = "周一";
                break;
            case 3:
                dayOfWeek = "周二";
                break;
            case 4:
                dayOfWeek = "周三";
                break;
            case 5:
                dayOfWeek = "周四";
                break;
            case 6:
                dayOfWeek = "周五";
                break;
            case 7:
                dayOfWeek = "周六";
                break;
            default:
                dayOfWeek = "";
        }
        return dayOfWeek;
    }

    /**
     * date自然月中的那一天
     *
     * @param date 时间YYYY.MM.DD
     * @return
     */
    public static int dayInMonth(String date)
    {
        int type = Calendar.DAY_OF_MONTH;
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.parseDateDot(date));
        int d1 = cal.get(type);
        return d1;
    }

    /**
     * 一个月最大的一天
     *
     * @param date 时间YYYY.MM.DD
     * @return int 天数
     */
    public static int maxDayMonth(String date)
    {
        int type = Calendar.DATE;
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(date.substring(0, 4));
        cal.set(Calendar.YEAR, year);
        int month = Integer.parseInt(date.substring(5, 7).replace("0", "")) - 1;
        cal.set(Calendar.MONTH, month);
        int d1 = cal.getActualMaximum(type);
        return d1;
    }

    /**
     * 一年中有多少个自然周
     *
     * @param date 时间YYYY.12.31
     *            <p/>
     *            处于一年中第几个自然周
     * @param date 时间YYYY.MM.DD
     * @return
     */
    public static int yearMoreWeek(String date)
    {
        Date time = DateUtil.addDay(date, 1);
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setMinimalDaysInFirstWeek(7);
        cal.setTime(time);
        int d1 = cal.get(Calendar.WEEK_OF_YEAR);
        return d1;
    }

    /**
     * 根据传递过来的日期，转换为对应的 年、所在周的格式
     *
     * @param startWeekDay
     * @return 返回 201601
     */
    public static int yearofWeekStr(String startWeekDay)
    {
        Date time = DateUtil.addDay(startWeekDay, 1);
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setMinimalDaysInFirstWeek(7);
        cal.setTime(time);
        String weekNumber = cal.get(Calendar.WEEK_OF_YEAR) + "";
        String week = startWeekDay.substring(0, 4) + weekNumber;
        if (weekNumber.equals("52") && startWeekDay.substring(5, 7).equals("01"))
        {
            week = String.valueOf(Integer.parseInt(startWeekDay.substring(0, 4)) - 1) + weekNumber;
        }
        if (weekNumber.length() == 1)
        {
            week = startWeekDay.substring(0, 4) + "0" + weekNumber;
        }

        return Integer.parseInt(week);
    }

    public static String timeStr(long time)
    {
        Date date = new Date(time);
        return getDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String timeStr2(long time)
    {
        Date date = new Date(time);
        return getDateFormat(date_pattern).format(date);
    }

    public static SimpleDateFormat getDateFormat(String pattern)
    {
        SimpleDateFormat sim = new SimpleDateFormat(pattern);
        sim.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        // sim.setTimeZone(TimeZone.getTimeZone("Asia/ShangHai"));
        return sim;
    }

    /**
     * 字符串转LONG
     *
     * @param time
     * @return
     */
    public static Long strTimeChangeLong(String time)
    {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calBegin = new GregorianCalendar();
        try
        {
            calBegin.setTime(format.parse(time));
        }
        catch (ParseException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        long beginTime = calBegin.getTimeInMillis();
        return beginTime;
    }

    /**
     * 字符串转LONG
     *
     * @param time
     * @return
     */
    public static Long strTimeChangeLongs(String time)
    {
        DateFormat format = new SimpleDateFormat(date_pattern);
        Calendar calBegin = new GregorianCalendar();
        try
        {
            calBegin.setTime(format.parse(time));
        }
        catch (ParseException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        long beginTime = calBegin.getTimeInMillis();
        return beginTime;
    }


    /**
     * 取得当前日期
     *
     * @return Date:当前日期
     */
    public static Date getNowDate()
    {
        Date now = null;
        SimpleDateFormat dateFormat = null;
        try
        {
            dateFormat = new SimpleDateFormat(getDate_pattern_dot);
            now = dateFormat.parse(dateFormat.format(new Date()));
        }
        catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        return now;
    }

    /**
     * 取得当前日期包含时分秒
     *
     * @return Date:当前日期包含时分秒
     */
    public static Date getNowDateHHmmss()
    {
        Date now = null;
        SimpleDateFormat dateFormat = null;
        try
        {
            dateFormat = new SimpleDateFormat(time_pattern);
            now = dateFormat.parse(dateFormat.format(new Date()));
        }
        catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        return now;
    }

    /**
     * 取得当前日期
     *
     * @return Date:当前日期
     */
    public static Date getNowDate(String timeType)
    {
        Date now = null;
        SimpleDateFormat dateFormat = null;
        try
        {
            dateFormat = new SimpleDateFormat(timeType);
            now = dateFormat.parse(dateFormat.format(new Date()));
        }
        catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        return now;
    }

    public static Date getDate(Date date, String pattern)
    {
        Date result = null;
        SimpleDateFormat dateFormat = null;
        try
        {
            dateFormat = new SimpleDateFormat(pattern);
            result = dateFormat.parse(dateFormat.format(date));
        }
        catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        return result;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date 日期
     * @param dateType 类型
     * @param amount 数值
     * @return 计算后日期
     */
    public static Date addInteger(Date date, int dateType, int amount)
    {
        Date myDate = null;
        if (date != null)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * long转String
     *
     * @param millSec
     * @return
     */
    public static Date transferLongToDate(Long millSec)
    {
        Date date = null;
        SimpleDateFormat dateFormat = null;
        try
        {
            dateFormat = new SimpleDateFormat(getDate_pattern_dot);
            date = dateFormat.parse(dateFormat.format(new Date(millSec)));
        }
        catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        return date;
    }

    /**
     * long转String
     *
     * @param millSec
     * @return
     */
    public static Date transferLongToDate(Long millSec, String pattern)
    {
        Date date = null;
        SimpleDateFormat dateFormat = null;
        try
        {
            dateFormat = new SimpleDateFormat(pattern);
            date = dateFormat.parse(dateFormat.format(new Date(millSec)));
        }
        catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        return date;
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date 日期
     * @param monthAmount 增加数量。可为负数
     * @return 增加月份后的日期字符串
     */
    public static Date addMonth(Date date, int monthAmount)
    {
        return addInteger(date, Calendar.MONTH, monthAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date 日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加月份后的日期字符串
     */
    public static Date addDay(String date, int dayAmount)
    {
        return addInteger(DateUtil.parseDateDot(date), Calendar.DAY_OF_YEAR, dayAmount);
    }
    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date 日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加月份后的日期字符串
     */
    public static Date addDays(String date, int dayAmount)
    {
        return addInteger(DateUtil.parseDateDots(date), Calendar.DAY_OF_YEAR, dayAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date 日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加月份后的日期字符串
     */
    public static Date addDay(Date date, int dayAmount)
    {
        return addInteger(date, Calendar.DAY_OF_YEAR, dayAmount);
    }

    /**
     * date1 与 date2 中包含独立的天数
     *
     * @param date1-小
     * @param date2-大
     * @return
     */
    public static int containAloneDays(String date1, String date2)
    {
        return diffByDay(date1, date2) + 1;
    }

    /**
     * date1 与 date2 中包含的独立周数
     *
     * @param date1-小
     * @param date2-大
     * @return
     */
    public static int containAloneWeeks(String date1, String date2)
    {
        if (firstDayOfCurrentWeek(date1).equals(firstDayOfCurrentWeek(date2)))
        {
            return 1;
        }
        else
        {
            DateTime dt1 = DateTime.parse(lastDayOfCurrentWeek(date1), DateTimeFormat.forPattern("yyyy.MM.dd"));
            DateTime dt2 = DateTime.parse(firstDayOfCurrentWeek(date2), DateTimeFormat.forPattern("yyyy.MM.dd"));
            return Weeks.weeksBetween(dt1, dt2).getWeeks() + 2;
        }
    }

    /**
     * 当前周的第一天
     *
     * @param date
     * @return
     */
    public static String firstDayOfCurrentWeek(String date)
    {
        return formatDateDot(addDay(date, 1 - dayInWeek(date)));
    }

    /**
     * 当前周的最后一天
     *
     * @param date
     * @return
     */
    public static String lastDayOfCurrentWeek(String date)
    {
        return formatDateDot(addDay(date, 7 - dayInWeek(date)));
    }

    /**
     * 下一周的第一天
     *
     * @param date
     * @return
     */
    public static String firstDayOfNextWeek(String date)
    {
        return formatDateDot(addDay(date, 8 - dayInWeek(date)));
    }

    /**
     * 下一周的最后一天
     *
     * @param date
     * @return
     */
    public static String lastDayOfNextWeek(String date)
    {
        return formatDateDot(addDay(date, 14 - dayInWeek(date)));
    }

    /**
     * 当前日期到当前周最后日期的日期范围(不完整周,向后)
     *
     * @param date
     * @return
     */
    public static String currentWeekRangeTail(String date)
    {
        date = date.substring(0, 10);
        return date + " - " + lastDayOfCurrentWeek(date);
    }

    /**
     * 当前日期到当前周最后日期的日期范围(不完整周,向前)
     *
     * @param date
     * @return
     */
    public static String currentWeekRangeHead(String date)
    {
        date = date.substring(0, 10);
        return firstDayOfCurrentWeek(date) + " - " + date;
    }

    /**
     * 当前周的日期范围
     *
     * @param date
     * @return
     */
    public static String currentWeekRange(String date)
    {
        date = date.substring(0, 10);
        return firstDayOfCurrentWeek(date) + " - " + lastDayOfCurrentWeek(date);
    }

    /**
     * 下一周的日期范围
     *
     * @param date
     * @return
     */
    public static String nextWeekRange(String date)
    {
        date = date.substring(0, 10);
        return firstDayOfNextWeek(date) + " - " + lastDayOfNextWeek(date);
    }

    /**
     * 增加i周后的日期范围
     *
     * @param date
     * @return
     */
    public static String addWeekRange(String date, int n)
    {
        date = date.substring(0, 10);
        return firstDayOfNextNWeek(date, n) + " - " + lastDayOfNextNWeek(date, n);
    }

    /**
     * 下n周的第一天
     *
     * @param date
     * @param n
     * @return
     */
    public static String firstDayOfNextNWeek(String date, int n)
    {
        return formatDateDot(addDay(date, 7 * n + 1 - dayInWeek(date)));
    }

    /**
     * 下n周的最后一天
     *
     * @param date
     * @param n
     * @return
     */
    public static String lastDayOfNextNWeek(String date, int n)
    {
        return formatDateDot(addDay(date, 7 * (n + 1) - dayInWeek(date)));
    }

    /**
     * 把week封装成日期范围
     *
     * @param date 格式：201623（2016年第23周）
     * @return
     */
    public static String parseToWeekRange(String date)
    {
        int year = Integer.parseInt(date.substring(0, 4));
        int week = Integer.parseInt(date.substring(4, 6));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
        Date weekStart = calendar.getTime();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
        Date weekEnd = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(weekStart) + " - " + sdf.format(weekEnd);
    }

    /**
     * date1 与 date2 中包含独立的月数
     *
     * @param date1-小
     * @param date2-大
     * @return
     */
    public static int containAloneMonths(String date1, String date2)
    {
        date1 = date1.substring(0, date1.lastIndexOf("."));
        date2 = date2.substring(0, date2.lastIndexOf("."));
        DateTime dt1 = DateTime.parse(date1, DateTimeFormat.forPattern("yyyy.MM"));
        DateTime dt2 = DateTime.parse(date2, DateTimeFormat.forPattern("yyyy.MM"));
        return Months.monthsBetween(dt1, dt2).getMonths() + 1;
    }

    /**
     * 当前周的第一天
     *
     * @param date
     * @return
     */
    public static String firstDayOfCurrentMonth(String date)
    {
        return date.substring(0, 8) + "01";
    }

    /**
     * 当前周的最后一天
     *
     * @param date
     * @return
     */
    public static String lastDayOfCurrentMonth(String date)
    {
        String day = String.valueOf(maxDayMonth(date));
        if (day.length() == 2)
        {
            return date.substring(0, 8) + day;
        }
        else
        {
            return date.substring(0, 8) + "0" + day;
        }
    }

    /**
     * 下一周的第一天
     *
     * @param date
     * @return
     */
    public static String firstDayOfNextMonth(String date)
    {
        return firstDayOfCurrentMonth(formatDateDot(addMonth(parseDateDot(date), 1)));
    }

    /**
     * 下一周的最后一天
     *
     * @param date
     * @return
     */
    public static String lastDayOfNextMonth(String date)
    {
        return lastDayOfCurrentMonth(formatDateDot(addMonth(parseDateDot(date), 1)));
    }

    /**
     * 下n周的第一天
     *
     * @param date
     * @param n
     * @return
     */
    public static String firstDayOfNextNMonth(String date, int n)
    {
        return firstDayOfCurrentMonth(formatDateDot(addMonth(parseDateDot(date), n)));
    }

    /**
     * 下n周的最后一天
     *
     * @param date
     * @param n
     * @return
     */
    public static String lastDayOfNextNMonth(String date, int n)
    {
        return lastDayOfCurrentMonth(formatDateDot(addMonth(parseDateDot(date), n)));
    }

    /**
     * 当前日期到当前月最后日期的日期范围(不完整月,向后)
     *
     * @param date
     * @return
     */
    public static String currentMonthRangeTail(String date)
    {
        return date + " - " + lastDayOfCurrentMonth(date);
    }

    /**
     * 当前日期到当前月最后日期的日期范围(不完整月,向前)
     *
     * @param date
     * @return
     */
    public static String currentMonthRangeHead(String date)
    {
        return firstDayOfCurrentMonth(date) + " - " + date;
    }

    /**
     * 当前月范围
     *
     * @param date
     * @return
     */
    public static String currentMonthRange(String date)
    {
        date = date.substring(0, 7) + ".01";
        return firstDayOfCurrentMonth(date) + " - " + lastDayOfCurrentMonth(date);
    }

    /**
     * 增加i月后的日期范围
     *
     * @param date
     * @return
     */
    public static String addMonthRange(String date, int n)
    {
        return firstDayOfNextNMonth(date, n) + " - " + lastDayOfNextNMonth(date, n);
    }

    /**
     * 下个月的第一天
     *
     * @param date YYYY.MM.DD
     * @return YYYY.MM.DD
     */
    public static String firstMonthDay(String date)
    {
        // 自然月区域
        int inDay = DateUtil.dayInMonth(date);
        String endDay =
                DateUtil.format(DateUtil.getDate_pattern_dot, DateUtil.addDay(date, DateUtil.maxDayMonth(date) - inDay));
        return DateUtil.format(DateUtil.getDate_pattern_dot, DateUtil.addDay(endDay, 1));
    }

    /**
     * 下周的第一天
     *
     * @param date YYYY.MM.DD
     * @return YYYY.MM.DD
     */
    public static String firstWeekDay(String date)
    {
        // 自然周区域
        int weekInDay = DateUtil.dayInWeek(date);
        String endDay = DateUtil.format(DateUtil.getDate_pattern_dot, DateUtil.addDay(date, 7 - weekInDay));
        return DateUtil.format(DateUtil.getDate_pattern_dot, DateUtil.addDay(endDay, 1));
    }


    /**
     * 时间段格式设置
     *
     * @param time 秒
     * @return twentyFourHour 12小时制
     */
    public static String twentyFourHour(Integer time)
    {
        String hour = String.valueOf(time / 3600);
        hour = hour.length() == 2 ? hour : "0" + hour;
        String minute = String.valueOf((time % 3600) / 60);
        minute = minute.length() == 2 ? minute : "0" + minute;
        String second = String.valueOf((time % 3600) % 60);
        second = second.length() == 2 ? second : "0" + second;
        String twentyFourHour = hour + ":" + minute + ":" + second;
        return twentyFourHour;
    }

    /**
     * 时间段格式设置
     *
     * @param time 秒
     * @return ta 00:00~00:59
     */
    public static String convStringToHour(Integer time)
    {
        String ta = "";
        if (time < 10)
        {
            ta = "0" + String.valueOf(time) + ":00~0" + String.valueOf(time) + ":59";
        }
        else
        {
            ta = String.valueOf(time) + ":00~" + String.valueOf(time) + ":59";
        }
        return ta;
    }

    /**
     * 获取今天第1秒时间戳
     *
     * @return 时间戳
     */
    public static Long firstSecondToday()
    {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today.getTimeInMillis();
    }

    /**
     * 日期格式转换
     *
     * @param datestr YYYYmmdd
     * @param type ./-
     * @return Date
     */
    public static Date parseDateDot(String datestr, String type)
    {
        try
        {
            StringBuffer str = new StringBuffer(datestr);
            str.insert(4, type);
            str.insert(7, type);
            if (type.equals("."))
            {
                return new SimpleDateFormat(getDate_pattern_dot).parse(str.toString());
            }
            if (type.equals("-"))
            {
                return new SimpleDateFormat(date_pattern).parse(str.toString());
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 与当前日期比较
     *
     * @param dateStr 比较时间
     * @return 1：大于当前日期，2：小于当前日期，3：等于当前日期
     */
    public static int diffNowDate(String dateStr) {
        try {
            Date dEndDate = new Date();
            if (!dateStr.contains(".") && !dateStr.contains("-")) {
                StringBuilder str = new StringBuilder(dateStr);
                str.insert(4, ".");
                str.insert(7, ".");
                dEndDate = DateUtil.parseDateDot(str.toString());
            } else if (dateStr.contains(".")) {
                dEndDate = DateUtil.parseDateDot(dateStr);
            } else if (dateStr.contains("-")) {
                dEndDate = DateUtil.parseDate(dateStr);
            }

            Date nowDate = DateUtil.getNowDate();
            if (dEndDate != null && nowDate != null) {
                if (dEndDate.getTime() > nowDate.getTime()) {
                    // 日期大于当前日期
                    return 1;
                } else if (dEndDate.getTime() < nowDate.getTime()) {
                    // 日期小于当前日期
                    return 2;
                } else { // dEndDate.getTime() == nowDate.getTime()
                    // 日期等于当前日期
                    return 3;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 与当前日期比较
     *
     * @param datestr 比较时间
     * @return 1：大于当前日期，2：小于当前日期，3：等于当前日期
     */
    public static String dateStr(String datestr, String type)
    {
        StringBuffer str = new StringBuffer(datestr);
        if (datestr.indexOf(".") == -1 && datestr.indexOf("-") == -1)
        {
            str.insert(4, type);
            str.insert(7, type);
        }
        return str.toString();
    }

    /**
     * 与当前日期比较
     *
     * @param datestr 比较时间
     * @return 1：大于当前日期，2：小于当前日期，3：等于当前日期
     */
    public static Date dateD(String datestr, String type)
    {
        StringBuffer str = new StringBuffer(datestr);
        Date date = new Date();
        if (datestr.indexOf(".") == -1 && datestr.indexOf("-") == -1)
        {
            str.insert(4, type);
            str.insert(7, type);
            if (datestr.indexOf(".") != -1)
            {
                date = DateUtil.parseDateDot(datestr);
            }
            else if (datestr.indexOf("-") != -1)
            {
                date = DateUtil.parseDate(datestr);
            }
        }
        return date;
    }

    /**
     * 判断今天、昨天、前天
     *
     * @param date 格式要求2016-08-08 08:08:08
     * @return 今天、昨天、前台 否则返回日期
     */
    public static String parseDateToday(Date date)
    {
        String ret = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long create = date.getTime();
        Calendar now = Calendar.getInstance();
        long ms =
                1000 * (now.get(Calendar.HOUR_OF_DAY) * 3600 + now.get(Calendar.MINUTE) * 60 + now.get(Calendar.SECOND));// 毫秒数
        long ms_now = now.getTimeInMillis();
        if (ms_now - create < ms)
        {
            ret = "今天";
        }
        else if (ms_now - create < (ms + 24 * 3600 * 1000))
        {
            ret = "昨天";
        }
        else if (ms_now - create < (ms + 24 * 3600 * 1000 * 2))
        {
            ret = "前天";
        }
        else
        {
            ret = sdf.format(date);
        }
        return ret;

    }

    /**
     * 判断输入日期是：今天、昨天，若比昨天还早且在本周内，则返回周几，若非本周内，则返回年月日
     *
     * @param dateStr 待判断的日期字符串
     * @param inputFormat 带判断日期字符串的格式
     * @param outPutFormat 要输出的日期字符串的格式
     * @param subFormat 显示为今天、昨天或周几时 带的时分秒格式，若为空，则不带时分秒
     * @return System.out.println(DateUtil.parseQqDate("2016-10-21 22:10:10", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm"
     *         ,"HH:mm"));
     */
    public static String parseQqDate(String dateStr, String inputFormat, String outPutFormat, String subFormat)
    {
        String time00 = "00:00:00";
        if (dateStr.indexOf(time00) != -1) {
            // 解决 当天0点被计算成昨天的bug
            dateStr = dateStr.replaceAll("00:00:00","00:00:01");
        }
        String ret = "";
        SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
        Date date = DateUtil.parseDate(dateStr, inputFormat);
        long create = date.getTime();
        Calendar now = Calendar.getInstance();
        long ms =
                1000 * (now.get(Calendar.HOUR_OF_DAY) * 3600 + now.get(Calendar.MINUTE) * 60 + now.get(Calendar.SECOND));// 毫秒数

        long ms_now = now.getTimeInMillis();

        String subStr = "";
        if (StringUtils.isNotEmpty(subFormat))
        {
            subStr = " " + DateUtil.format(subFormat, date);
        }
        System.out.println(ms_now - create);
        if (ms_now - create < ms)
        {
            ret = "今天" + subStr;
        }
        else if (ms_now - create < (ms + 24 * 3600 * 1000))
        {
            ret = "昨天" + subStr;
        }
        else
        {
            if (DateUtil.isThisWeek(date))
            {
                ret = DateUtil.dayInWeek(date) + subStr;
            }
            else
            {
                sdf = new SimpleDateFormat(outPutFormat);
                ret = sdf.format(date);
            }
        }
        return ret;

    }

    /**
     * 判断选择的日期是否是本周
     *
     * @param date
     * @return
     */
    public static boolean isThisWeek(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(date);
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if (paramWeek == currentWeek)
        {
            return true;
        }
        return false;
    }

    /**
     * 与当前时间相差多少秒
     *
     * @param date yyyy-mm-dd hh:mm:ss
     * @return
     */
    public static Long diffNoeDate(String date)
    {
        Date a = new Date();
        Date b = parseDate(date, time_pattern);
        Long interval = (a.getTime() - b.getTime()) / 1000;
        return interval;
    }

    /**
     * 取得当前日期(yyyyMMddHHmmss)
     *
     * @return String:当前日期
     */
    public static String getNowDate_yyyyMMddHHmmss()
    {
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat(date_st_pattern_yyyyMMddHHmmss);
        String now = dateFormat.format(new Date());
        return now;
    }


    /**
     * 取得指定格式当前日期
     *
     * @param pattern:指定格式
     * @return String:当前日期
     */
    public static String getFormatNowDate(String pattern)
    {
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat(pattern);
        String now = dateFormat.format(new Date());
        return now;
    }
    /**
     * 取得当前日期(yyyyMMddHHmmss)
     *
     * @return String:当前日期
     */
    public static String getNowDate_yyyyMMdd()
    {
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat(date_s_pattern);
        String now = dateFormat.format(new Date());
        return now;
    }
    /**
     * 取得当前日期(yyyy-MM-dd)
     *
     * @return String:当前日期
     */
    public static String getNowDate_yyyy_MM_dd()
    {
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat(date_pattern);
        String now = dateFormat.format(new Date());
        return now;
    }

    /**
     * 取某天零点时间
     * @param time
     * @return
     */
    public static Long getZeroTime(long time){
        String dateStr = formatDate(new Date(time));
        return DateUtil.parseDate(dateStr + " 00:00:00", DateUtil.time_pattern).getTime();
    }

    /**
     * 取某天最后一分钟时间戳
     * @param time
     * @return
     */
    public static Long getLastTime(long time){
        String dateStr = formatDate(new Date(time));
        return DateUtil.parseDate(dateStr + " 23:59:59", DateUtil.time_pattern).getTime();
    }

    /**
     * 取得当前日期(yyyyMMddHHmm)
     *
     * @return String:当前日期
     */
    public static String getNowDate_yyyyMMddHHmm()
    {
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat(date_st_pattern_yyyyMMddHHmm);
        String now = dateFormat.format(new Date());
        return now;
    }

    /**
     * 取得当前日期(yyyyMMddHHmm)
     *
     * @return String:当前日期
     */
    public static String getNowDate_date_st_partten()
    {
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat(date_st_pattern);
        String now = dateFormat.format(new Date());
        return now;
    }

    /**
     * 取得指定两日期之间的连续日期
     *
     * @param start 开始日期yyyyMMdd
     * @param end   结束日期yyyyMMdd
     * @return 日期数组[yyyyMMdd, ...]
     */
    public static String[] getDateArrayBetween(String start, String end) {
        try {
            Date sDate = parseDate(start, date_s_pattern);
            Date eDate = parseDate(end, date_s_pattern);
            if (sDate == null || eDate == null || sDate.compareTo(eDate) > 0) {
                return new String[0];
            }
            List<String> ret = new ArrayList<>();
            while (sDate.compareTo(eDate) <= 0) {
                ret.add(formatSimDate(sDate));
                sDate = addDay(sDate, 1);
            }
            return ret.toArray(new String[ret.size()]);
        } catch (Exception e) {
            return new String[0];
        }
    }

    /**
     * 与当前时间差装换为小时
     * @param time
     * @return
     */
    public static Long secondConversionToHour(String time) {
        Long diffTime = diffNoeDate(time);
        if (diffTime != null){
            diffTime = diffTime / CONVERT_TO_SECOND;
        }else {
            return 0L;
        }
        return diffTime;
    }



    /**
     * 与当前时间相差多少秒
     *
     * @param b
     * @return
     */
    public static Long diffNoeDate(Date b)
    {
        Date a = new Date();
        Long interval = (a.getTime() - b.getTime()) / 1000;
        return interval;
    }

    /**
     * 时间戳转时间
     *
     * @param timeStamp
     * @return
     */
    public static String timeStampToDate(Long timeStamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(time_pattern);
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
        return sd;
    }

    protected static final Logger logger = LoggerFactory.getLogger(DateUtil.class);


    //根据传入的week判断是否是周的第一天，如果是周的第一天，取上周的周一
    public static Date geLastWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }
    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }
    /**
     * 获取前一个月第一天(yyyyMMdd)
     *
     * @return String:前一个月第一天
     */
    public static String getLast_month_firstDay()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(date_pattern);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, -1);
        calendar1.set(Calendar.DAY_OF_MONTH,1);
        String firstDay = sdf.format(calendar1.getTime());
        return firstDay;
    }

    /**
     * 获取当前月(yyyy-MM)
     *
     * @return String:获取当前月
     */
    public static String getMonth()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(month_pattern);
        Calendar calendar2 = Calendar.getInstance();
        String month = sdf.format(calendar2.getTime());
        return month;
    }

    /**
     * 获取月
     * @param date
     * @return
     */
    public static int getMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        return month;
    }

    /**
     * 获取月
     * @param date
     * @return
     */
    public static String getStrMonth(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(month_pattern);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String month = sdf.format(cal.getTime());
        return month;
    }

    /**
     * 获取下月(yyyy-MM)
     *
     * @return String:获取下月
     */
    public static String getNext_month()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(month_pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, +1);
        String firstDay = sdf.format(calendar.getTime());
        return firstDay;
    }

    /**
     *
     * date1=2018-12-24,date2=2018-12-24,result=0
     * date1=2018-12-24,date2=2018-12-25,result=-1
     * date1=2018-12-26,date2=2018-12-25,result=1
     *
     */
    public static int compare_date(String DATE1, String DATE2,String date_pattern) {

        DateFormat df = new SimpleDateFormat(date_pattern);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {//dt1在dt2之后
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {//dt1在dt2之前
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    /**
     * 开始日期转换
     * @description 如果查询条件中包含日期并且格式为yyyy-MM-dd，将时间补齐位数，方便数据库查询，提高查询效率及准确性
     * @date 2019/01/27
     * @author mengqa
     */
    public static String paramsBeginDateConvert(String beginDate) {
        return StringUtils.isNotEmpty(beginDate) ? beginDate + " " + BEGIN_TIME_FOR_DAY : null;
    }

    /**
     * 结束日期转换
     * @description 如果查询条件中包含日期并且格式为yyyy-MM-dd，将时间补齐位数，方便数据库查询，提高查询效率及准确性
     * @date 2019/01/27
     * @author mengqa
     */
    public static String paramsEndDateConvert(String endDate) {
        return StringUtils.isNotEmpty(endDate) ? endDate + " " + END_TIME_FOR_DAY : null;
    }

    /**
     * 获取指定日期下个月的第一天(默认yyyy-MM-dd格式返回)
     * @description 获取指定日期下个月的第一天
     * @date 2019/01/27
     * @author mengqa
     */
    public static String getFirstDayStringOfNextMonth(Date date) {
        return getFirstDayStringOfNextMonth(date, DateUtil.date_pattern);
    }

    /**
     * 获取指定日期下个月的第一天(自定义返回格式)
     * @description 获取指定日期下个月的第一天
     * @date 2019/01/27
     * @author mengqa
     */
    public static String getFirstDayStringOfNextMonth(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取指定日期下个月的第一天(返回date类型)
     * @description 获取指定日期下个月的第一天
     * @date 2019/01/27
     * @author mengqa
     */
    public static Date getFirstDayDateOfNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取指定日期的下个月(数字格式)
     * @description 获取指定日期下个月的第一天
     * @date 2019/01/27
     * @author mengqa
     */
    public static int getNextMonthNum(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 1);
        return cale.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取这个月的最后一天
     * @description 获取这个月的最后一天
     * @date 2019/01/27
     * @author mengqa
     */
    public static Date getLastDayOfCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return cal.getTime();
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @date 2019/01/27
     * @author mengqa
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}
