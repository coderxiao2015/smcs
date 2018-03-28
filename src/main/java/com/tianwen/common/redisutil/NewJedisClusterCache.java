package com.tianwen.common.redisutil;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisClusterInfoCache;

public class NewJedisClusterCache extends JedisClusterInfoCache {

    public NewJedisClusterCache(GenericObjectPoolConfig poolConfig, int timeout) {
        super(poolConfig, timeout);
    }

    public NewJedisClusterCache(GenericObjectPoolConfig poolConfig, int connectionTimeout, int soTimeout) {
        super(poolConfig, connectionTimeout, soTimeout);
    }


}
