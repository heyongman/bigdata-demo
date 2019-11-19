package com.he;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;
import java.util.Random;

/**
 * 生成并上传票房的类
 */
public class MovieDataGenerator {

    public synchronized static void main(String[] args) {
        try {
//            获取连接
            FileSystem fs = getCon();
            for (int i = 0; i < 1000; i++) {
//                获取票房数据
                InputStream is = new ByteArrayInputStream(getMovieData().getBytes());
//                追加票房数据
                FSDataOutputStream fos = fs.append(new Path("/movie_data.txt"));
//                传输
                IOUtils.copyBytes(is,fos,1024);
                is.close();
                fos.close();
                Thread.sleep(1000);
            }
//            关闭连接
            fs.close();
//            System.out.println("传输完成！");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     * 获取连接
     * @return
     * @throws Exception
     */
    public static FileSystem getCon() throws Exception{
        String url = "hdfs://192.168.200.200:9000";
        Configuration configuration = new Configuration();
        configuration.set("fs.hdfs.impl",org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
//        configuration.set("dfs.replication","3");
        FileSystem fs = FileSystem.get(URI.create(url), configuration,"root");
        return fs;
    }
    /**
     * 生成票房数据
     * @return
     */
    public static String getMovieData(){
//        电影名单
        String[] movieArray = {"情圣","铁道飞虎","酒国英雄之摆渡人","血战钢锯岭","那年夏天你去了哪","冰雪女皇之冬日魔","你好，疯子","罗曼蒂克消亡史"};
//        地区名单
        String[] regionArray = {"成都","北京","上海","广州","重庆","福建","深圳","杭州","天津"};
//        票价范围
        int[] fareArray = {20,25,30};
//        循环添加数据
        Random random = new Random();
        String movieData = movieArray[random.nextInt(movieArray.length)] + "\t" +
                regionArray[random.nextInt(regionArray.length)] + "\t" +
                fareArray[random.nextInt(fareArray.length)] + "\n";
        return movieData;
    }


}
