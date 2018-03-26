package com.tianwen.common.redisutil;

import com.tianwen.base.util.PropsLoader;
import com.tianwen.common.util.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ClusterCommands;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xiaoyi
 * redis 基于Aspect注解的读写分离切面
 * 利用注解的方式动态的修改上下文中jedisPoolConfig
 */
@Component
@Aspect
public class RedisClusterAdapter implements ApplicationContextAware {


    private ApplicationContext ctx;

    @Autowired
    private PropsLoader propsLoader;

    //只读集群节点组
    private LinkedHashSet<RedisNode> clusterSlaveNodes=new LinkedHashSet<>();

    /*加载slave节点组*/
    @PostConstruct
    public void   init() {
        String isSuccess="failed";

        //SlaveNode1
        String slaveIp1 = propsLoader.props.getProperty("redis.slaver1.ip");
        String slavePort1 = propsLoader.props.getProperty("redis.slaver1.port");
        //SlaveNode2
        String slaveIp2 = propsLoader.props.getProperty("redis.slaver2.ip");
        String slavePort2 = propsLoader.props.getProperty("redis.slaver2.port");
        //SlvaeNode3
        String slaveIp3 = propsLoader.props.getProperty("redis.slaver3.ip");
        String slavePort3 = propsLoader.props.getProperty("redis.slaver3.port");

        if (StringUtils.isBlank(slaveIp1) || StringUtils.isBlank(slavePort1) || StringUtils.isBlank(slaveIp2)
                || StringUtils.isBlank(slavePort2) || StringUtils.isBlank(slaveIp3) || StringUtils.isBlank(slavePort3)) {
            throw new NullPointerException("RedisClusterAdapter 初始化节点null异常");
        }
       // Set<RedisNode>
        //slaveClusterSet
        RedisNode redisNode1=new RedisNode(slaveIp1,Integer.valueOf(slavePort1));
        RedisNode redisNode2=new RedisNode(slaveIp2,Integer.valueOf(slavePort2));
        RedisNode redisNode3=new RedisNode(slaveIp3,Integer.valueOf(slavePort3));


        clusterSlaveNodes.add(redisNode1);
        clusterSlaveNodes.add(redisNode2);
        clusterSlaveNodes.add(redisNode2);

    }

    //配置RedisClusterAdapter的方法切面
    @Pointcut(value = "execution(* com.tianwen.common.redisutil.*.*(..))")
    public void allMethod() {

    }

    //redis注解经过反射增强
    @Before("allMethod()")
    public void before(JoinPoint point)
    {
        Object target = point.getTarget();
        String method = point.getSignature().getName();

        Class classz = target.getClass();

        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = classz.getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(RedisSelector.class)) {
                RedisSelector data = m.getAnnotation(RedisSelector.class);
                String selectorName=data.selectorName();
                System.out.println(method+"方法的RedisSelector 属性是:"+selectorName);
                if(StringUtils.isBlank(selectorName)){
                    //默认master 节点读写缓存
                }
                if(selectorName.equals("slave")){


                }

              /*  JedisPool jedisPool = (JedisPool) ctx.getBean(data.selectorName());
                DynamicJedisPoolHolder.putJedisPool(jedisPool);*/
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.ctx=applicationContext;
    }
}


