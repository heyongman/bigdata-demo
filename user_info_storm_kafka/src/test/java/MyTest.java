import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

public class MyTest {

    @Test
    public void test3(){
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.200.200", 6379, 2 * 60000, "1234");
        Jedis jedis = jedisPool.getResource();
        String q = jedis.hget("key","region");
        System.out.println(q);
    }
}
