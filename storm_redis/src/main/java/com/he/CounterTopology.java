package com.he;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;

public class CounterTopology {
    public static void main(String[] args) {
        try {
            TopologyBuilder builder = new TopologyBuilder();
            builder.setSpout("redisSpout", new RedisSpout("localhost",6379,"storm-redis"), 1);
            builder.setBolt("parseBolt", new ParseBolt(), 1).shuffleGrouping("redisSpout");
            builder.setBolt("printBolt", new PrintBolt(), 1).shuffleGrouping("parseBolt");

            Config config = new Config();
            config.setDebug(false);
            if (args != null && args.length > 0) {
//                集群模式
                config.setNumWorkers(1);
                StormSubmitter.submitTopology(args[0], config, builder.createTopology());
            } else {
//                本地模式
                config.setMaxTaskParallelism(3);
                LocalCluster cluster = new LocalCluster();
                cluster.submitTopology("storm-redis", config, builder.createTopology());
//                杀死进程
//                Thread.sleep(50000);
//                cluster.killTopology("storm-redis");
//                cluster.shutdown();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
