package com.tianwen.common.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

import com.tianwen.common.log.LogUtils;
import com.tianwen.common.shiro.token.TokenManager;
import com.tianwen.common.util.SysUtil;
import com.tianwen.core.user.entity.TMember;

/**
 * 登录拦截器
 * 
 * @author Administrator
 *
 */
public class LoginFilter extends AccessControlFilter {

	/**
	 * 登录身份鉴权 isAccessAllowed:是否允许访问，返回true表示允许 mappedValue:拦截器配置的参数
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		LogUtils.info(this.getClass(), "mappedValue:" + mappedValue);
		TMember member = TokenManager.getToken();

		if (!SysUtil.isEmpty(member)) {
			return Boolean.TRUE;
		}

		if (ShiroFilterUtils.isAjax(request)) {
			// TODO 页面提示
		}
		LogUtils.info(this.getClass(), "登录拦截器拦截");
		return Boolean.FALSE;
	}

	/**
	 * 登录身份鉴权失败拦截后经过这个方法 只有当isAccessAllowd和OnaccessDenied都true时程序将不再往下进行
	 * 一般如果isAccessAllowd拦截之后这个方法仅作为错误页面跳转来用 但是shiro默认是跳转到index页面,
	 * 所以如果想自定义跳转页面需要重写DEFAULT_LOGIN_URL属性
	 * onAccessDenied:表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了（
	 * 比如重定向到另一个页面）。
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		saveRequestAndRedirectToLogin(request, response);
		return Boolean.FALSE;
	}
}
