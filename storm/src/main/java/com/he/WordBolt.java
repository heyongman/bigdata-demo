package com.he;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

public class WordBolt extends BaseRichBolt {

    Map<String,Long> resultMap;

    OutputCollector collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        resultMap = new HashMap<>();
        collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
//        拿到每一个tuple》值列表
        String word = tuple.getStringByField("word");
        if (resultMap.get(word)!=null){
            resultMap.put(word,resultMap.get(word)+1L);
        }else {
            resultMap.put(word,1L);
        }
        collector.emit(new Values(resultMap));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("resultMap"));
    }
}
