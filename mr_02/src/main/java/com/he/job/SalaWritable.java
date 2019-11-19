package com.he.job;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class SalaWritable implements Writable {
    private Double price;
    private String name;
    private int num;
    private Double count;

    public SalaWritable() {
    }

    public SalaWritable(Double price, String name, int num, Double count) {
        this.price = price;
        this.name = name;
        this.num = num;
        this.count = count;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeDouble(this.price);
        out.writeUTF(this.name);
        out.writeInt(this.num);
        out.writeDouble(this.count);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.price = in.readDouble();
        this.name = in.readUTF();
        this.num = in.readInt();
        this.count = in.readDouble();

    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return price+"\t"+name+"\t"+num+"\t"+count;
    }
}
