package com.he.appendData;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class BehaviorData {

    private static final String wordSeparator = "\t";
    private static String keywords[] = new String[]{"java", "spark", "hive", "hbase", "spring", "hibernate", "html", "css", "javascript", "ajax", "jquery"};
    private static String clicks[] = new String[]{"search", "click", "order", "pay"};
    private static String date = "2018-05-10";

    /**
     * 主方法
     *
     * @param args
     */
    public static void main(String[] args) {

        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
            DateFormat df = new SimpleDateFormat("yyyy-MM-d");
            String date = df.format(new Date());
            File file = new File("d:\\behavior_data.txt");
            Random random = new Random();
            String actionTime = null;
//            一个用户
            for (int i = 0; i < 100; i++) {
                String userId = String.valueOf(random.nextInt(10) + 1);
                String sessionId = UUID.randomUUID().toString().replaceAll("-", "");

                int forNum = random.nextInt(2000);
                for (int j = 0; j < forNum; j++) {
                    String pageId = null;
                    String searchKeyword = null;
                    String clickCategoryId = null;
                    String clickProductId = null;
                    String orderCategoryId = null;
                    String orderProductId = null;
                    String cityId = null;
                    String payCategoryId = null;
                    String payProductId = null;
                    pageId = String.valueOf(random.nextInt(20) + 1);
                    actionTime = dateFormat.format(new Date());
                    clickCategoryId = clicks[random.nextInt(clicks.length)];
                    if ("search".equals(clickCategoryId)) {
                        searchKeyword = keywords[random.nextInt(keywords.length)];
                    } else if ("click".equals(clickCategoryId)) {
                        clickProductId = String.valueOf(random.nextInt(10) + 1);
                    } else if ("order".equals(clickCategoryId)) {
                        orderCategoryId = String.valueOf(random.nextInt(100000) + 1);
                        orderProductId = String.valueOf(random.nextInt(10) + 1);
                    } else if ("pay".equals(clickCategoryId)) {
                        payCategoryId = String.valueOf(random.nextInt(100000) + 1);
                        payProductId = String.valueOf(random.nextInt(10) + 1);
                    }
                    cityId = String.valueOf(random.nextInt(31) + 1);
                    StringBuffer str = new StringBuffer();
                    str.append(date).append(wordSeparator).append(userId).append(wordSeparator).append(sessionId).append(wordSeparator).append(pageId).append(wordSeparator);
                    str.append(actionTime).append(wordSeparator).append(searchKeyword).append(wordSeparator).append(clickCategoryId).append(wordSeparator).append(clickProductId).append(wordSeparator);
                    str.append(orderCategoryId).append(wordSeparator).append(orderProductId).append(wordSeparator).append(payCategoryId).append(wordSeparator).append(payProductId).append(wordSeparator);
                    str.append(cityId).append("\n");
//                    System.out.println(str.toString());
                    fw = new FileWriter(file, true);
                    pw = new PrintWriter(fw);
                    pw.print(str);
                    pw.flush();
                    Thread.sleep(1);
                }
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
