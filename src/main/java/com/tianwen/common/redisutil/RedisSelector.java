package com.tianwen.common.redisutil;

import java.lang.annotation.*;

/**
 * Created by xiaoyi
 * redis读写分离注解
 * 迪米特法则，无侵入性控制redis读写分离功能
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
@Documented
public @interface RedisSelector {
    //redis master-slave 选择其中一个作为查询的集群组，默认是master 集群组
    String selectorName() default "master";
}
