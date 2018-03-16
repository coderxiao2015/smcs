package com.tianwen.common.session.manager;

import com.tianwen.common.session.CustomShiroSessionDAO;
import com.tianwen.common.session.ShiroSessionRepository;

/**
 *
 * session操作类
 * 
 */

public class SessionManager {

	ShiroSessionRepository shiroSessionRepository;

	CustomShiroSessionDAO customShiroSessionDAO;

	public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
		this.shiroSessionRepository = shiroSessionRepository;
	}

	public void setCustomShiroSessionDAO(CustomShiroSessionDAO customShiroSessionDAO) {
		this.customShiroSessionDAO = customShiroSessionDAO;
	}
}
