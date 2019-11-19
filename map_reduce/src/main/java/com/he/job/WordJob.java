package com.he.job;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordJob {
    /**
     * 主方法
     *
     * @param args
     */
    public static void main(String[] args) {
        System.setProperty("HADOOP_USER_NAME", "root");
        try {
//            创建job
            Configuration conf = new Configuration();

            conf.set("fs.defaultFS", "hdfs://keduo:9000");
            conf.set("yarn.resourcemanager.hostname", "keduo");

            Job job = Job.getInstance(conf);
//            给job取个名字
            job.setJobName("mr_word_job");
//            设置主类
            job.setJarByClass(WordJob.class);

//            关联map
            job.setMapperClass(WordMap.class);
//            指定map输出类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(LongWritable.class);

//            关联reduce
            job.setReducerClass(WordReduce.class);
//            制定reduce输出类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(LongWritable.class);
//            制定来源
            FileInputFormat.addInputPath(job,new Path("/word.txt"));

//            制定输出地
            FileSystem fileSystem = FileSystem.get(conf);
//            解决输出目录已存在
            Path outPath = new Path("/wc_out");
            if (fileSystem.exists(outPath)){
                fileSystem.delete(outPath,true);
            }

            FileOutputFormat.setOutputPath(job,outPath);
//            启动
            boolean b = job.waitForCompletion(true);//是否查看详情
            if (b){
                System.out.println("任务完成");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
