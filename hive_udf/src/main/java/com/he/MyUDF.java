package com.he;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;


public class MyUDF extends UDF {
//    方法名必须是evaluate！
    public String evaluate(Text mobile) {
        StringBuffer result = new StringBuffer();
        result.append("格式不正确");
        if (mobile != null && mobile.getLength() == 11) {
            result.append(mobile.toString().substring(0, 3)).append("****").append(mobile.toString().substring(7));
            return result.toString();
        }
        return result.toString();
    }
//    public static String evaluate(Text text) {
//        String result = "格式不正确";
//        if (text != null && text.getLength() == 11) {
//            result = text.toString().substring(0, 3) + "****" + text.toString().substring(7);
//        }
//        System.out.println(result);
//        return result;
//    }
}
