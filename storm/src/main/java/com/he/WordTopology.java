package com.he;

import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;

import java.util.HashMap;

public class WordTopology {
    public static void main(String[] args) {
//        调用主api
        TopologyBuilder builder = new TopologyBuilder();
//        build关联spout和bolt
        builder.setSpout("mySpout",new WordSpout());
        builder.setBolt("myBolt",new WordBolt()).shuffleGrouping("mySpout");
        builder.setBolt("myPrintBolt",new PrintBolt()).shuffleGrouping("myBolt");

//        本地发布
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("HelloStorm",new HashMap(),builder.createTopology());

    }
}
