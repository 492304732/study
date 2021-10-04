package com.susu.study.j2se.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormat常见用法
 */
public class TestDateFormat {
    public static void main(String[] args) {
        printNowDate();
        dateToString();
        dateCompare();
    }

    /**
     * 打印当前时间
     */
    public static void printNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        System.out.println(dateString);
        System.out.println();
    }

    /**
     * 字符串转换成日期
     */
    public static void dateToString() {
        String dateString = "2017-2-15";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(dateString);
            System.out.println(date);
            System.out.println();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 日期比较大小
     */
    public static void dateCompare() {
        String dateString1 = "2017-2-15";
        String dateString2 = "2017-2-15";
        Date date1 = null, date2 = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date1 = formatter.parse(dateString1);
            date2 = formatter.parse(dateString2);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("date1:" + date1 + "\ndate2:" + date2);
        System.out.println("date1>date2? " + (date1.compareTo(date2)));
        System.out.println();

        date2 = new Date();
        System.out.println("date1:" + date1 + "\ndate2:" + date2);
        System.out.println("date1>date2? " + (date1.compareTo(date2)));
        System.out.println();
    }
}
