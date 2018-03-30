package com.tianwen.common.redisutil;

import com.tianwen.base.util.PropsLoader;
import com.tianwen.common.util.StringUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.ClusterNodeInformation;
import redis.clients.util.JedisClusterCRC16;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class RedisComponetUtil {

    @Autowired
    private PropsLoader propsLoader;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    private List<ClusterNode> masterNodes=new ArrayList<>();
    private  List<ClusterNode> slaveNodes=new ArrayList<>();

    private HashMap<String,Object> slavePool=new HashMap<>();



    /**
     * 初始化集群信息
     */
    @PostConstruct
public void initClusterInfo(){
    String host= (String) propsLoader.props.get("redis.master1.ip");
    String port= (String) propsLoader.props.get("redis.master1.port");

    if(StringUtil.isBlank(host)|| StringUtil.isBlank(port)){
        throw  new NullPointerException("集群初始化失败");
    }

    JedisPool jedisPool=new JedisPool(jedisPoolConfig,host,Integer.valueOf(port));
       // JedisPool jedisPool=new JedisPool(host,Integer.valueOf(port));
    //创建单个jedis实例
    Jedis jedis=jedisPool.getResource();
    convertNodes(jedis);
}



   /*解析cluster nodes信息 转化为ClusterNode实例*/
   public void convertNodes(Jedis jedis){
    //   w.lock();
       try {
           String localNodes = jedis.clusterNodes();
           for (String nodeInfo : localNodes.split("\n")) {
               //判断是否为主节点
               if(nodeInfo.indexOf("master")>-1){
                    String[] nodeInfoPartArray = nodeInfo.split(" ");
                    ClusterNode clusterNode=new ClusterNode();
                    clusterNode.setParentId("0");
                    String[] hostAndPort=nodeInfoPartArray[1].split(":");
                    clusterNode.setHost(hostAndPort[0]);
                    clusterNode.setPort(Integer.valueOf(hostAndPort[1]));
                    clusterNode.setNodeName("master");
                    clusterNode.setCurrentId(nodeInfoPartArray[0]);
                    clusterNode.setConnected(nodeInfoPartArray[nodeInfoPartArray.length-2]);
                    String[] slotScope=nodeInfoPartArray[nodeInfoPartArray.length-1].split("-");
                    clusterNode.setStartSlot(Integer.valueOf(slotScope[0]));
                    clusterNode.setEndSlot(Integer.valueOf(slotScope[1]));
                    masterNodes.add(clusterNode);
               }else{
                   String[] nodeInfoPartArray = nodeInfo.split(" ");
                   ClusterNode clusterNode=new ClusterNode();
                   clusterNode.setParentId(nodeInfoPartArray[3]);
                   String[] hostAndPort=nodeInfoPartArray[1].split(":");
                   clusterNode.setHost(hostAndPort[0]);
                   clusterNode.setPort(Integer.valueOf(hostAndPort[1]));
                   clusterNode.setNodeName("slave");
                   clusterNode.setCurrentId(nodeInfoPartArray[0]);
                   clusterNode.setConnected(nodeInfoPartArray[nodeInfoPartArray.length-1]);
                   clusterNode.setStartSlot(0);
                   clusterNode.setEndSlot(0);
                   slaveNodes.add(clusterNode);
               }
           }
           /*主从节点映射*/
           matchMS();
       } finally {
        //   w.unlock();
       }
   }

   /*映射主从节点*/
   public void  matchMS(){
       HashMap<String,Object> tempMatchMap=new HashMap<>();
       if(masterNodes!=null){
            for(ClusterNode clusterNode:masterNodes){
                tempMatchMap.put(clusterNode.getCurrentId(),clusterNode);
            }
       }

       if(slaveNodes!=null){
            for(ClusterNode clusterNode:slaveNodes){
                if(tempMatchMap.containsKey(clusterNode.getParentId())){
                    //修改起止slot
                    ClusterNode tempMasterNode=(ClusterNode)tempMatchMap.get(clusterNode.getParentId());
                    clusterNode.setStartSlot(tempMasterNode.getStartSlot());
                    clusterNode.setEndSlot(tempMasterNode.getEndSlot());
                    slavePool.put(String.valueOf(clusterNode.getPort()),clusterNode);
                    //至此slave节点组动态获取完毕
                }
            }
       }

   }

/*公共方法*/
   public String  get(String keys){
       //通过被选中的jedis实例
       Jedis jedis = null;
       byte[] data = null;
       String result=null;
       try {
           if (!StringUtil.isBlank(keys)) {
               int slot = JedisClusterCRC16.getSlot(keys);
               if (slaveNodes != null) {
                   Iterator<ClusterNode> clusterNodeIterator = slaveNodes.iterator();
                   while (clusterNodeIterator.hasNext()) {
                       ClusterNode clusterNode = clusterNodeIterator.next();
                       if ((slot >= clusterNode.getStartSlot()) && (slot <= clusterNode.getEndSlot())) {
                           //被选中的jedis实例化
                           jedis = new Jedis(clusterNode.getHost(), clusterNode.getPort());
                           jedis.readonly();
                           byte[] kesby=keys.getBytes("UTF-8");
                           data=jedis.get(kesby);
                           if(data!=null){
                               result =(String)new JdkSerializationRedisSerializer().deserialize(data);
                           }
                           break;
                       }
                   }
               } else {
                   throw new NullPointerException("查询" + keys + "从节点null");
               }

           }
       }catch (JedisException je){
            je.printStackTrace();
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       } finally {
           if(jedis!=null)jedis.close();
       }
        return result;
   }



}
