package com.tianwen.common.redisutil;

import com.tianwen.common.util.StringUtil;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.JedisClusterCRC16;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

@Component
public class RedisOperationsImpl implements RedisOperations {

    private RedisSerializer<?> defaultSerializer;

    private ClassLoader classLoader;

    private RedisSerializer valueSerializer = null;


    @PostConstruct
    public void init(){
        this.defaultSerializer = new JdkSerializationRedisSerializer(this.classLoader != null?this.classLoader:this.getClass().getClassLoader());
    }



    @Override
    public String getString(String keys) {
        //通过被选中的jedis实例
        Jedis jedis = null;
        byte[] data = null;
        String result=null;
        try {
            if (!StringUtil.isBlank(keys)) {
                int slot = JedisClusterCRC16.getSlot(keys);
                if ( RedisComponetUtil.getSlaveNodes()!= null) {
                    Iterator<ClusterNode> clusterNodeIterator = RedisComponetUtil.getSlaveNodes().iterator();
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
                                System.out.println(result);
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
            if(jedis!=null)
                //jedis.flushAll();
                jedis.close();
        }
        return result;
    }



    @Override
    public List<String> range(String keys, long s, long e) {
        //通过被选中的jedis实例
        Jedis jedis = null;
        List<byte[]> data = null;
        List<String> result=null;
        try {
            if (!StringUtil.isBlank(keys)) {
                int slot = JedisClusterCRC16.getSlot(keys);
                if ( RedisComponetUtil.getSlaveNodes()!= null) {
                    Iterator<ClusterNode> clusterNodeIterator = RedisComponetUtil.getSlaveNodes().iterator();
                    while (clusterNodeIterator.hasNext()) {
                        ClusterNode clusterNode = clusterNodeIterator.next();
                        if ((slot >= clusterNode.getStartSlot()) && (slot <= clusterNode.getEndSlot())) {
                            //被选中的jedis实例化
                            jedis = new Jedis(clusterNode.getHost(), clusterNode.getPort());
                            jedis.readonly();
                            byte[] keysBytes=keys.getBytes("UTF-8");
                            data=jedis.lrange(keysBytes,s,e);
                            if(data!=null){
                                //将获取的data数据转化为byte[] 数组
                               result=(List<String>) SerializationUtils.deserialize(data, this.defaultSerializer);
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
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } finally {
            if(jedis!=null)
                //jedis.flushAll();
                jedis.close();
        }
        return result;
    }

    @Override
    public Object index(String var1, long var2) {
        return null;
    }

    @Override
    public Object pop(String var1) {
        return null;
    }
}
