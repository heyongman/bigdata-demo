package com.he.appendData;


import com.he.invoke.RandomName;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class UserData {

    private static final String wordSeparator = "\t";
    private static String sexs[] = new String[]{"男", "女"};

    /**
     * 主方法
     *
     * @param args
     */
    public static void main(String[] args) {

        FileWriter fw = null;
        PrintWriter pw = null;
        String userId = null;
        String userName = null;
        String age = null;
        String sex = null;

        try {
            File file = new File("d:\\user_data.txt");
            Random random = new Random();

//            用户
            for (int i = 0; i < 100; i++) {
                userId = String.valueOf(random.nextInt(10) + 1);
                userName = RandomName.getUserName();
                age = String.valueOf(random.nextInt(80) + 10);
                sex = sexs[random.nextInt(sexs.length)];

                StringBuffer str = new StringBuffer();
                str.append(userId).append(wordSeparator).append(userName).append(wordSeparator).append(wordSeparator).append(age).append(wordSeparator).append(wordSeparator).append(wordSeparator).append(sex).append("\n");
//                 System.out.println(str.toString());
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
