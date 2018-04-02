package redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 测试redis的管道基数
 */
public class Pipe {

    /*非管道技术测试*/
    @Test
    public void test(){
        HostAndPort hostAndPort=new HostAndPort("127.0.0.1",6379);
        Set<HostAndPort> set=new HashSet<>();
        set.add(hostAndPort);
        JedisCluster jedisCluster=new JedisCluster(set);

        long startTime=System.currentTimeMillis();
        /*测试同时写入一千个数据*/
        System.out.println("开始时间=="+startTime);
        for(int i=0;i<100000;i++){
            jedisCluster.set("key"+i,""+i);
        }
        long endTime=System.currentTimeMillis();
        System.out.println("结束时间=="+endTime);
        long miTime=endTime-startTime;
        System.out.println("执行时间："+miTime);
    }

    /*Pipelining管道技术测试*/
    @Test
    public void testPipe(){
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("127.0.0.1", 6379));

        JedisCluster jc = new JedisCluster(nodes);

        JedisClusterPipeline jcp = JedisClusterPipeline.pipelined(jc);
        jcp.refreshCluster();
        List<Object> batchResult = null;
        long s = System.currentTimeMillis();
        try {
            // batch write
            for (int i = 0; i < 100000; i++) {
                jcp.set("k" + i, "v1" + i);
            }
           /* jcp.sync();

            // batch read
            for (int i = 0; i < 10000; i++) {
                jcp.get("k" + i);
            }
            batchResult = jcp.syncAndReturnAll();*/
        } finally {
            jcp.close();
        }

        // output time
        long t = System.currentTimeMillis() - s;
        System.out.println(t);


        // 实际业务代码中，close要在finally中调，这里之所以没这么写，是因为懒
        try {
            jc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
