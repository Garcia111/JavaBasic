/*
 * Copyright (C) 2019 China Telecom System Integration Co., Ltd.
 * All rights reserved.
 */
package com.example.javabasic.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/** Created with IntelliJ IDEA. User: fangzh Date: 2019/9/16 16:37 Desp: 时间相关工具类 */
@UtilityClass
public class DateUtil {

    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD = "yyyyMMdd";
    public static final String Y_M_D = "yyyy-MM-dd";

    public static final String YMDHMS2 = "yyyyMMddHHmmss";

    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * Get a new date time from a existed date and time
     *
     * @param date
     * @param time
     * @return
     */
    public static Date getDateTime(Date date, Date time) {
        if (null == date && null == time) {
            return null;
        }
        if (null == date) {
            date = DateUtil.getSystemDate();
        }
        if (null != date && null == time) {
            time = DateUtil.getSystemTimestamp();
        }
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(DateUtil.parseDate(date, "yyyy"));
        int month = Integer.parseInt(DateUtil.parseDate(date, "M"));
        int day = Integer.parseInt(DateUtil.parseDate(date, "dd"));
        int hour = Integer.parseInt(DateUtil.parseDate(time, "h"));
        int minute = Integer.parseInt(DateUtil.parseDate(time, "m"));
        cal.set(year, month, day, hour, minute);
        return cal.getTime();
    }

    public static boolean isDateEqual(Date date1, Date date2) {
        return !((date1 == null) || (date2 == null))
                && resetTime(date1).compareTo(resetTime(date2)) == 0;
    }

    public static boolean isEndOfTheMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return cal.get(Calendar.DATE) == maxDay;
    }

    public static boolean isEndOfTheYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return (cal.get(Calendar.MONTH) == 11) && (cal.get(Calendar.DATE) == 31);
    }

    public static int getLastDayOfTheMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Date getNextWorkingDay() {
        Date nextWorkingDay = DateUtil.addDaysToDate(DateUtil.getSystemDate(), 1);
        Calendar c = Calendar.getInstance();
        c.setTime(nextWorkingDay);
        int day = c.get(Calendar.DAY_OF_WEEK);
        if (day == Calendar.SUNDAY) {
            nextWorkingDay = DateUtil.addDaysToDate(nextWorkingDay, 1);
        }
        return nextWorkingDay;
    }

    public static boolean isStartBeforeEndDate(Date startDate, Date endDate) {
        return !((startDate == null) || (endDate == null))
                && resetTime(startDate).compareTo(resetTime(endDate)) < 0;
    }

    public static boolean isStartBeforeEndTime(Date startTime, Date endTime) {
        if ((startTime == null) || (endTime == null)) {
            return true;
        }
        return startTime.getTime() <= endTime.getTime();
    }

    public static boolean isStartOfTheMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE) == 1;
    }

    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static boolean isStartOfTheYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return (cal.get(Calendar.MONTH) == 1) && (cal.get(Calendar.DATE) == 1);
    }

    public static java.sql.Date getSystemDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new java.sql.Date(cal.getTime().getTime());
    }

    public static Timestamp getSystemTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static boolean isValidYearFormat(String s) {
        if (s == null) {
            return false;
        } else if (s.trim().length() == 4) {
            return true;
        }
        return false;
    }

    public static String getDate(Date date, String strFormat) {
        return DateUtil.parseDate(date, strFormat);
    }

    public static Date addDate(int type, Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(type, num);
        return new Date(cal.getTime().getTime());
    }

    public static Date addDaysToDate(Date date, int numDays) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, numDays);
        return c.getTime();
    }

    public static Date addHoursToDate(Date date, int numHours) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, numHours);
        return c.getTime();
    }

    public static Date addMinutesToDate(Date date, int numMins) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, numMins);
        return c.getTime();
    }

    public static Date addMonthsToDate(Date date, int numMonths) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, numMonths);
        return c.getTime();
    }

    public static Date addYearsToDate(Date date, int numYears) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, numYears);
        return c.getTime();
    }

    public static String parseDate(Date date, String formatStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
        if (date == null) {
            return null;
        } else {
            return dateFormat.format(date);
        }
    }

    public static Date resetTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date toDate(String strDateTime, String dateTimeFormat) {
        if ((strDateTime == null)
                || (strDateTime.length() == 0)
                || (dateTimeFormat == null)
                || (dateTimeFormat.length() == 0)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
        Date date = dateFormat.parse(strDateTime, new ParsePosition(0));
        if (date == null) {
            return null;
        }
        String dateStr = parseDate(date, dateTimeFormat);
        if (!strDateTime.equals(dateStr)) {
            return null;
        }
        return date;
    }

    public static Timestamp toTimestamp(String dateTimeStr, String dateTimeFormat) {
        return toTimestamp(toDate(dateTimeStr, dateTimeFormat));
    }

    public static Timestamp toTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    public static Date toDate(Timestamp timeStamp) {
        if (timeStamp == null) {
            return null;
        }

        return new Date(timeStamp.getTime());
    }

    public static Date firstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(0);
        int year = DateUtil.getYear(date);
        int month = DateUtil.getMonth(date) + 1;
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 2;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        if (dayOfWeek < 0) {
            dayOfWeek += 7;
        }
        return DateUtil.getDate(year, month, dayOfMonth - dayOfWeek);
    }

    public static Date lastDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(0);
        int year = DateUtil.getYear(date);
        int month = DateUtil.getMonth(date) + 1;
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 2;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return DateUtil.getDate(year, month, dayOfMonth - dayOfWeek + 6);
    }

    public static int getFirstDayOfWeekInMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 2;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth - dayOfWeek;
    }

    /**
     * 根据增减天数获取新日期
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return str 返回之间的"日 小时 分钟"
     */
    public static String getDateDiff(Date startDate, Date endDate) {
        String str = "";
        Calendar ca1 = Calendar.getInstance();
        Calendar ca2 = Calendar.getInstance();
        ca1.setTime(startDate);
        ca2.setTime(endDate);
        int distanceDay = ca2.get(Calendar.DAY_OF_MONTH) - ca1.get(Calendar.DAY_OF_MONTH);
        int distanceHour = ca2.get(Calendar.HOUR_OF_DAY) - ca1.get(Calendar.HOUR_OF_DAY);
        int distanceMin = ca2.get(Calendar.MINUTE) - ca1.get(Calendar.MINUTE);
        if (distanceDay != 0) {
            str = str + distanceDay + "天";
        }
        str = str + distanceHour + "小时" + distanceMin + "分钟";
        return str;
    }

    /**
     * 根据增减天数获取新日期
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return str 返回之间的秒"
     */
    public static int getDateDiffSec(Date startDate, Date endDate) {
        int sec = 0;
        Calendar ca1 = Calendar.getInstance();
        Calendar ca2 = Calendar.getInstance();
        ca1.setTime(endDate);
        ca2.setTime(startDate);
        double timeLong = ca1.getTimeInMillis() - ca2.getTimeInMillis();
        sec = (int) timeLong / 1000;
        return sec;
    }

    /**
     * 根据增减天数获取新日期
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return str 返回之间的天数"
     */
    public static int getDateDiffDay(Date startDate, Date endDate) {
        Calendar ca1 = Calendar.getInstance();
        Calendar ca2 = Calendar.getInstance();
        ca1.setTime(endDate);
        ca2.setTime(startDate);

        int day =
                ((int) (ca1.getTime().getTime() / 1000) - (int) (ca2.getTime().getTime() / 1000))
                        / (60 * 60 * 24);
        return Math.abs(day);
    }

    /**
     * 获取月份差
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDateDiffMonth(Date startDate, Date endDate) {
        Calendar ca1 = Calendar.getInstance();
        Calendar ca2 = Calendar.getInstance();
        ca1.setTime(endDate);
        ca2.setTime(startDate);

        int year = ca2.get(Calendar.YEAR) - ca1.get(Calendar.YEAR);
        int month = ca2.get(Calendar.MONTH) - ca1.get(Calendar.MONTH);
        return Math.abs(year) * 12 + Math.abs(month);
    }

    /**
     * 根据增减天数获取新日期
     *
     * @param inDate 传入的日期
     * @param AddDateInt 要增减的天数
     * @return Date 返回日期
     */
    public static Date getDateAddInt(Date inDate, int AddDateInt) {
        Calendar theCalendar = new GregorianCalendar();
        Date returnDate = new Date();

        if (inDate != null) {
            String DateStr = parseDate(inDate, "yyyy-MM-dd HH:mm:ss");
            theCalendar.set(
                    Integer.parseInt(DateStr.substring(0, 4)),
                    Integer.parseInt(DateStr.substring(5, 7)) - 1,
                    Integer.parseInt(DateStr.substring(8, 10)) + AddDateInt,
                    Integer.parseInt(DateStr.substring(11, 13)),
                    Integer.parseInt(DateStr.substring(14, 16)),
                    Integer.parseInt(DateStr.substring(17, 19)));
            returnDate = new Date();
            returnDate = theCalendar.getTime();
        }

        return returnDate;
    }

    public static Date toTime(String dateStr, String formatStr) {
        Date startTime = null;
        if (dateStr == null && formatStr == null) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            startTime = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return startTime;
    }

    /**
     * 判断是否在一个时间段内
     *
     * @param startTimeStr
     * @param endTimeStr
     * @param timeStr
     * @param formatStr
     * @return
     */
    public static boolean isBetweenTimes(
            String startTimeStr, String endTimeStr, String timeStr, String formatStr) {
        boolean flag = false;
        Date startTime = null;
        Date endTime = null;
        Date time = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            startTime = sdf.parse(startTimeStr);
            endTime = sdf.parse(endTimeStr);
            time = sdf.parse(timeStr);
            if (startTime.before(time) && endTime.after(time)) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    // 同当前时间比较时间差
    public static boolean timeDiff(String timestamp, Integer diffMinute) {
        boolean flag = false;
        Date date = DateUtil.toDate(timestamp, "yyyyMMddHHmmss");
        if (date != null) {
            Date now = new Date();
            long l = now.getTime() - date.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            if (day != 0 || hour != 0 || min < 0 || min > diffMinute) {
                flag = true;
            }
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 取当月的最后天日期
     *
     * @param currDate
     * @return
     */
    public static Date getLastDayOfMonth(Date currDate) {
        Calendar cDay1 = Calendar.getInstance();
        cDay1.setTime(currDate);
        final int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay1.getTime();
        // lastDate.setDate(lastDay);
        return lastDate;
    }

    /**
     * 取当月的第一天日期
     *
     * @param currDate
     * @return
     */
    public static Date getFirstDayOfMonth(Date currDate) {
        Calendar cDay1 = Calendar.getInstance();
        cDay1.setTime(currDate);
        final int lastDay = cDay1.getActualMinimum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay1.getTime();
        lastDate.setDate(lastDay);
        return lastDate;
    }

    public static int obtainPartition(Date date) {
        int month = date.getMonth() + 1;
        int day = date.getDate();
        int partition = month * 100 + day;
        return partition;
    }

    public static int obtainPartition1(Date date) {
        //        int month = date.getMonth() + 1;
        int day = date.getDate();
        int partition = day;
        return partition;
    }

    public static int obtainPartition(String date) {
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        int partition = Integer.parseInt(month + day);
        return partition;
    }

    /**
     * 格式化时间
     *
     * @param date 时间
     * @param his 加减时间天数
     * @param flag 设置开始or结束时间设置（true 00:00:00;flase 23:59:59）
     */
    public static Date getFirstOrEndTimeOfDay(Date date, int his, boolean flag) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        if (flag) {
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
        } else {
            date.setHours(23);
            date.setMinutes(59);
            date.setSeconds(59);
        }
        calendar.setTime(date);
        System.out.println("    date:" + date);
        calendar.add(Calendar.DAY_OF_MONTH, his);
        String dateStr = sdf.format(calendar.getTime());
        System.out.println("    dateStr:" + dateStr);
        Date firstTime = null;
        try {
            firstTime = sdf.parse(dateStr);
            System.out.println("    firstTime:" + firstTime.toString());
        } catch (ParseException e) {
            e.printStackTrace(); // To change body of catch statement use File | Settings | File
            // Templates.
        }
        return firstTime;
    }

    /**
     * 判断某一时间是否在一个区间内
     *
     * @param sourceTime 时间区间,半闭合,如[10:00-20:00)
     * @param curTime 需要判断的时间 如10:00
     * @return
     * @throws IllegalArgumentException
     */
    public static boolean isInTime(String sourceTime, String curTime) {
        // curTime默认为当前时间
        if (StringUtils.isEmpty(curTime)) {
            curTime = getDate(new Date(), "HH:mm");
        }
        if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
            throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
        }

        if (!curTime.contains(":")) {
            throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
        }
        String[] args = sourceTime.split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            long now = sdf.parse(curTime).getTime();
            long start = sdf.parse(args[0]).getTime();
            long end = sdf.parse(args[1]).getTime();
            if (args[1].equals("00:00")) {
                args[1] = "24:00";
            }
            if (end < start) {
                if (now >= end && now < start) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (now >= start && now < end) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
        }
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNowDate() {
        return getNowDate(YMD);
    }

    /**
     * 获取当前时间
     *
     * @param strFormat
     * @return
     */
    public static String getNowDate(String strFormat) {
        return DateUtil.parseDate(new Date(), strFormat);
    }

    public static boolean isValidDate(String str, String formatStr) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public static String formatData(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YMDHMS);
        String timeToString = formatter.format(dateTime);
        return timeToString;
    }

    public static LocalDateTime formateToLocal(String date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(YMDHMS);
        LocalDateTime ldt = LocalDateTime.parse(date, df);
        return ldt;
    }

    public static void main(String[] args) {}
}
