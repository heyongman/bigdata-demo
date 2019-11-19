package com.he;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 获取一行数据的类
 */
public class LineData {
    /**
     * 字段分隔符
     */
    private static final String filedDelimeter = "\t";
    /**
     * 浏览器
     */
    private static final String[] browser = {"Chrome","Firefox","QQ浏览器","搜狗高速浏览器","Internet Explorer 11","360安全浏览器","Opera","Edge"};

    /**
     * 获取一行数据的方法
     *
     * @return
     */
    public static String getData() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String format = dateFormat.format(date);
        return format+filedDelimeter+ProduceIp.getRandomIp()+filedDelimeter+browser[new Random().nextInt(browser.length)];
    }
}
