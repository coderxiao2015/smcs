package com.tianwen.common.shiro.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.tianwen.common.log.LogUtils;
import com.tianwen.common.redisutil.RedisUtil;
import com.tianwen.common.util.SerializeUtil;

/**
 * 用jedisManager的jedis底层方法操作缓存 主要是要和shiro集成必须实现cache接口
 * jedis存储数据全部序列化存储
 * @author Administrator
 *
 * @param <K>
 * @param <V>
 */

@SuppressWarnings("unchecked")
public class JedisShiroCache<K, V> implements Cache<K, V> {

//	/**
//	 * 为了不和其他的缓存混淆，采用追加前缀方式以作区分
//	 */
//	private static final String REDIS_SHIRO_CACHE = "tw:";
//	/**
//	 * Redis 分片(分区)，也可以在配置文件中配置
//	 */
//	private static final int DB_INDEX = 1;

//	private JedisManager jedisManager;
	
	private RedisTemplate<String, Object> redisTemplate;

	private String name;

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public JedisShiroCache(String name) {
		this.name = name;
	}

	/**
	 * 自定义relm中的授权/认证的类名加上授权/认证英文名字
	 */
	public String getName() {
		if (name == null)
			return "";
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public V get(K key) throws CacheException {
//		byte[] byteKey = SerializeUtil.serialize(buildCacheKey(key));
//		byte[] byteValue = new byte[0];
		Object object = new Object();
		try {
		//	byteValue = jedisManager.getValueByKey(DB_INDEX, byteKey);
//			byteValue = jedisManager.getValueByKey(DB_INDEX, byteKey);
			object = getRedisTemplate().opsForValue().get(key);
		} catch (Exception e) {
			LogUtils.error(this.getClass(), "get value by cache throw exception" + e);
		}
		return (V) object;
	}

	public V put(K key, V value) throws CacheException {
		//V previos = get(key);
		try {
			//jedisManager.saveValueByKey(DB_INDEX, SerializeUtil.serialize(buildCacheKey(key)), SerializeUtil.serialize(value), -1);
			getRedisTemplate().opsForValue().set((String) key, value);
		} catch (Exception e) {
			LogUtils.error(this.getClass(), "put cache throw exception" + e);
		}
		return value;
	}

	public V remove(K key) throws CacheException {
		V previos = get(key);
		try {
			//jedisManager.deleteByKey(DB_INDEX, SerializeUtil.serialize(buildCacheKey(key)));
			getRedisTemplate().opsForValue().set((String) key, null);
		} catch (Exception e) {
			LogUtils.error(this.getClass(), "remove cache  throw exception" + e);
		}
		return previos;
	}

	public void clear() throws CacheException {
		// TODO--
	}

	public int size() {
		if (keys() == null)
			return 0;
		return keys().size();
	}

	public Set<K> keys() {
		// TODO
		return null;
	}

	public Collection<V> values() {
		// TODO
		return null;
	}

//	private String buildCacheKey(Object key) {
//		return REDIS_SHIRO_CACHE + getName() + ":" + key;
//	}

}
