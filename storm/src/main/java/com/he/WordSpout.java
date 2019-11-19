package com.he;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.Random;

public class WordSpout extends BaseRichSpout{

    String[] init_data = {"java ewew","hello storm"};

    SpoutOutputCollector collector;

    /**
     * 初始化
     * @param map
     * @param topologyContext
     * @param spoutOutputCollector
     */
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;
    }

    /**
     * 实例的数据来源是从上面的初始值来的，可以从kafka、flume。。
     */
    @Override
    public void nextTuple() {
//        拿到数据
        String init_datum = init_data[new Random().nextInt(init_data.length)];
//        拆分
        String[] split = init_datum.split(" ");
//        将拆分的数据发送到bolt
        for (String str:split) {
            collector.emit(new Values(str));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
    }
}
