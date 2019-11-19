package com.he.modules.dao;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;


/**
 * 连接hbase的类
 */
@Component
public class BankDao {
    /**
     * 连接hbase
     */
    public JSONArray conHbase(String table){
        try {
//            建立连接
            Configuration conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.property.clientPort", "2181");
            conf.set("hbase.zookeeper.quorum", "192.168.200.200");
            Connection con = ConnectionFactory.createConnection(conf);
            JSONArray dataList = scanData(con,table);//scan数据
//            关闭连接
            con.close();
            return dataList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 扫描数据
     */
    private static JSONArray scanData(Connection con, String t) throws Exception{
//        three_day_big five_day_top erro_status_customer
        Table table = con.getTable(TableName.valueOf(t));
        Scan scan = new Scan();
//            scan.setStartRow(Bytes.toBytes("rowkey003"));
//            scan.setStopRow(Bytes.toBytes("rowkey011"));
        ResultScanner scanner = table.getScanner(scan);
        Result result = scanner.next();
        JSONObject obj = null;
        JSONArray dataList = new JSONArray();
        while (result!=null){
            obj = new JSONObject();
            for (Cell cell:result.rawCells()) {
//                System.out.print(Bytes.toString(CellUtil.cloneRow(cell)) + "--");
//                System.out.print(Bytes.toString(CellUtil.cloneFamily(cell)) + "--");
//                System.out.print(Bytes.toString(CellUtil.cloneQualifier(cell)) + "--");
//                System.out.print(Bytes.toString(CellUtil.cloneValue(cell)) + "--");
//                将数据放入json对象中
                obj.put(Bytes.toString(CellUtil.cloneQualifier(cell)),Bytes.toString(CellUtil.cloneValue(cell)));
            }
//            将json对象加到json集合中
            dataList.put(obj);
            result = scanner.next();
//            System.out.println();
        }
        scanner.close();
        table.close();
        return dataList;
    }
}
