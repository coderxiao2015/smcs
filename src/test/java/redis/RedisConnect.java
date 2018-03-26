package redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Administrator on 2018/3/25.
 * 测试redis的连接
 */
public class RedisConnect {

    /**
     * 测试redis连接
     * 主节点write
     */
    @Test
    public void testWriteConnect(){
        JedisPoolConfig config=new JedisPoolConfig();
        Set<HostAndPort> materHostAndPorts=new LinkedHashSet<>(3);
        materHostAndPorts.add(new HostAndPort("127.0.0.1",6379));
        materHostAndPorts.add(new HostAndPort("127.0.0.1",7000));
        materHostAndPorts.add(new HostAndPort("127.0.0.1",7001));

        JedisCluster cluster = new JedisCluster(materHostAndPorts, config);

        cluster.set("zhangs","123456");
        System.out.println(cluster.get("zhangs"));
        try {
            cluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试redis连接
     * 从节点只读
     */
    @Test
    public void testReadConnect(){
        JedisPoolConfig config=new JedisPoolConfig();
        Set<HostAndPort> materHostAndPorts=new LinkedHashSet<>(3);
        materHostAndPorts.add(new HostAndPort("127.0.0.1",7002));
        materHostAndPorts.add(new HostAndPort("127.0.0.1",7003));
        materHostAndPorts.add(new HostAndPort("127.0.0.1",7004));

        JedisCluster cluster = new JedisCluster(materHostAndPorts, config);

        System.out.println(cluster.get("zhangs"));
        try {
            cluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
