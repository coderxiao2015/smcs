package com.tianwen.common.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ShiroToken extends UsernamePasswordToken{
	
	private static final long serialVersionUID = -4406149723383575307L;
	
	/**免密登录*/
	public ShiroToken(String account) {
		super(account, "", false, null);
		this.type = LoginType.NOPASSWD;
	}
	
	/**账号密码登录*/
	public ShiroToken(String account, String password) {
		super(account, "", false, null);
		this.type = LoginType.PASSWORD;
	}

	public ShiroToken(String account, String password,LoginType type) {
		super(account, password);
		this.pwd = password;
		this.type = type;
	}
	
	private String pwd;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	private LoginType type;
	
	
	public LoginType getType() {
		return type;
	}

	public void setType(LoginType type) {
		this.type = type;
	}
}
