package com.he.modules.dao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

/**
 * 连接redis的类
 */
@Component
public class UserInfoDao {
    /**
     * 连接池
     */
    private static JedisPool jedisPool;
    private static Jedis jedis;


    /**
     * 获取地区
     * @return
     */
    public JSONArray getRegion(){
        jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.200.200", 6379, 6000, "1234");
        jedis = jedisPool.getResource();

        Map<String, String> region = getInstance().hgetAll("region");
        JSONObject regionObj;
        JSONArray regionArr = new JSONArray();
        for (String str:region.keySet()) {
            regionObj = new JSONObject();
            regionObj.put("name",str);
            regionObj.put("value",region.get(str));
            regionArr.add(regionObj);
        }

        jedis.close();
        jedisPool.close();
        return regionArr;
    }

    /**
     * 获取浏览器
     * @return
     */
    public JSONArray getBrowser(){
        jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.200.200", 6379, 6000, "1234");
        jedis = jedisPool.getResource();

        Map<String, String> browser = getInstance().hgetAll("browser");
        JSONObject browserObj;
        JSONArray browserArr = new JSONArray();
        for (String str:browser.keySet()) {
            browserObj = new JSONObject();
            browserObj.put("name",str);
            browserObj.put("value",browser.get(str));
            browserArr.add(browserObj);
        }

        jedis.close();
        jedisPool.close();
        return browserArr;
    }
}
