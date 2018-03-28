package com.tianwen.common.redisutil;

import com.tianwen.common.util.SerializeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/*
 * 
 * String	可以是字符串、整数或者浮点数	对整个字符串或者字符串的其中一部分执行操作；对象和浮点数执行自增(increment)或者自减(decrement)
   List	一个链表，链表上的每个节点都包含了一个字符串	从链表的两端推入或者弹出元素；根据偏移量对链表进行修剪(trim)；读取单个或者多个元素；根据值来查找或者移除元素
    Set	包含字符串的无序收集器(unorderedcollection)，并且被包含的每个字符串都是独一无二的、各不相同	添加、获取、移除单个元素；检查一个元素是否存在于某个集合中；计算交集、并集、差集；从集合里卖弄随机获取元素
    Hash	包含键值对的无序散列表	添加、获取、移除单个键值对；获取所有键值对
    Zset	字符串成员(member)与浮点数分值(score)之间的有序映射，元素的排列顺序由分值的大小决定	添加、获取、删除单个元素；根据分值范围(range)或者成员来获取元素

 */
@Component
public class RedisUtil {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

<<<<<<< HEAD

=======
/*	@Autowired
	private RwRedisTemplate<String,Object> rwRedisTemplate;*/
	
>>>>>>> 8367702c63d16eb2e72771d4ebeafe4b1df24fa2
	
	/*********************************************** Object ************************************************/
	
	/**
	 * 普通存储 不设定有效期
	 * @param key
	 * @param value
	 */
	public void setObject(String key, Object value){
		if(StringUtils.isBlank(key) || value == null) throw new NullPointerException("redis set key or value is null");
		redisTemplate.opsForValue().set(key, SerializeUtil.serialize(value));
	}
	
	/**
	 * 普通存储 有效期expire
	 * @param key
	 * @param value
	 * @param expire 毫秒级别
	 */
	public void setObject(String key, Object value, long expire){
		if(expire <= 0) setObject(key, value);
		if(StringUtils.isBlank(key) || value == null) throw new NullPointerException("redis set key or value is null");
		redisTemplate.opsForValue().set(key, SerializeUtil.serialize(value), expire, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 普通获取
	 * @param key
	 * @return
	 */
	public Object getObject(String key){
		if(StringUtils.isBlank(key)) throw new NullPointerException("redis get key is null");
		return SerializeUtil.deserialize((byte[]) redisTemplate.opsForValue().get(key));
	}
	
	/**
	 * 因为没找到del方法, 所以删除暂时用设置为空代替
	 * @param key
	 */
	public void delObject(String key){
		if(StringUtils.isBlank(key)) throw new NullPointerException("redis get key is null");
		redisTemplate.opsForValue().set(key, null);
	}
	
	/*********************************************** String ************************************************/
	
	/**
	 * 普通存储 不设定有效期
	 * @param key
	 * @param value
	 */
	public void 	setString(String key, String value){
		if(StringUtils.isBlank(key) || StringUtils.isBlank(value)) throw new NullPointerException("redis set key or value is null");
		redisTemplate.opsForValue().set(key, value);
	}
	
	/**
	 * 普通存储 有效期expire
	 * @param key
	 * @param value
	 * @param expire 毫秒级别
	 */
	public void setString(String key, String value, long expire){
		if(expire <= 0) setString(key, value);
		if(StringUtils.isBlank(key) || StringUtils.isBlank(value)) throw new NullPointerException("redis set key or value is null");
		redisTemplate.opsForValue().set(key, value, expire, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 普通获取
	 * @param key
	 * @return
	 */

	public String getString(String key){
		if(StringUtils.isBlank(key)) throw new NullPointerException("redis get key is null");
		return (String) redisTemplate.opsForValue().get(key);
	}
	
	
	/*********************************************** Map ************************************************/
	
	/**
	 * 存储key哈希表
	 * @param key
	 * @param value
	 */
	public void setHash(String key, HashMap<?, ?> value){
		if(StringUtils.isBlank(key) || value.isEmpty()) throw new NullPointerException("redis set key or value is null");
		redisTemplate.opsForHash().putAll(key, value);
	}
	
	/**
	 * 存储key哈希表 有效期expire 毫秒级
	 * @param key
	 * @param value
	 * @param expire
	 */
	public void setHash(String key, HashMap<?, ?> value, long expire){
		if(expire <= 0) setHash(key, value);
		if(StringUtils.isBlank(key) || value.isEmpty()) throw new NullPointerException("redis set key or value is null");
		redisTemplate.opsForHash().putAll(key, value);
		redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 存储key哈希表的field字段的值 不设置有效期
	 * @param key
	 * @param field
	 * @param value
	 */
	public void setHash(String key, String field, Object value){
		if(StringUtils.isBlank(key) || StringUtils.isBlank(key) || value == null) throw new NullPointerException("redis set key or value or table is null");
		redisTemplate.opsForHash().put(key, field, value);
	}
	
	/**
	 * 存储key哈希表的field字段的值 有效期expire 毫秒级
	 * @param key
	 * @param field
	 * @param value
	 * @param expire
	 */
	public void setHash(String key, String field, Object value, long expire){
		if(expire <= 0) setHash(key, field, value); 
		if(StringUtils.isBlank(key) || StringUtils.isBlank(field) || value == null) throw new NullPointerException("redis set key or value or table is null");
		redisTemplate.opsForHash().put(key, field, value);
		redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 获取key哈希表的所有字段值
	 * @param key
	 * @return
	 */
	public Map<?, ?> getHash(String key){
		if(StringUtils.isBlank(key)) throw new NullPointerException("redis get key is null");
		return redisTemplate.opsForHash().entries(key);
	}
	
	/**
	 * 获取key哈希表的field字段的值
	 * @param key
	 * @param field
	 * @return
	 */
	public Object getHash(String key, String field){
		if(StringUtils.isBlank(key) || StringUtils.isBlank(field)) throw new NullPointerException("redis get key or field is null");
		return redisTemplate.opsForHash().get(key, field);
	}
	
	/**
	 * 删除key哈希表的field字段的值
	 * @param key
	 * @param field
	 */
	public void delhash(String key, Object...field){
		if(StringUtils.isBlank(key) || field == null) throw new NullPointerException("redis get key or field is null");
		redisTemplate.opsForHash().delete(key, field);
	}
	
	/**
	 * key键在hash表field字段是否存在值
	 * @param key
	 * @param field
	 * @return
	 */
	public boolean hasHash(String key, String field){
		if(StringUtils.isBlank(key)) throw new NullPointerException("redis get key or field is null");
		return redisTemplate.opsForHash().hasKey(key, field);
	}
	
	/*********************************************** List ************************************************/
	
	/**
	 * 在数组最后添加元素
	 * @param key
	 * @param value
	 */
	public void setList(String key, Object value){
		if(StringUtils.isBlank(key)) throw new NullPointerException("redis set key or field is null");
		redisTemplate.opsForList().rightPush(key, value);
	}
	
	/**
	 * 在数组最后添加元素
	 * @param key
	 * @param value
	 * @param expire 有效期 毫秒级
	 */
	public void setList(String key, Object value, long expire){
		if(expire <= 0) setList(key, value); 
		if(StringUtils.isBlank(key)) throw new NullPointerException("redis set key or field is null");
		redisTemplate.opsForList().rightPush(key, value);
		redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 新建一个list
	 * @param key
	 * @param list
	 */
	public void setList(String key, List<?> list){
		if(StringUtils.isBlank(key)) throw new NullPointerException("redis set key or field is null");
		redisTemplate.opsForList().rightPushAll(key, list);
	}
	
	/**
	 * 新建一个list
	 * @param key
	 * @param list
	 * @param expire 有效期毫秒级
	 */
	public void setList(String key, List<?> list, long expire){
		if(expire <= 0) setList(key, list); 
		if(StringUtils.isBlank(key)) throw new NullPointerException("redis set key or field is null");
		redisTemplate.opsForList().rightPushAll(key, list);
		redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 根据下标得到数组元素
	 * @param key
	 * @param index
	 * @return
	 */
	public Object getList(String key, long index){
		if(StringUtils.isBlank(key)) throw new NullPointerException("redis get key or field is null");
		return redisTemplate.opsForList().index(key, index);
	}
	
	/**
	 * 根据起始下标得到数组元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<?> getList(String key, long start, long end){
		if(StringUtils.isBlank(key) || start < 0 || end < 0) throw new NullPointerException("redis get key or start < 0");
		return redisTemplate.opsForList().range(key, start, end);
	}
	
	/**
	 * 得到数组长度
	 * @param key
	 * @return
	 */
	public long getListSize(String key){
		return redisTemplate.opsForList().size(key);
	}
	
	/**
	 * 修改指定下标的元素
	 * @param key
	 * @param index
	 * @param value
	 * @return
	 */
	public boolean updList(String key, long index, Object value){
		redisTemplate.opsForList().set(key, index, value);
		return true;
	}
	
	/**
	 * 移除数组元素为value的count个元素
	 * @param key
	 * @param count
	 * @param value
	 * @return
	 */
	public long removeList(String key, long count, Object value){
		return redisTemplate.opsForList().remove(key, count, value);
	}
}
