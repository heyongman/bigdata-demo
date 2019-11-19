package com.he.job;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SalaWorkMap extends Mapper<LongWritable,Text,Text,SalaWritable>{

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.判断value是否有值
        if(value !=null && value.getLength()>0){
            String[] splits = value.toString().split("\t");
            System.out.println(value.toString());
            //数据校验
            SalaWritable salaWritable = new SalaWritable();
            salaWritable.setPrice(Double.parseDouble(splits[2]));
            salaWritable.setName(splits[1]);
            salaWritable.setNum(Integer.parseInt(splits[3]));
            salaWritable.setCount(Double.parseDouble(splits[4]));

            context.write(new Text(splits[0]),salaWritable);
            }

        }

}
