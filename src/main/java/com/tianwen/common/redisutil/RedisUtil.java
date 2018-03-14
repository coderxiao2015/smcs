package com.tianwen.common.redisutil;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
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
		     private RedisTemplate<String, ?>  redisTemplate;

		    
		      
		     //向redis中添加Map 
		      public void setMap(Map<?,?> value,String key) {  
	    	
			        redisTemplate.opsForHash().putAll(key,value);
					
			        
			     }  
		      //获取redis中 Map  根据key 
		      public Map< Object ,Object>getMap(String key) {  
			     
			        	
			        	return redisTemplate.opsForHash().entries(key);
			        
				 
			        
			     }  
		      
		      //向redis中添加list,注意即使key一样，但在redis中仍然会保存多个 
		      public void setList(List<?> value,String key) {  
			         try {
			        	
						 ListOperations listOperations=  redisTemplate.opsForList();
			             listOperations.leftPushAll(key, value);
					} catch (Exception e) {
						
						e.printStackTrace();
					}  
			        
			     }  
		      //向redis中 获取list,注意即使key一样，但在redis中仍然会保存多个
		      public Object getList(String key){
		    	
		    	 
		    	  return  redisTemplate.opsForList().range(key,0,-1);
		      }
		    
		     
}
