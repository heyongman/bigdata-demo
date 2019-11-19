package com.he;

import org.apache.commons.lang.StringUtils;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

public class UserInfoBolt extends BaseRichBolt {
    private static OutputCollector collector;
    private static JedisPool jedisPool;
    private static final String regionField = "region";
    private static final String browserfield = "browser";


    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        collector = outputCollector;
//        连接jedis
        jedisPool = new JedisPool(new JedisPoolConfig(), "master", 6379, 2 * 60000, "1234");
    }

    @Override
    public void execute(Tuple tuple) {
        //1.拿到每一个tuple----值列表
        String str = tuple.getString(0);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@一行：" + str);
//        连接jedis
        Jedis jedis = jedisPool.getResource();

        if (StringUtils.isNotBlank(str)) {
            String[] cells = str.split("\t");
            if (cells.length<3){
                return;
            }
//            System.out.println("region:" + cells[1] + "---browser:" + cells[2]);
//            获取地区并转换
            String region = AddressUtils.getAddressByIP(cells[1]);
//            获取浏览器
            String browser = cells[2];
//            统计地区
            if (region!=null){
                String sr = jedis.hget(regionField, region);
                if (sr != null) {
                    jedis.hset(regionField,region, (Integer.parseInt(sr) + 1) + "");
                } else {
                    jedis.hset(regionField,region, 1 + "");
                }
            }
//              统计浏览器
            if (browser!=null){
                String sb = jedis.hget(browserfield, browser);
                if (sb != null) {
                    jedis.hset(browserfield,browser, (Integer.parseInt(sb) + 1) + "");
                } else {
                    jedis.hset(browserfield,browser, 1 + "");
                }
            }

//            发射
//            collector.emit(new Values(resultMap));
        }
        //汇报给storm,该tuple执行成功
        collector.ack(tuple);
//        关闭连接
        jedis.close();
        jedisPool.close();
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
//        outputFieldsDeclarer.declare(new Fields("resultMap"));
    }
}
