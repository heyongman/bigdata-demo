package com.he.produceDemo;


import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class SimpleKafkaPartitioner implements Partitioner{

    /**
     *
     * @param s
     * @param o
     * @param bytes
     * @param o1
     * @param bytes1
     * @param cluster
     * @return
     */
    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        Integer topicNum = cluster.partitionCountForTopic(s);
        if (o1!=null){
            if (o1.toString().startsWith("111")){
                return 0;
            }
        }
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
