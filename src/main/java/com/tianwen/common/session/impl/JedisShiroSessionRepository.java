package com.tianwen.common.session.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.springframework.data.redis.core.RedisTemplate;

import com.tianwen.common.log.LogUtils;
import com.tianwen.common.session.ShiroSessionRepository;
import com.tianwen.common.util.SerializeUtil;
import com.tianwen.common.util.SysUtil;

public class JedisShiroSessionRepository implements ShiroSessionRepository {
	//private Log log = LogFactory.getLog(JedisShiroSessionRepository.class);

//	private static final int DB_INDEX = 1;

//	private JedisManager jedisManager;
//
//	public JedisManager getJedisManager() {
//		return jedisManager;
//	}
//
//	public void setJedisManager(JedisManager jedisManager) {
//		this.jedisManager = jedisManager;
//	}
	
	private RedisTemplate<String, Object> redisTemplate;
	
	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void setSession(Session session) {
		if (SysUtil.isEmpty(session) || SysUtil.isEmpty(session.getId()))
			throw new NullPointerException("shiro session is empty");

		try {
			

			//getJedisManager().saveValueByKey(DB_INDEX, serializeKey, serializeValue, (int) (session.getTimeout() / 1000));
			
			//redisUtil.setObject(new String(serializeKey, "UTF-8"), session, session.getTimeout());
			
			getRedisTemplate().opsForValue().set(fmtSessionKey(session.getId()), fmtSessionValue(session), session.getTimeout());
			
			LogUtils.info(this.getClass(), "sessionId:" + session.getId());
			
		} catch (InvalidSessionException e) {
			e.printStackTrace();
			LogUtils.error(this.getClass(), "setSession error:" + session.getId());
		} catch (Exception e) {
			LogUtils.error(this.getClass(), "setSession error:" + session.getId());
			e.printStackTrace();
		}
	}

	@Override
	public void delSession(Serializable sessionId) {
		if (SysUtil.isEmpty(sessionId))
			throw new NullPointerException("shiro sessionId is empty");

		try {
			//getJedisManager().deleteByKey(DB_INDEX, SerializeUtil.serialize(sessionId));
			//String key = new String(SerializeUtil.serialize(sessionId), "UTF-8");
			//redisUtil.delObject(key);
			getRedisTemplate().opsForValue().getOperations().delete(fmtSessionKey(sessionId));
			
		} catch (Exception e) {
			LogUtils.error(this.getClass(), "delSession error:" + sessionId);
			e.printStackTrace();
		}
	}

	@Override
	public Session getSession(Serializable sessionId) {
		if (SysUtil.isEmpty(sessionId))
			throw new NullPointerException("shiro sessionId is empty");

		Session session = null;

		try {
			//byte[] serializeValue = getJedisManager().getValueByKey(DB_INDEX, SerializeUtil.serialize(sessionId));
			//session = SerializeUtil.deserialize(serializeValue, Session.class);
			
			//String key = new String(SerializeUtil.serialize(sessionId), "UTF-8");
			//session = (Session) redisUtil.getObject(key);
			//String key = new String(SerializeUtil.serialize(sessionId), "UTF-8");
				String value = getRedisTemplate().opsForValue().get(fmtSessionKey(sessionId)).toString();
			session = deFmtSessionValue(value);
			
		} catch (Exception e) {
			LogUtils.error(this.getClass(), "getSession error:" + sessionId);
			e.printStackTrace();
		}
		return session;
	}

	@Override
	public Collection<Session> getAllSessions() {
		Collection<Session> collection = null;

//		try {
//			//collection = getJedisManager().getAllSession(DB_INDEX);
//			collection = getRedisTemplate().
//		} catch (Exception e) {
//			LogUtils.error(this.getClass(), "getAllSessions error");
//			e.printStackTrace();
//		}

		return collection;
	}
	
	private String fmtSessionKey(Serializable sessionId){
		String value = "";
		try {
			value = new String(SerializeUtil.serialize(sessionId), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LogUtils.error(this.getClass(), "SessionKey format error:" + e.getStackTrace());
			e.printStackTrace();
		}
		return value;
	}
	
	private String fmtSessionValue(Session session){
		String value = "";
		try {
			value = new String(SerializeUtil.serialize(session), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LogUtils.error(this.getClass(), "SessionValue format error:" + e.getStackTrace());
			e.printStackTrace();
		}
		return value;
	}
	
	private Session deFmtSessionValue(String value){
		return (Session) SerializeUtil.deserialize(value.getBytes());
	}

}
