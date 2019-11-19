package com.he.appendData;

import com.he.LineData;
import com.he.invoke.HDFSConnector;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class AppendDataToHDFS {
    /**
     * 目的文件
     */
    private static final String desPath = "/movie_data.txt";

    /**
     * 主方法
     * @param args
     */
    public static void main(String[] args) {
        try {
//            获取连接
            FileSystem fs = HDFSConnector.getCon();
            for (int i = 0; i < 1000; i++) {
//                获取票房数据
                InputStream is = new ByteArrayInputStream(LineData.getLineData().getBytes());
//                判断输出文件是否存在
                if (! fs.exists(new Path(desPath))){
                    fs.create(new Path(desPath));
                }
//                追加票房数据
                FSDataOutputStream fos = fs.append(new Path(desPath));
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
}
