package com.tianwen.common.redisutil;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
/*
 * 
 * String	可以是字符串、整数或者浮点数	对整个字符串或者字符串的其中一部分执行操作；对象和浮点数执行自增(increment)或者自减(decrement)
   List	一个链表，链表上的每个节点都包含了一个字符串	从链表的两端推入或者弹出元素；根据偏移量对链表进行修剪(trim)；读取单个或者多个元素；根据值来查找或者移除元素
    Set	包含字符串的无序收集器(unorderedcollection)，并且被包含的每个字符串都是独一无二的、各不相同	添加、获取、移除单个元素；检查一个元素是否存在于某个集合中；计算交集、并集、差集；从集合里卖弄随机获取元素
    Hash	包含键值对的无序散列表	添加、获取、移除单个键值对；获取所有键值对
    Zset	字符串成员(member)与浮点数分值(score)之间的有序映射，元素的排列顺序由分值的大小决定	添加、获取、删除单个元素；根据分值范围(range)或者成员来获取元素

 */
public class StringRedisUtil {

		     private StringRedisTemplate  stringRedisTemplate;
		     
		     /**
		      * 向Hash中添加值
		      * @param key      可以对应数据库中的表名
		       * @param field    可以对应数据库表中的唯一索引
		      * @param value    存入redis中的值
		      */
		     public void set(String key, String field, String value) {
		         if(key == null || "".equals(key)){
		             return ;
		         }
		        
					stringRedisTemplate.opsForHash().put(key, field, value);
				
		   
		     }
		     
		     /**  不需要指定过期时间 key -value
		      * 向Hash中添加值
		      * @param key      可以对应数据库中的表名
		       * @param field    可以对应数据库表中的唯一索引
		      * @param value    存入redis中的值
		      * 
		      */
		     public void set(String key, String value) {
		         if(key == null || "".equals(key)){
		             return ;
		         }
		         stringRedisTemplate.opsForValue().set(key, value);//
				    
		     }
		     /**
		      * 从redis中取出值
		      * @param key
		
		      * @return
		      */
		     public String get(String key){
		         if(key == null || "".equals(key)){
		             return null;
		         }
		         return (String) stringRedisTemplate.opsForValue().get(key);
		     }
		     
		     
		     /**
		      * 向Hash中添加值
		      * @param key      可以对应数据库中的表名
		       * @param field    可以对应数据库表中的唯一索引
		      * @param value    存入redis中的值
		      * time 过期时间
		      */
		     public void set(String key, String value,int time) {
		         if(key == null || "".equals(key)){
		             return ;
		         }
		         stringRedisTemplate.opsForValue().set(key, value,time,TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间  
				    
		     }
		     
		    
		     /**
		      * 从redis中取出值
		      * @param key
		      * @param field
		      * @return
		      */
		     public String get(String key, String field){
		         if(key == null || "".equals(key)){
		             return null;
		         }
		         return (String) stringRedisTemplate.opsForHash().get(key, field);
		     }
		     
		     /**
		      * 判断 是否存在 key 以及 hash key
		      * @param key
		      * @param field
		      * @return
		      */
		     public boolean exists(String key, String field){
		         if(key == null || "".equals(key)){
		             return false;
		         }
		         return stringRedisTemplate.opsForHash().hasKey(key, field);
		     }
		     
		     /**
		      * 查询 key中对应多少条数据
		      * @param key
		      * @return
		      */
		     public long size(String key) {
		         if(key == null || "".equals(key)){
		             return 0L;
		         }
		         return stringRedisTemplate.opsForHash().size(key);
		     }
		     
		     /**
		      * 删除
		      * @param key
		      * @param field
		      */
		     public void del(String key, String field) {
		         if(key == null || "".equals(key)){
		             return;
		         }
		         stringRedisTemplate.opsForHash().delete(key, field);
		     }
		 
		     /**
		      * 删除
		      * @param key
		    
		      */
		     public void del(String key) {
		         if(key == null || "".equals(key)){
		             return;
		         }
		         stringRedisTemplate.opsForHash().delete(key);
		     }
		     
		   
		    
		   
		  
		     
}
