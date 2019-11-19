package com.he.job;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * wordReduce
 */
public class WordReduce extends Reducer<Text,LongWritable,Text,LongWritable>{
    /**
     *
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        Long result = 0L;
        for (LongWritable lw:values) {
            result+=lw.get();
        }
        context.write(key,new LongWritable(result));
    }
}
