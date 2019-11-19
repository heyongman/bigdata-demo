package com.he;

import org.apache.storm.trident.operation.BaseAggregator;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Values;

import java.util.*;

public class Aggregate extends BaseAggregator<Map<String, Integer>> {
    @Override
    public Map<String, Integer> init(Object batchId, TridentCollector collector) {
        return new HashMap<>();
    }

    @Override
    public void aggregate(Map<String, Integer> map, TridentTuple tuple, TridentCollector collector) {
        String word = tuple.getStringByField("word");
        if (map.containsKey(word)) {
            int num = map.get(word);
            map.put(word, num + 1);
        } else {
            map.put(word, 1);
        }
    }

    @Override
    public void complete(Map<String, Integer> val, TridentCollector collector) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(val.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        for (int i = 0; i < 5 && i < list.size(); i++) {
            collector.emit(new Values(String.valueOf(i + 1), list.get(i).getKey(), list.get(i).getValue().toString()));
        }
    }
}
