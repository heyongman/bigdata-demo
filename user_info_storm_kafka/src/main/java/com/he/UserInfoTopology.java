package com.he;

import kafka.api.OffsetRequest;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;


public class UserInfoTopology {
    private static String topicName = "userInfo";
    private static String zkRoot = "/brokers/topics/"+topicName;

    public static void main(String[] args) {

        BrokerHosts hosts = new ZkHosts("master:2181,slave0:2181,slave1:2181");


        SpoutConfig spoutConfig = new SpoutConfig(hosts,topicName,zkRoot, "userInfoId");
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        spoutConfig.startOffsetTime = OffsetRequest.LatestTime();
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafkaSpout",kafkaSpout);
        builder.setBolt("userInfoBolt", new UserInfoBolt()).shuffleGrouping("kafkaSpout");
//        builder.setBolt("userInfoPrintBolt",new UserInfoPrintBolt(),2).shuffleGrouping("userInfoBolt");

        Config conf = new Config();
        conf.setDebug(true);
        conf.setNumWorkers(5);
        if(args != null && args.length > 0) {
            try {
                StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            } catch (AuthorizationException e) {
                e.printStackTrace();
            }

        } else {
            conf.setMaxSpoutPending(3);

            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("userInfoTopology", conf, builder.createTopology());
        }
    }
}
