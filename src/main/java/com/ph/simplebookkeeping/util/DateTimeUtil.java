package com.ph.simplebookkeeping.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd
     */
    public static final String DATE_FORMAT_2 = "yyyy-MM-dd";
    /**
     * yyyy/MM/dd HH:mm:ss
     */
    public static final String DATE_FORMAT_3 = "yyyy/MM/dd HH:mm:ss";
    /**
     * yyyyMMdd HH:mm:ss
     */
    public static final String DATE_FORMAT_4 = "yyyyMMdd HH:mm:ss";
    /**
     * yyyyMMdd
     */
    public static final String DATE_FORMAT_5 = "yyyyMMdd";

    /**
     * 将string字符串转化为Date类型的字符串
     *
     * @param dateTimeStr 需要转化的string类型的字符串
     * @param formatStr   转化规则
     * @return 返回转化后的Date类型的时间
     */
    public static Date strToDate(String dateTimeStr, String formatStr) {
        if (dateTimeStr == null || dateTimeStr.trim().isEmpty()) {
            throw new RuntimeException("转换时间不能为空");
        }
        if (formatStr == null || formatStr.trim().isEmpty()) {
            throw new RuntimeException("转换时间的格式为空");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    /**
     * 将date类型的时间转化为string类型
     *
     * @param date      需要转化的date类型的时间
     * @param formatStr 转化规则
     * @return 返回转化后的string类型的时间
     */
    public static String dateToStr(Date date, String formatStr) {
        if (date == null) {
            throw new RuntimeException("转换时间为空");
        }
        if (formatStr == null || formatStr.trim().isEmpty()) {
            throw new RuntimeException("转换时间的格式为空");
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    /**
     * 将string字符串转化为Date类型的字符串
     *
     * @param dateTimeStr 需要转化的string类型的时间
     * @return 返回转化后的Date类型的时间
     */
    public static Date strToDate(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.trim().isEmpty()) {
            throw new RuntimeException("转换时间不能为空");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(DATE_FORMAT_1);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    /**
     * 将date类型的时间转化为string类型
     *
     * @param date 需要转化的date类型的时间
     * @return 返回转化后的string类型的时间
     */
    public static String dateToStr(Date date) {
        if (date == null) {
            throw new RuntimeException("转换时间不能为空");
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(DATE_FORMAT_1);
    }

    /**
     * 将字符串类型时间戳转换为指定类型字符串类型日期格式
     *
     * @param timeStamp 毫秒值字符串
     * @param df        定义了格式的SimpleDateFormat
     * @return
     */
    public static String timeStampToStrDate(String timeStamp, SimpleDateFormat df) {
        if (timeStamp == null || timeStamp.trim().isEmpty()) {
            return null;
        }
        try {
            long time = Long.parseLong(timeStamp);
            return df.format(new Date(time));
        } catch (Exception e) {
            return timeStamp;
        }
    }

}