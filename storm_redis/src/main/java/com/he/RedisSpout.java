package com.he;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

import java.util.Map;

/**
 * redis订阅源
 */
public class RedisSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;
    private String channel;
    private String host;
    private int port;
    private String msg;

    public RedisSpout(String host, int port, String channel) {
        this.channel = channel;
        this.host = host;
        this.port = port;
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), host, port);
        ListenerThread listenerThread = new ListenerThread(jedisPool, channel);
        listenerThread.start();
    }

    @Override
    public void nextTuple() {
        collector.emit(new Values(msg));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("message"));
    }


    class ListenerThread extends Thread{
        JedisPool pool;
        String channel;
        public ListenerThread(JedisPool pool, String channel) {
            this.pool = pool;
            this.channel = channel;
        }
        @Override
        public void run() {
            JedisPubSub jedisPubSub = new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "监听队列:" + channel + ";收到变更消息:" + message);
                    msg=message;
                }

            };
            Jedis jedis = pool.getResource();
            try {
                jedis.subscribe(jedisPubSub, channel);
            }finally {
                jedis.close();
            }
        }

    }

}
