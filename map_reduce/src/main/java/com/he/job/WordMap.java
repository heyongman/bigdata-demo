package com.he.job;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Mapper中的四个泛型参数，
 * 前面两个表示是：hadoop在调用自定义map类的时候，给的入参类型
 * 后面两个表示是：map类在处理完之后要给reduce的出去的类型
 */
public class WordMap extends Mapper<LongWritable,Text,Text,LongWritable>{
    /**
     *
     * @param key keyIn
     * @param value value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        使用空格分隔
        String[] split = value.toString().split(" ");
        for (String str:split) {
            if (str.length()>0){
//                不为空串就发送
                context.write(new Text(str),new LongWritable(1));
            }
        }

    }
}
