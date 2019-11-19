package com.he.appendData;


import com.he.invoke.RandomName;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ProductData {

    private static final String wordSeparator = "\t";
    private static String ex[] = new String[]{"自营", "第三方"};
    private static String productNames[] = new String[]{"果酒","米酒","咖啡","茶叶","面包","奶粉","啤酒","巧克力","瓜子","开心果"};

    /**
     * 主方法
     *
     * @param args
     */
    public static void main(String[] args) {

        FileWriter fw = null;
        PrintWriter pw = null;
        String product_id	=null;
        String product_name	=null;
        String extend_info	=null;
        try {
            File file = new File("d:\\product_data.txt");
            Random random = new Random();

//            一个用户
            for (int i = 0; i < 100; i++) {
                product_id = String.valueOf(random.nextInt(10) + 1);
                product_name = productNames[random.nextInt(productNames.length)];
                extend_info = ex[random.nextInt(ex.length)];
                StringBuffer str = new StringBuffer();
                str.append(product_id).append(product_name).append(extend_info);
//                    System.out.println(str.toString());
                fw = new FileWriter(file, true);
                pw = new PrintWriter(fw);
                pw.print(str);
                pw.flush();

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
