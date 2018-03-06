package com.tianwen.common.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.tianwen.common.shiro.token.TokenManager;
import com.tianwen.common.util.SysUtil;
import com.tianwen.core.user.entity.TMember;

/**
 * 登录拦截器
 * @author Administrator
 *
 */
public class LoginFilter extends AccessControlFilter{
	private Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * 登录身份鉴权
	 * true拦截
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		TMember member = TokenManager.getToken();
		
		if(!SysUtil.isEmpty(member)){
			return Boolean.TRUE;
		}
		
		if(ShiroFilterUtils.isAjax(request)){
			//TODO 页面提示
		}
		logger.info("登录拦截器拦截");
		return Boolean.FALSE;
	}

	/**
	 * 登录身份鉴权失败拦截后经过这个方法
	 * 只有当isAccessAllowd和OnaccessDenied都true时程序将不再往下进行
	 * 一般如果isAccessAllowd拦截之后这个方法仅作为错误页面跳转来用
	 * 但是shiro默认是跳转到index页面, 所以如果想自定义跳转页面需要重写DEFAULT_LOGIN_URL属性
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		saveRequestAndRedirectToLogin(request, response);
		return Boolean.FALSE;
	}
	
}
