package com.he.dayUDF;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DayUDF extends UDF{
    public static String evaluate(int i) {
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,i);//把日期往后增加一天.整数往后推,负数往前移动，0代表今天的时间
        date=calendar.getTime();      //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(date);
        return dateString;
    }
//    public static void main(String[] args) {
//        System.out.println("今天时间："+evaluate(0));
//        System.out.println("明天时间："+evaluate(1));
//        System.out.println("昨天天时间："+evaluate(-1));
//    }
}
