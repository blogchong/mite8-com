package com.mite8.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author: blogchong
 * Time:   2016/6/19
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Des:    时间转换函数
 */
public class TransferTime {

//    public static void main(String[] args) throws Exception {
//
//        String type = "dd/MMM/yyy:HH:mm:ss Z";
//
//        Date dateDate = new Date();
//
//        //1469896652
//        System.out.println(TransferTime.longToString(1469896652, "YY/MM/dd"));
//        System.out.println(TransferTime.stringToLong("2016-08-03", "yyy-MM-dd")/1000);
//        System.out.println(TransferTime.dateToString(new Date(), "YY/MM/dd"));
//
//        long time1 = TransferTime.stringToLong("2016-10-3", "yyy-MM-dd")/1000;
//        long time2 = TransferTime.dateToLong(new Date())/1000;
//
//        String strDate = dateToString(dateDate, type);
////
////        Long longDate = stringToLong(strDate, type);
////
////        System.out.println("初始时间1：" + strDate);
////        System.out.println("初始时间2：" + longDate/1000);
//
//    }

    // date类型转换为String类型
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType, Locale.ENGLISH).format(data);
    }

    // long类型转换为String类型
    // currentTime要转换的long类型的时间,秒级
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // string类型转换为date类型
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat(formatType, Locale.ENGLISH);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // long转换为Date类型
    // currentTime要转换的long类型的时间,秒级
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime * 1000); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // string类型转换为long类型
    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    //输出的时间为毫秒级
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // date类型转换为long类型
    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }
}
