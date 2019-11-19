package com.he.invoke;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class IterateDate {
    public static void iterDate() {
        try {
            Calendar c = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//             开始时间必须小于结束时间
            Date beginDate = dateFormat.parse("20170501");
            Date endDate = dateFormat.parse("20170625");
            Date date = beginDate;
            while (!date.equals(endDate)) {
                System.out.println(dateFormat.format(date));
                c.setTime(date);
                c.add(Calendar.DATE, 1); // 日期加1天
                date = c.getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
