package com.example.javabasic.date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import java.util.Calendar;

/**
 * @author：Cheng.
 * @since： 0910
 */
public class DateUtil {

    /***
     * 获取前一日的零点时间
     */
    @Test
    public void getLastDayZeroTime(){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_MONTH, -1);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        DateTime startTime = new DateTime(calendar1.getTimeInMillis());
        System.out.println("startTime = " + startTime.toString(ConfigKey.TIME_FORMAT_PATTERN_2));
    }

    /**
     * 取前一日的23:59:59
     */
    @Test
    public void getLastDayEndTime(){
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, -1);
        calendar2.set(Calendar.HOUR_OF_DAY, 23);
        calendar2.set(Calendar.MINUTE, 59);
        calendar2.set(Calendar.SECOND, 59);
        DateTime statEndTime = new DateTime(calendar2.getTimeInMillis());
        System.out.println("statEndTime = " + statEndTime.toString(ConfigKey.TIME_FORMAT_PATTERN_2));
    }


    /***
     * 字符串转DateTime
     */
    @Test
    public void strToDateTime(){
        String timeStr = "2020-09-09 23:59:59";
        DateTime dateTime = DateTime.parse(timeStr, DateTimeFormat.forPattern(ConfigKey.TIME_FORMAT_PATTERN_2));
        System.out.println("dateTime = " + dateTime.toString(ConfigKey.TIME_FORMAT_PATTERN_1));
    }
}
