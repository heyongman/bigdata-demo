package com.he;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class RedisReceiver extends Receiver<String> {
    private String host;
    private int port;
    private String channel;
    private int timeout;
    private String pwd;


    public RedisReceiver(String host,int port,String channel,int timeout,String pwd) {
        super(StorageLevel.MEMORY_AND_DISK());
        this.host = host;
        this.port = port;
        this.channel = channel;
        this.timeout = timeout;
        this.pwd = pwd;
    }

    @Override
    public void onStart() {
        new Thread() {
            @Override
            public void run() {
                GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
                JedisPool jedisPool = new JedisPool(genericObjectPoolConfig,host,port,timeout,pwd);
                receive(jedisPool);
            }
        }.start();
    }

    @Override
    public void onStop() {

    }

    /**
     * Create a socket connection and receive data until receiver is stopped
     */
    private void receive(JedisPool pool) {
        JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
//                System.out.println("当前线程：" + Thread.currentThread().getName() + "监听队列:" + channel + ";收到变更消息:" + message);
                store(message);
            }
        };

        Jedis jedis = pool.getResource();
        try {
            jedis.subscribe(jedisPubSub, channel);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            jedis.close();
        }

    }


}
