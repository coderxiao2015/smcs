package com.tianwen.common.redisutil;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisShardInfo;

import javax.annotation.Resource;

// 使用装饰器设计模式
public class RwRedisTemplate<K, V> {
    private JedisShardInfo masterJedisShardInfo;


    private RedisClusterConfiguration slaveJedisConfig;
    private RedisTemplate<K, V> delegate;
    // 记录delegate最后一次的状态
    private volatile boolean isMaster = true;

    public RwRedisTemplate(RedisTemplate<K, V> delegate) {
        this.delegate = delegate;
    }

    public void setSlaveJedisConfig(RedisClusterConfiguration slaveJedisConfig) {
        this.slaveJedisConfig = slaveJedisConfig;
    }

    public void setMasterJedisShardInfo(JedisShardInfo masterJedisShardInfo) {
        this.masterJedisShardInfo = masterJedisShardInfo;
    }




    ///////////////////////////////////////////string/////////////////////////////////////////////////////
    public void set(K key, V value) {
        switchToMaster();
        delegate.opsForValue().set(key, value);
    }

    public void set(K key, V value, long timeout, TimeUnit unit) {
        switchToMaster();
        delegate.opsForValue().set(key, value, timeout, unit);
    }

    public Boolean setIfAbsent(K key, V value) {
        switchToMaster();
        return delegate.opsForValue().setIfAbsent(key, value);
    }

    public void multiSet(Map<? extends K, ? extends V> m) {
        switchToMaster();
        delegate.opsForValue().multiSet(m);
    }

    public Boolean multiSetIfAbsent(Map<? extends K, ? extends V> m) {
        switchToMaster();
        return delegate.opsForValue().multiSetIfAbsent(m);
    }

    public V get(K key) {
        return delegate.opsForValue().get(key);
    }

    public V get(K key, boolean forceToMaster) {
        if(forceToMaster) {
            switchToMaster();
        } else{
            switchToSlave();
        }
        return delegate.opsForValue().get(key);
    }


    public V getAndSet(K key, V value) {
        switchToMaster();
        return delegate.opsForValue().getAndSet(key, value);
    }

    public List<V> multiGet(Collection<K> keys) {
        return multiGet(keys, false);
    }

    public List<V> multiGet(Collection<K> keys, boolean forceToMaster) {
        if(forceToMaster) {
            switchToMaster();
        } else {
            switchToSlave();
        }
        return delegate.opsForValue().multiGet(keys);
    }

    public Long increment(K key, long delta) {
        switchToMaster();
        return delegate.opsForValue().increment(key, delta);
    }

    public Double increment(K key, double delta) {
        switchToMaster();
        return delegate.opsForValue().increment(key, delta);
    }

    public Integer append(K key, String value) {
        switchToMaster();
        return delegate.opsForValue().append(key, value);
    }

    public String get(K key, long start, long end) {
        return get(key, start, end, false);
    }

    public String get(K key, long start, long end, boolean forceToMaster) {
        if(forceToMaster) {
            switchToMaster();
        } else {
            switchToSlave();
        }
        return delegate.opsForValue().get(key, start, end);
    }

    public void set(K key, V value, long offset) {
        switchToMaster();
        delegate.opsForValue().set(key, value, offset);
    }

    public Long size(K key) {
        switchToMaster();
        return delegate.opsForValue().size(key);
    }

    public Boolean setBit(K key, long offset, boolean value) {
        switchToMaster();
        return delegate.opsForValue().setBit(key, offset, value);
    }

    public Boolean getBit(K key, long offset) {
        return getBit(key, offset, false);
    }

    public Boolean getBit(K key, long offset, boolean forceToMaster) {
        if(forceToMaster) {
            switchToMaster();
        } else {
            switchToSlave();
        }
        return delegate.opsForValue().getBit(key, offset);
    }
    ///////////////////////////////////////////string/////////////////////////////////////////////////////

    ///////////////////////////////////////////key/////////////////////////////////////////////////////
    public Boolean hasKey(K key) {
        switchToSlave();
        return delegate.hasKey(key);
    }

    public void delete(K key) {
        switchToMaster();
        delegate.delete(key);
    }

    public void delete(Collection<K> key) {
        switchToMaster();
        delegate.delete(key);
    }

    public DataType type(K key) {
        switchToSlave();
        return delegate.type(key);
    }

    public Set<K> keys(K pattern) {
        switchToSlave();
        return delegate.keys(pattern);
    }
//
//  K randomKey();
//
//  void rename(K oldKey, K newKey);
//
//  Boolean renameIfAbsent(K oldKey, K newKey);
//
//  Boolean expire(K key, long timeout, TimeUnit unit);
//
//  Boolean expireAt(K key, Date date);
//
//  Boolean persist(K key);
//
//  Boolean move(K key, int dbIndex);
//
//  byte[] dump(K key);
//
//  void restore(K key, byte[] value, long timeToLive, TimeUnit unit);
//
//  Long getExpire(K key);
//
//  Long getExpire(K key, TimeUnit timeUnit);
//
//  void watch(K keys);
//
//  void watch(Collection<K> keys);
//
//  void unwatch();
//
//  /**
//   * '
//   */
//  void multi();
//
//  void discard();
    ///////////////////////////////////////////key/////////////////////////////////////////////////////

    private void switchToSlave() {
            JedisConnectionFactory redisConnectionFactory = switchToSlaveCnnection();
            switchRedisConnectionFactory(redisConnectionFactory);
            isMaster = false;
    }

    private void switchToMaster() {
        if(!isMaster) {
            JedisConnectionFactory redisConnectionFactory = switchToMasterConnection();
            switchRedisConnectionFactory(redisConnectionFactory);
            isMaster = true;
        }
    }

    private JedisConnectionFactory switchToMasterConnection() {
        JedisConnectionFactory redisConnectionFactory = (JedisConnectionFactory) delegate.getConnectionFactory();
        redisConnectionFactory.setShardInfo(masterJedisShardInfo);
        return redisConnectionFactory;
    }

    private JedisConnectionFactory switchToSlaveCnnection() {
        JedisConnectionFactory redisConnectionFactory = (JedisConnectionFactory) delegate.getConnectionFactory();
        redisConnectionFactory=new JedisConnectionFactory(slaveJedisConfig,redisConnectionFactory.getPoolConfig());
        redisConnectionFactory.afterPropertiesSet();
        return redisConnectionFactory;
    }

    // 多余
    private void switchRedisConnectionFactory(JedisConnectionFactory redisConnectionFactory) {
        delegate.setConnectionFactory(redisConnectionFactory);
    }

}
