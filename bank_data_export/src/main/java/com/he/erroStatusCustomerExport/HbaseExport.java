package com.he.erroStatusCustomerExport;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.util.List;

public class HbaseExport {
    /**
     * 连接hbase
     */
    public static void conHbase(List<Put> bankList){
        try {
//            建立连接
            Configuration conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.property.clientPort", "2181");
            conf.set("hbase.zookeeper.quorum", "192.168.200.200");
//            建立连接
            Connection con = ConnectionFactory.createConnection(conf);
            createTable(con);//创建表
            putData(con,bankList);//put数据
//            关闭连接
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 插入数据
     */
    private static void putData(Connection con,List<Put> bankList) throws Exception {
        Table table = con.getTable(TableName.valueOf("erro_status_customer"));
//        put数据
        table.put(bankList);
//        关闭
        table.close();
    }
    /**
     * 创建表
     */
    private static void createTable(Connection con) throws Exception{
        HTableDescriptor td = new HTableDescriptor(TableName.valueOf("erro_status_customer"));
        HColumnDescriptor cd = new HColumnDescriptor("cf01");
        td.addFamily(cd);
        Admin admin = con.getAdmin();
        if (!admin.tableExists(TableName.valueOf("erro_status_customer"))){
            admin.createTable(td);
        }
        admin.close();
    }
}
