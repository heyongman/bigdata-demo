package com.he.map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class RegionMap extends Mapper<LongWritable, Text, Text, LongWritable> {
    /**
     * @param key     偏移量
     * @param value   传过来的一行数据
     * @param context 内容
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (value != null) {
            String[] split = value.toString().split("\t");
//            不为空串就发送
            context.write(new Text(split[1]), new LongWritable(Integer.parseInt(split[2])));
        }
    }
}
