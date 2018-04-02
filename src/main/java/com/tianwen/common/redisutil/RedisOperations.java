package com.tianwen.common.redisutil;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */
public interface RedisOperations<K, V> {

    /**
     * 获取字符串
     * @param var1 key
     */
     String getString(String var1 );

    /**
     * 获取list
     * @param  var1:key
     *         var2:start
     *         var3:end
     */
    List<String> range(String var1, long var2, long var4);

    /**
     * 获取list某个值
     * @param var1:key var2 :index
     */
    Object index(String var1, long var2);


    /**
     * 获取set
     * @param var1:key
     */
    Object pop(String var1);


    /*获取zset*/


}
