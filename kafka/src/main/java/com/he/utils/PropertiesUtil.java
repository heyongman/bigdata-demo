package com.he.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件的类
 */
public class PropertiesUtil {
    /**
     * 获取配置文件
     * @param name 配置文件的名字
     * @return Properties对象
     */
    public static Properties getProperties(String name){
//        创建Properties对象
        Properties prop = new Properties();
        try{
            //读取属性文件a.properties
            InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(name);
            prop.load(in);     ///加载属性列表
//            Iterator<String> it=prop.stringPropertyNames().iterator();
//            while(it.hasNext()){
//                String key=it.next();
//                System.out.println(key+":"+prop.getProperty(key));
//            }
            in.close();
            return prop;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
