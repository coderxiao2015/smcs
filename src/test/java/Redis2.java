import org.junit.Test;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.*;

public class Redis2 {


/*测试从节点组中读取数据*/
    @Test
    public void testRead(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(1);
        // 最大空闲数
        poolConfig.setMaxIdle(1);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
        poolConfig.setMaxWaitMillis(1000);
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.1.168", 7002));
        nodes.add(new HostAndPort("192.168.1.168", 7003));
        nodes.add(new HostAndPort("192.168.1.168", 7004));
        JedisCluster cluster = new JedisCluster(nodes, poolConfig);
        String name = cluster.get("wangwu");
        System.out.println("wangwu:"+name);
        Map<String, JedisPool> clusterNodes = cluster.getClusterNodes();
        System.out.println("集群信息");
        System.out.println(clusterNodes.entrySet());
        Iterator nodeI=clusterNodes.entrySet().iterator();
        while (nodeI.hasNext()){
            System.out.println(nodeI.next());
        }
        try {
            cluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*测试主节点组中存放数据*/
    @Test
    public void testWrite(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(1);
        // 最大空闲数
        poolConfig.setMaxIdle(1);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
        poolConfig.setMaxWaitMillis(1000);
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.1.168", 6379));
        nodes.add(new HostAndPort("192.168.1.168", 7000));
        nodes.add(new HostAndPort("192.168.1.168", 7001));
        JedisCluster cluster = new JedisCluster(nodes, poolConfig);
        //存数据
        cluster.set("wangwu","789");
        System.out.println("wangwu:"+cluster.get("wangwu"));
        Iterator iterator=nodes.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());

        }
        try {
            cluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
