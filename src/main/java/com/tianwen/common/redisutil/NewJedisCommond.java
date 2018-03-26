package com.tianwen.common.redisutil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClusterCommand;
import redis.clients.jedis.JedisClusterConnectionHandler;
import redis.clients.jedis.JedisPool;

/**
 * 根据上下文状态修改connectionHandler
 */

public class NewJedisCommond extends JedisClusterCommand {


    public NewJedisCommond(JedisClusterConnectionHandler connectionHandler, int maxRedirections) {

        super(connectionHandler, maxRedirections);
    }

    @Override
    public Object execute(Jedis connection) {
        return null;
    }

    /*get的写配置*/
    public JedisPool writePool(){
        JedisPool jedisPool=new JedisPool();
        return jedisPool;
    }

}
