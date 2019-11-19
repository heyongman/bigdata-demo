package com.he.invoke;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 调用linux系统进程的类
 */
public class InvokeProcess {
    public static void invoke(String date) {
        try {
            String shPath = "/keduox/load_data.sh "+date;
            System.out.println(shPath);
            Process ps = Runtime.getRuntime().exec(new String[] {"/bin/sh","-c",shPath},null,null);
            ps.waitFor();
//            打印输入流的内容
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
