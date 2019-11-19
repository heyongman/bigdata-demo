import com.he.modules.dao.UserInfoDao;
import net.sf.json.JSONArray;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.Map;

public class MyTest {
    @Test
    public void test1(){
        UserInfoDao userInfoDao = new UserInfoDao();
        JSONArray stringStringMap = userInfoDao.getRegion();
        System.out.println(stringStringMap);

    }
    @Test
    public void test2(){
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.200.200", 6379, 6000, "1234");
        Jedis jedis = jedisPool.getResource();
        Map<String, String> region = jedis.hgetAll("region");
        System.out.println(region);
    }
}
