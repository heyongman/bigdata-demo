package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Hive {
    public static void main(String[] args) throws Exception{
//        加载驱动
        Class.forName("org.apache.hive.jdbc.HiveDriver");
//        建立连接
        Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default","root","");
//        执行语句
        PreparedStatement ps = con.prepareStatement("SELECT * FROM flume_user");
        ResultSet rs = ps.executeQuery();
//        处理结果
        while (rs.next()){
            System.out.println(rs.getString(1)+","+rs.getString(2));
        }
//        关闭资源
        rs.close();
        ps.close();
        con.close();
    }
}
