package com.he.fiveDayTopExport;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HiveImport {
    public static void main(String[] args) {
        try {
//            加载驱动
            Class.forName("org.apache.hive.jdbc.HiveDriver");
//            建立连接
            Connection con = DriverManager.getConnection("jdbc:hive2://192.168.200.200:10000/default", "root", "");
            String now = "20180209";
//            执行语句
            PreparedStatement ps = con.prepareStatement("SELECT * FROM five_day_top WHERE b_date="+now);
            ResultSet rs = ps.executeQuery();
//            处理结果
            List<Put> bankList = new ArrayList<>();
            Put put = null;
            while (rs.next()) {
                put = new Put(Bytes.toBytes(UUID.randomUUID().toString()));
                put.addColumn(Bytes.toBytes("cf01"), Bytes.toBytes("name"), Bytes.toBytes(rs.getString("name")));
                put.addColumn(Bytes.toBytes("cf01"), Bytes.toBytes("cost"), Bytes.toBytes(rs.getString("cost")));
                put.addColumn(Bytes.toBytes("cf01"), Bytes.toBytes("b_date"), Bytes.toBytes(rs.getString("b_date")));
                bankList.add(put);
            }
//            调用添加数据到hbase方法
            System.out.println(bankList.size());
            HbaseExport.conHbase(bankList);
//            关闭资源
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
