package com.he;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;

public class App {
    /**
     * 下载文件
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        String url = "hdfs://192.168.200.10:9000";
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(url), configuration);
        InputStream is = fs.open(new Path("/b.png"));
        FileOutputStream fos = new FileOutputStream("D://b.png");
//         FSDataInputStream
        IOUtils.copyBytes(is, fos, 4096);
        System.out.println("传输完成");
        fos.close();
        is.close();
    }

    /**
     * 获取连接
     * @return
     * @throws Exception
     */
    public FileSystem getCon() throws Exception{
        String url = "hdfs://192.168.200.10:9000";
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication","1");
        FileSystem fs = FileSystem.get(URI.create(url), configuration,"root");
        return fs;
    }

    /**
     * 上传
     */
    @Test
    public void upload() throws Exception{
        FileSystem fs = getCon();
        fs.copyFromLocalFile(new Path("a.txt"),new Path("/a.txt"));
    }

    /**
     * 创建目录
     */
    @Test
    public void mkdir() throws Exception{
        FileSystem fs = getCon();
        fs.mkdirs(new Path("/heyongman"));
    }
    /**
     * 递归删除
     */
    @Test
    public void delete() throws Exception{
        FileSystem fs = getCon();
        fs.delete(new Path("/heyongamn"),true);
    }
    /**
     * 文件追加
     */
    @Test
    public void append() throws Exception{
        InputStream is = new FileInputStream("a.txt");
        FileSystem fs = getCon();
        FSDataOutputStream fos = fs.append(new Path("/a.txt"));
        IOUtils.copyBytes(is,fos,1024);
    }
}
