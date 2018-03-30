package com.tianwen.common.session.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.data.redis.core.RedisTemplate;

import com.tianwen.common.log.LogUtils;
import com.tianwen.common.session.ShiroSessionRepository;
import com.tianwen.common.util.SysUtil;

public class JedisShiroSessionRepository implements ShiroSessionRepository {
	// private Log log = LogFactory.getLog(JedisShiroSessionRepository.class);

	// private static final int DB_INDEX = 1;

	// private JedisManager jedisManager;
	//
	// public JedisManager getJedisManager() {
	// return jedisManager;
	// }
	//
	// public void setJedisManager(JedisManager jedisManager) {
	// this.jedisManager = jedisManager;
	// }

	private static final String KEY = "TW_SESSION";

	private RedisTemplate<String, Session> redisTemplate;

	public RedisTemplate<String, Session> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Session> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void setSession(Session session) {
		if (SysUtil.isEmpty(session) || SysUtil.isEmpty(session.getId()))
			throw new NullPointerException("shiro session is empty");

		try {

			// getJedisManager().saveValueByKey(DB_INDEX, serializeKey,
			// serializeValue, (int) (session.getTimeout() / 1000));

			// redisUtil.setObject(new String(serializeKey, "UTF-8"), session,
			// session.getTimeout());
			// System.out.println(fmtSessionKey(session.getId()));

			// getRedisTemplate().opsForValue().set(session.getId().toString(),
			// sessionToByte(session), session.getTimeout());
			redisTemplate.boundHashOps(KEY).put(session.getId().toString(), session);
			redisTemplate.expire(KEY, session.getTimeout(), TimeUnit.MILLISECONDS);

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
			// getJedisManager().deleteByKey(DB_INDEX,
			// SerializeUtil.serialize(sessionId));
			// String key = new String(SerializeUtil.serialize(sessionId),
			// "UTF-8");
			// redisUtil.delObject(key);
			// getRedisTemplate().opsForValue().getOperations().delete(fmtSessionKey(sessionId));
			redisTemplate.boundHashOps(KEY).put(sessionId.toString(), null);

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
			// byte[] serializeValue = getJedisManager().getValueByKey(DB_INDEX,
			// SerializeUtil.serialize(sessionId));
			// session = SerializeUtil.deserialize(serializeValue,
			// Session.class);

			// String key = new String(SerializeUtil.serialize(sessionId),
			// "UTF-8");
			// session = (Session) redisUtil.getObject(key);
			// String key = new String(SerializeUtil.serialize(sessionId),
			// "UTF-8");
			// System.out.println(fmtSessionKey(sessionId));
			// byte[] bytes = (byte[])
			// getRedisTemplate().opsForValue().get(sessionId.toString());

			Object object = redisTemplate.boundHashOps(KEY).get(sessionId.toString());

			if (SysUtil.isEmpty(object))
				return null;

			session = (Session) object;

		} catch (Exception e) {
			LogUtils.error(this.getClass(), "getSession error:" + sessionId);
			e.printStackTrace();
		}
		return session;
	}

	@Override
	public Collection<Session> getAllSessions() {
		Collection<Session> collection = null;

		try {
			
			Set<Object> set = redisTemplate.boundHashOps(KEY).keys();
			System.err.println(set);
			System.out.println(set.size());
			if(set.size() > 0){
				for (Object object : set) {
					String key = (String) object;
					System.out.println(key);
					Object session = redisTemplate.boundHashOps(KEY).get(key);
					if(SysUtil.isEmpty(session)){
						collection.add((Session) session);
					}
				}
			}

		} catch (Exception e) {
			LogUtils.error(this.getClass(), "getAllSessions error");
			e.printStackTrace();
		}

		return collection;
	}

	// 把session对象转化为byte保存到redis中
	public byte[] sessionToByte(Session session) {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		byte[] bytes = null;
		try {
			ObjectOutput oo = new ObjectOutputStream(bo);
			oo.writeObject(session);
			bytes = bo.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	// 把byte还原为session
	public Session byteToSession(byte[] bytes) {
		ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
		ObjectInputStream in;
		SimpleSession session = null;
		try {
			in = new ObjectInputStream(bi);
			session = (SimpleSession) in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return session;
	}

}
