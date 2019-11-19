package com.he.job;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class SalaJob {
    public static void main(String[] args) {
        System.setProperty("HADOOP_USER_NAME", "root");
        //System.setProperty("hadoop.home.dir","/hadoopdata" );
        try {
            Configuration config = new  Configuration();
//
            config.set("fs.defaultFS", "hdfs://keduo:9000");

            config.set("yarn.resourcemanager.hostname", "keduo");
//
            Job job = Job.getInstance(config, "saleMapReduce");
            job.setJarByClass(SalaJob.class);
            //关联map 、reduce
            job.setMapperClass(SalaWorkMap.class);
            job.setReducerClass(SalaWorkReduce.class);
            //定义map输出
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(SalaWritable.class);
            //定义reduce输入
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(SalaWritable.class);
            //设置reduce task数量
            job.setNumReduceTasks(3);
            //设置数据源
            FileInputFormat.addInputPath(job,new Path("/a.txt"));
            //设置输出目录
            //先判断一下该目录是否存在，如果存在将该目录进行删除
            FileSystem fs=FileSystem.get(config);
            Path outDir=new Path("/mr_out_sale");
            if(fs.exists(outDir)){
                fs.delete(outDir,true);
            }
            FileOutputFormat.setOutputPath(job,outDir);
            //启动
            boolean b = job.waitForCompletion(true);
            if( b){
                System.out.println("完成了。。。。。。");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
