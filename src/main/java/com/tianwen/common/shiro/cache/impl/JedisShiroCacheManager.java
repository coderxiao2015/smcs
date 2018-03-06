package com.tianwen.common.shiro.cache.impl;

import org.apache.shiro.cache.Cache;

import com.tianwen.common.shiro.cache.JedisManager;
import com.tianwen.common.shiro.cache.JedisShiroCache;
import com.tianwen.common.shiro.cache.ShiroCacheManager;


public class JedisShiroCacheManager implements ShiroCacheManager {

    private JedisManager jedisManager;

    public <K, V> Cache<K, V> getCache(String name) {
        return new JedisShiroCache<K, V>(name, getJedisManager());
    }

    public void destroy() {
    	//如果和其他系统，或者应用在一起就不能关闭
    	//getJedisManager().getJedis().shutdown();
    }

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }
}
