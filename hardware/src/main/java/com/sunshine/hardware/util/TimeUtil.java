package com.sunshine.hardware.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    public static final String dataMonthStringNotSign = "yyyyMM";
    public static String dataSecondString = "yyyy-MM-dd HH:mm:ss";
    public static String dataMinuteString = "yyyy-MM-dd HH:mm";
    public static String dataSecondStringLine = "yyyy/MM/dd HH:mm:ss";
    public static String dataSecondNoDayStringLine = "HH:mm:ss";
    public static String dataDayStringNotSign = "yyyyMMdd";

    public static Calendar getNextMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH , 1);
        return calendar;
    }

    public static int getHourOfDay(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static Calendar getCurrentMonth(){
        Calendar calendar = Calendar.getInstance();
        return calendar;
    }

    public static String getTimeString(Date date, String format){
        return new SimpleDateFormat(format).format(date);
    }
}
