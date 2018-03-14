import com.google.gwt.thirdparty.guava.common.annotations.VisibleForTesting;
import com.tianwen.common.redisutil.RedisUtil;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

/*测试jedis*/
public class TestJedis {

    @Test
    public void testJedis(){
        RedisUtil redisUtil=new RedisUtil();
        HashMap<String,Object> aa=new HashMap<>();
        aa.put("aa","11");
        aa.put("aaa","22");
        redisUtil.setMap(aa,"aa");
        System.out.println(redisUtil.getMap("aa"));
        System.out.println(redisUtil.getMap("aa"));
    }
}
