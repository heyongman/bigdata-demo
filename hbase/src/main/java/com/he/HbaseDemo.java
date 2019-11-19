package com.he;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

public class HbaseDemo {
    public static void main(String[] args) {
        try {
//            建立连接
            Configuration conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.property.clientPort", "2181");
            conf.set("hbase.zookeeper.quorum", "192.168.137.200");
            // conf.set("hbase.zookeeper.chroot","xjjgpt")
            Connection con = ConnectionFactory.createConnection(conf);
//            System.out.println(con);
           putData(con);//put数据
//            getData(con);//get数据
            scanData(con);//scan数据
//            filter(con);//过滤器
//            createTable(con);//创建表
//            dropTable(con);//删除表
//            关闭连接
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 插入数据
     */
    private static void putData(Connection con) throws Exception {
        Table table = con.getTable(TableName.valueOf("t01"));
        List<Put> list = new ArrayList<>();
        Put put = null;
        for (int i = 0; i < 10; i++) {
//            创建put对象，并给定rowkey
            put = new Put(Bytes.toBytes("rowkey0" + i));
            put.addColumn(Bytes.toBytes("cf01"), Bytes.toBytes("name" + i), Bytes.toBytes("value" + i));
            list.add(put);
        }
//        put数据
        table.put(list);
//        关闭
        table.close();
    }

    /**
     * 通过get获取数据
     *
     * @param con
     */
    private static void getData(Connection con) throws Exception {
            Table table = con.getTable(TableName.valueOf("t01"));
            Get get = new Get(Bytes.toBytes("rowkey09"));
            get.addFamily(Bytes.toBytes("cf01"));
            Result result = table.get(get);
            for (Cell cell : result.rawCells()) {
                System.out.print(Bytes.toString(CellUtil.cloneRow(cell)) + ":");
                System.out.print(Bytes.toString(CellUtil.cloneFamily(cell)) + ":");
                System.out.print(Bytes.toString(CellUtil.cloneQualifier(cell)) + ":");
                System.out.print(Bytes.toString(CellUtil.cloneValue(cell)) + ":");
            }
    }

    /**
     * 扫描数据
     */
    private static void scanData(Connection con) throws Exception{
//        three_day_big five_day_top erro_status_customer
            Table table = con.getTable(TableName.valueOf("erro_status_customer"));
            Scan scan = new Scan();
//            scan.setStartRow(Bytes.toBytes("rowkey003"));
//            scan.setStopRow(Bytes.toBytes("rowkey011"));
            ResultScanner scanner = table.getScanner(scan);
            Result result = scanner.next();
            while (result!=null){
                for (Cell cell:result.rawCells()) {
                    System.out.print(Bytes.toString(CellUtil.cloneRow(cell)) + "--");
                    System.out.print(Bytes.toString(CellUtil.cloneFamily(cell)) + "--");
                    System.out.print(Bytes.toString(CellUtil.cloneQualifier(cell)) + "--");
                    System.out.print(Bytes.toString(CellUtil.cloneValue(cell)) + "--");
                }
                result = scanner.next();
                System.out.println();
            }
            scanner.close();
            table.close();
    }

    /**
     * 过滤器
     * @param con
     */
    private static void filter(Connection con) throws Exception{
            Table table = con.getTable(TableName.valueOf("t01"));
            Scan scan = new Scan();
            Filter filter = new PrefixFilter(Bytes.toBytes("rowkey006"));
            scan.setFilter(filter);
            ResultScanner scanner = table.getScanner(scan);
            Result result = scanner.next();
            while (result!=null){
                for (Cell cell:result.rawCells()) {
                    System.out.print(Bytes.toString(CellUtil.cloneRow(cell)));
                    System.out.print(Bytes.toString(CellUtil.cloneFamily(cell)) + "--");
                    System.out.print(Bytes.toString(CellUtil.cloneQualifier(cell)) + "--");
                    System.out.print(Bytes.toString(CellUtil.cloneValue(cell))+"--");
                }
                result = scanner.next();
                System.out.println();
            }
            scanner.close();
            table.close();
    }

    /**
     * 创建表
     */
    private static void createTable(Connection con) throws Exception{
        HTableDescriptor td = new HTableDescriptor(TableName.valueOf("t01"));
        HColumnDescriptor cd = new HColumnDescriptor("cf01");
        td.addFamily(cd);
        Admin admin = con.getAdmin();
        admin.createTable(td);
        admin.close();
    }

    /**
     * 删除表
     */
    private static void dropTable(Connection con) throws Exception{
        Admin admin = con.getAdmin();
        TableName tableName = TableName.valueOf("t03");
        if (admin.tableExists(tableName)){
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        }
        admin.close();
    }
}
