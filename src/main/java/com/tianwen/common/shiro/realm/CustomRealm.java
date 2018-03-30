package com.tianwen.common.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianwen.common.constant.SysConstant;
import com.tianwen.common.shiro.token.ShiroToken;
import com.tianwen.common.util.SysUtil;
import com.tianwen.core.user.entity.TMember;
import com.tianwen.core.user.service.UserService;

public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	
	public CustomRealm() {
		super();
	}
	
	@Autowired //注入父类的属性，注入加密算法匹配密码时使用
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher){
        super.setCredentialsMatcher(credentialsMatcher);
    }
	

	/**
	 * 
	 * 认证回调函数，登录信息和用户验证信息验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		ShiroToken shiroToken = (ShiroToken) token;
		TMember member = userService.findMemberByMobile(shiroToken.getUsername());
		if(SysUtil.isEmpty(member)){
			throw new AccountException("此账号不存在！");
		}else if(member.getStatus() == SysConstant.BLACK_USER_STATUS){
			throw new DisabledAccountException("该账号已被拉入黑名单，请联系管理员！");
		}else{
			member.setLastLogin(SysUtil.getTime());
			userService.updMemberByMid(member);
			usernamePasswordToken.setRememberMe(true);
		}
		return new SimpleAuthenticationInfo(member, member.getPassword(), getName());
	}
	
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {

		TMember tMember = (TMember) SecurityUtils.getSubject().getSession().getAttribute(SysConstant.SESSION_USERLOGINFO);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole(tMember.getStatus().toString().trim());
		return info;
	}

}
