package com.he.reduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.*;


public class MovieReduce extends Reducer<Text, LongWritable, Text, LongWritable> {
    private SortedMap<LongWritable, Text> movieTreeMap = new TreeMap<>();

    /**
     * @param key     键
     * @param values  值
     * @param context 内容
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        Long result = 0L;
        for (LongWritable lw : values) {
            result += lw.get();
        }
//         向set里添加数据
        movieTreeMap.put(new LongWritable(result),new Text(key));
        if (movieTreeMap.size() > 3) {
//            超过三个就移除最小的
            movieTreeMap.remove(movieTreeMap.firstKey());
        }
    }

    /**
     * 最后执行的方法
     *
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (Map.Entry<LongWritable,Text> entry : movieTreeMap.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            context.write((Text) value,(LongWritable) key);
        }
    }
}
