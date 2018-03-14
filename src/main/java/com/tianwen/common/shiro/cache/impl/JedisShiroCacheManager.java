package com.tianwen.common.shiro.cache.impl;

import org.apache.shiro.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.tianwen.common.shiro.cache.ShiroCacheManager;


public class JedisShiroCacheManager implements ShiroCacheManager {

  //  private JedisManager jedisManager;
    private RedisTemplate redisTemplate;
    private StringRedisTemplate stringRedisTemplate;

   public <K, V> Cache<K, V> getCache(String name) {
        return null;
    }

    public void destroy() {
    	//如果和其他系统，或者应用在一起就不能关闭
    	//getJedisManager().getJedis().shutdown();
    }

    /*public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }*/
    
    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate temp) {
        this.redisTemplate = temp;
    }
    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    public void setStringRedisTemplate(StringRedisTemplate temp) {
        this.stringRedisTemplate = temp;
    }
}
