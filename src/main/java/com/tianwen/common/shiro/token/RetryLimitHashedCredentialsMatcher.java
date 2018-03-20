package com.tianwen.common.shiro.token;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		ShiroToken shiroToken = (ShiroToken) token;
	
		 if(shiroToken.getType().equals(LoginType.NOPASSWD))
			 return true;
		 
		return super.doCredentialsMatch(token, info);
	}
	
}
