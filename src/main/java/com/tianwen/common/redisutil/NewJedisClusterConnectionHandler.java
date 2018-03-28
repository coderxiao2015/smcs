package com.tianwen.common.redisutil;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClusterConnectionHandler;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xiaoyi 2018-3-26
 * @version 根据上下文切换读写connection
 */
public abstract class NewJedisClusterConnectionHandler extends JedisClusterConnectionHandler{
    @Override
    public Jedis getConnectionFromNode(HostAndPort node) {
        return super.getConnectionFromNode(node);
    }

    public NewJedisClusterConnectionHandler(Set<HostAndPort> nodes, GenericObjectPoolConfig poolConfig, int connectionTimeout, int soTimeout) {
        super(nodes, poolConfig, connectionTimeout, soTimeout);
    }

    @Override
    public Map<String, JedisPool> getNodes() {
        return super.getNodes();
    }

    @Override
    public void renewSlotCache() {
        super.renewSlotCache();
    }

    @Override
    public void renewSlotCache(Jedis jedis) {
        super.renewSlotCache(jedis);
    }

    @Override
    protected List<JedisPool> getShuffledNodesPool() {
        return super.getShuffledNodesPool();
    }



}
