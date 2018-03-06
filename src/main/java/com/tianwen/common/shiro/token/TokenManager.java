package com.tianwen.common.shiro.token;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.tianwen.core.user.entity.TMember;

public class TokenManager {

	
	/**
	 * 获取token里的用户对象
	 * @return
	 */
	public static TMember getToken(){
		return (TMember) SecurityUtils.getSubject().getPrincipal();
	}
	
	/**
	 * 登录
	 * @param user
	 * @param rememberMe
	 * @return
	 */
	public static TMember login(TMember user,Boolean rememberMe){
		ShiroToken token = new ShiroToken(user.getAccount(), user.getPassword());
		token.setRememberMe(rememberMe);
		SecurityUtils.getSubject().login(token);
		return getToken();
	}
	
	/**
	 * 获取当前用户的Session
	 * @return
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
	
	/**
	 * 退出登录
	 */
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
}
