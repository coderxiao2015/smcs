package com.tianwen.common.session;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

public interface ShiroSessionRepository {

	/**
	 * 存储session
	 * @param session
	 */
	void setSession(Session session);
	
	/**
	 * 删除session
	 * @param sessionId
	 */
	void delSession(Serializable sessionId);
	
	/**
	 * 获取session
	 * @param sessionId
	 * @return 
	 */
	Session getSession(Serializable sessionId);
	
	/**
	 * 获取所有session
	 * @return
	 */
	Collection<Session> getAllSessions();
	
}
