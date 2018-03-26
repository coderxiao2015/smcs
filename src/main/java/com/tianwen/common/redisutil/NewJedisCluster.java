package com.tianwen.common.redisutil;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisClusterCommand;

import java.util.Set;

public class NewJedisCluster extends JedisCluster {

    private QueryContextHolder contextHolder=new QueryContextHolder();

    //set 注入
    public void setContextHolder(QueryContextHolder contextHolder) {
        this.contextHolder = contextHolder;
    }

    public NewJedisCluster(Set<HostAndPort> nodes) {
        super(nodes);
    }

    /*根据上下文操作状态重载set方法 读写操作（后续需要添加时候添加默认读写都可以）*/


    /*根据上下文状态重载get方法 只读操作*/
    @Override
    public String get(final String key) {
        return new JedisClusterCommand<String>(connectionHandler, maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                if(contextHolder.getOp()==0){ //只读 切换connection连接器到slave节点
                    //重点设置从节点只读属性，所有的操作都是为这个做辅助
                    connection.readonly();
                }
                return connection.get(key);
            }
        }.run(key);
    }
}
