package com.tianwen.common.interceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tianwen.base.util.PropsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tianwen.common.constant.SysConstant;
import com.tianwen.common.shiro.token.TokenManager;
import com.tianwen.common.util.AESUtil;
import com.tianwen.common.util.CookieUtil;
import com.tianwen.common.util.StringUtil;
import com.tianwen.common.util.SysUtil;
import com.tianwen.core.user.entity.TMember;
import com.tianwen.core.user.service.UserService;

public class SessionCheckInterceptor implements HandlerInterceptor {


	 @Autowired
	 private UserService userService;
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("************ preHandle executed**********");
        TMember tMember = TokenManager.getToken();
		if(!SysUtil.isEmpty(tMember)){
			System.out.println("用户已经登录");
			return Boolean.TRUE;
		}
		System.out.println("用户未登录");
		String mid = AESUtil.decrypt(CookieUtil.getCookieValue(arg0, SysConstant.TW_ISMEMBER));
		System.out.println("cookie中获取的mid========"+mid);
		if(StringUtil.isBlank(mid)){
			//跳转到注册页面开始注册
			arg1.sendRedirect(SysConstant.PCPT+"/content/user.register.do");
			return false;
		}else{
			tMember =  userService.findMemberByMid(StringUtil.isBlank(mid) ? null : new Integer(mid));
			TokenManager.login(tMember, Boolean.TRUE);
		}

		
		return Boolean.TRUE;
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("************ postHandle executed**********");
	}

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("************ afterCompletion executed**********");
	}
}