package com.he;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器
 */
public class HbaseFilter {
    public static void main(String[] args) {
        try {
//            建立连接
            Configuration conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.property.clientPort", "2181");
            conf.set("hbase.zookeeper.quorum", "192.168.200.200");
            Connection con = ConnectionFactory.createConnection(conf);
//            putData(con);
            filter(con);
//            关闭连接
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加数据
     */
    private static void putData(Connection con) throws Exception{
        Table table = con.getTable(TableName.valueOf("t01"));
        List<Put> putList = new ArrayList<>();
        Put put = null;
        for (int i = 0; i < 1000000; i++) {
            put = new Put(Bytes.toBytes("rw"+i));
            put.addColumn(Bytes.toBytes("cf01"),Bytes.toBytes("name"),Bytes.toBytes("value"+i));
            putList.add(put);
        }
        table.put(putList);
        table.close();
    }

    /**
     * 过滤器
     * @param con
     */
    private static void filter(Connection con) throws Exception{
        Table table = con.getTable(TableName.valueOf("t01"));
//        Scan scan = new Scan();
//        ResultScanner resultScanner  = table.getScanner(scan);
//        RegexStringComparator comp = new RegexStringComparator("zs35"); // 以zs35开头的字符串
//        SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes("cf01"), Bytes.toBytes("name"), CompareFilter.CompareOp.EQUAL, comp);
//        scan.setFilter(filter);


        //过滤器的使用
        Scan scan=new Scan();
        //scan.setStartRow("rowkey163370".getBytes());
        //scan.setStopRow("rowkey163380".getBytes());

        RegexStringComparator comp = new RegexStringComparator("120");
        //有可能出现组合filter的情况    比如name以java开头，而年龄是120开头   ---- where name=  and age =
        //  where name=  or age=
        FilterList filterList=new FilterList(FilterList.Operator.MUST_PASS_ALL);
        SingleColumnValueFilter filter1=new SingleColumnValueFilter(Bytes.toBytes("cf01"),Bytes.toBytes("name"), CompareFilter.CompareOp.EQUAL,comp);
        //如果在查询的过程，该行没有查询的字段，那么hbase的过滤器也会默认匹配上该行的记录。
        //需要修改成，如果没有该字段，不进行匹配操作。
        filter1.setFilterIfMissing(true);

        RegexStringComparator comp2 = new RegexStringComparator("value5");
        SingleColumnValueFilter filter2=new SingleColumnValueFilter(Bytes.toBytes("cf01"),Bytes.toBytes("name"), CompareFilter.CompareOp.EQUAL,comp2);

        filterList.addFilter(filter1);
        filterList.addFilter(filter2);


        //将filterList放在scan中
        scan.setFilter(filterList);
        ResultScanner scanner = table.getScanner(scan);
        Result result=null;
        while((result=scanner.next())!=null){
            for(Cell cell:result.rawCells()){
                String rowkey=Bytes.toString(CellUtil.cloneRow(cell));
                String name=Bytes.toString(CellUtil.cloneQualifier(cell));
                String value=Bytes.toString(CellUtil.cloneValue(cell));
                System.out.println(rowkey+" | "+name+" | "+value+" | ");
            }
        }
        table.close();

    }
}
