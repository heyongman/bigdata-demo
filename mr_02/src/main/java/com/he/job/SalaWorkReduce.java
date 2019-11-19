package com.he.job;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SalaWorkReduce extends Reducer<Text,SalaWritable,Text,SalaWritable>{
    @Override
    protected void reduce(Text key, Iterable<SalaWritable> values, Context context) throws IOException, InterruptedException {
        int result = 0;
        for (SalaWritable sw : values){
            context.write(key,sw);
        }
    }
}
