package com.tianwen.common.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ShiroToken extends UsernamePasswordToken{
	
	private static final long serialVersionUID = -4406149723383575307L;

	public ShiroToken(String account, String password) {
		super(account, password);
		this.pwd = password;
	}
	
	private String pwd;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
