package com.he.appendData;


import com.he.lineData.LineData;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppendData {
    /**
     * 目的目录
     */
    private static final String desDir = "/keduox/bankData";
    /**
     * 数据后缀名
     */
    private static final String suffix = ".txt";
    /**
     * 数据标记文件
     */
    private static final String successSuffix = ".success";


    /**
     * 主方法
     *
     * @param args
     */
    public static void main(String[] args) {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            Calendar c = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//             开始时间必须小于结束时间
            Date beginDate = dateFormat.parse("201803228");
            Date endDate = new Date();
            Date date = beginDate;
//            判断目录存不存在
            File dir = new File(desDir);
            if (!dir.exists()){
                dir.mkdirs();
            }
//            日期循环
            while (date.before(endDate)) {
                System.out.println(dateFormat.format(date));
                String time = dateFormat.format(date);
                c.setTime(date);
                c.add(Calendar.DATE, 1); // 日期加1天
                date = c.getTime();
                File file = new File(desDir + "/"+ time + suffix);
                File successFile = new File(desDir + "/"+ time + successSuffix);
//                判断文件存在否
                if (!file.exists()) {
                    file.createNewFile();
                }
//                删除成功标记文件
                if (successFile.exists()){
                    successFile.delete();
//                    System.out.println("删除成功！");
                }
//                追加内容
                for (int i = 0; i < 100000; i++) {
//                    获取文件大小
                    long length = file.length();
//                    System.out.println("文件大小"+length);
//                    判断文件大小受否超过128M
                    if (length>=128*1024){
                        break;
                    }
                    fw = new FileWriter(file, true);
                    pw = new PrintWriter(fw);
                    pw.print(LineData.getLineData());
                    pw.flush();
//                    能否解决Too many open files问题
                    pw.close();
                    fw.close();
                }
                Thread.sleep(2000);
//                写完就创建成功标记文件
                successFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
