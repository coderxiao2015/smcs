package com.tianwen.common.redisutil;

import com.sun.org.apache.bcel.internal.generic.NEW;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClusterCommand;
import redis.clients.jedis.JedisClusterConnectionHandler;
import redis.clients.jedis.JedisPool;

/**
 * 根据上下文状态修改connectionHandler
 */
public abstract class NewJedisCommond<T> extends JedisClusterCommand {

    /**
     *@param connectionHandler 自定义连接操作类
     */
    public NewJedisCommond(JedisClusterConnectionHandler connectionHandler, int maxRedirections) {
        super(connectionHandler, maxRedirections);
    }



    @Override
    public abstract Object execute(Jedis connection);



}
