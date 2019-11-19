package com.he;


public class RunRedis {

    public static void main(String[] args) {
        RedisReceiver redisReceiver = new RedisReceiver("202.193.72.250", 6379, "Ecard_rec", 2000, "GuetRedis123");
        redisReceiver.onStart();


    }
}
