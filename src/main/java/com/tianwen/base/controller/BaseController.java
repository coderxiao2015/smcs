package com.tianwen.base.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tianwen.common.session.manager.SessionManager;

@Controller
@Scope("protopype")
public class BaseController {

	protected final static Log logger = LogFactory.getLog(BaseController.class);
	
	@Autowired
	SessionManager sessionManager;
	
	private Session session;
	
	public static String URL404 = "/error/404";
	
	public BaseController() {
		Subject subject = SecurityUtils.getSubject();
		session = subject.getSession();
	}

	public ModelAndView redirect(String redirectUrl, Map<String, Object>... parament) {
		ModelAndView view = new ModelAndView(new RedirectView(redirectUrl));
		if (null != parament && parament.length > 0) {
			view.addAllObjects(parament[0]);
		}
		return view;
	}
	
	public ModelAndView redirect404(){
		return new ModelAndView(new RedirectView(URL404));
	}
	
	public static HttpSession getSession(HttpServletRequest request){
		return request.getSession();
	}
	
	public void setSession(String key, Object value){
		session.setAttribute(key, value);
	}
	
	public Object getSession(String key){
		return session.getAttribute(key);
	}
}
