package com.xinyartech.baselibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangjiebo on 2017/12/26/026.
 *
 * @author ZhangJieBo
 */

public class DateTransforam {
    /**
     * 将时间转换为时间戳
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static long dateToStamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        return date.getTime();
    }
    /**
     * 将时间转换为时间戳
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static long dateToStampXX(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = simpleDateFormat.parse(s);
        return date.getTime();
    }

    /**
     * 将时间转换为时间戳
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static long dateToStampX(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        return date.getTime();
    }
  /**
     * 将时间转换为时间戳
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static long dateToStampXXX(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = simpleDateFormat.parse(s);
        return date.getTime();
    }

    /**
     * 时间戳转日期
     *
     * @param s
     * @return
     */
    public static String stampToDate(long s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String dateTime = sdf.format(s);
        return dateTime;
    }

    /**
     * 时间戳转日期
     *
     * @param s
     * @return
     */
    public static String stampToDateYYMMddX(long s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String dateTime = sdf.format(s);
        return dateTime;
    }

    /**
     * 时间戳转日期
     *
     * @param s
     * @return
     */
    public static String stampToDateX(long s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateTime = sdf.format(s);
        return dateTime;
    }
    /**
     * 时间戳转日期
     *
     * @param s
     * @return
     */
    public static String stampToDateyyyyMMdd(long s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateTime = sdf.format(s);
        return dateTime;
    }

    /**
     * 时间戳转日期
     *
     * @param s
     * @return
     */
    public static String
    stampToDateYYMMdd(long s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = sdf.format(s);
        return dateTime;
    }

    /**
     * 时间戳转日期
     *
     * @param s
     * @return
     */
    public static String stampToDateYYmm(long s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String dateTime = sdf.format(s);
        return dateTime;
    }
}
