package com.he.job;

import com.he.map.RegionMap;
import com.he.reduce.RegionReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RegionJob {
    /**
     * 主方法
     * @param args
     */
    public static void main(String[] args) {
        System.setProperty("HADOOP_USER_NAME", "root");
        try {
//            创建job
            Configuration conf = new Configuration();

            conf.set("fs.defaultFS", "hdfs://master:9000");
            conf.set("yarn.resourcemanager.hostname", "master");

            Job job = Job.getInstance(conf);
//            给job取个名字
            job.setJobName("region_bo_job");
//            设置主类
            job.setJarByClass(RegionJob.class);

//            关联map
            job.setMapperClass(RegionMap.class);
//            使用combiner组件
            job.setCombinerClass(RegionReduce.class);
//            指定map输出类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(LongWritable.class);

//            关联reduce
            job.setReducerClass(RegionReduce.class);
//            设置reduce数量
//            job.setNumReduceTasks(3);
//            制定reduce输出类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(LongWritable.class);
//            制定来源
            FileInputFormat.addInputPath(job,new Path("/movie_data.txt"));

//            制定输出地
            FileSystem fileSystem = FileSystem.get(conf);
//            解决输出目录已存在
            Path outPath = new Path("/region_bo_out");
            if (fileSystem.exists(outPath)){
                fileSystem.delete(outPath,true);
            }

            FileOutputFormat.setOutputPath(job,outPath);
//            启动
            boolean b = job.waitForCompletion(true);//是否查看详情
//            if (b){
//                System.out.println("任务完成");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
