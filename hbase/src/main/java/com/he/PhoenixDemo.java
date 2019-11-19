package com.he;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PhoenixDemo {
    public static void main(String[] args) {
        try {
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
//            String url = "jdbc:phoenix:172.20.14.215,172.20.14.216,172.20.14.217";
            String url = "jdbc:phoenix:192.168.137.200,192.168.137.201,192.168.137.202";
            Connection conn = DriverManager.getConnection(url);
            System.out.println(conn);
            PreparedStatement preparedStatement = conn.prepareStatement("select * from \"five_day_top\"");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString(1));
            }
            resultSet.close();
            preparedStatement.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
