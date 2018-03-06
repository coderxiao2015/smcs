package com.tianwen.core.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianwen.common.exception.ProductException;
import com.tianwen.common.exception.UserException;
import com.tianwen.common.log.SystemLog;
import com.tianwen.common.shiro.token.ShiroToken;
import com.tianwen.common.shiro.token.TokenManager;
import com.tianwen.common.util.JsonResponseResult;
import com.tianwen.common.util.StringUtils;
import com.tianwen.common.util.SysUtil;
import com.tianwen.core.user.entity.TMember;
import com.tianwen.core.user.service.UserService;

import hep.aida.ref.Test;

@Scope("prototype")
@Controller
@RequestMapping(value = "/open")
public class OpenController {
	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserService userService;

	@GetMapping(value = "/adduser")
	public ModelAndView toAddUser() {
		return new ModelAndView("user/adduser");
	}

	@GetMapping(value = "/logSuccess")
	public ModelAndView toLoginSuccess() {
		return new ModelAndView("user/success");
	}

	@GetMapping(value = "/login")
	public ModelAndView toLogin() {
		logger.info("12345");
		return new ModelAndView("user/login");
	}

	@GetMapping(value = "usercenter")
	@SystemLog(opType="123", opDescription="456", opModel="789")
	public ModelAndView toUsercenter(){
		String a = null;
		if(StringUtils.isBlank(a)) throw new UserException("");
		TMember tMember = new TMember();
		tMember.setMid(60452);
		HashMap<String, Object> infoMap = userService.getPerCenterInfo(tMember);
		return new ModelAndView("/usercenter/perCenter", "map", infoMap);
		//throw new ProductException("用户错误");
		//throw new UserException("");
	}
	
	@GetMapping(value = "jspusercenter")
	public ModelAndView toJSPUsercenter(){
		TMember tMember = new TMember();
		tMember.setMid(60452);
		HashMap<String, Object> infoMap = userService.getPerCenterInfo(tMember);
		ModelAndView mView = new ModelAndView();
		mView.addObject("map", infoMap);
		mView.setViewName("/usercenter/jspPerCenter");
		//return new ModelAndView("/usercenter/jspPerCenter", "map", infoMap);
		return mView;
	}
	
	@GetMapping(value="test/{abc}/jjj/{efg}")
	@ResponseBody
	public JsonResponseResult test(@PathVariable("abc") String a, @PathVariable("efg") String b){
		System.out.println(a);
		System.out.println(b);
		JsonResponseResult result = JsonResponseResult.createSuccess();
		result.addData(a);
		result.addData(b);
		return result;
	}

	@PostMapping(value = "/addNewUser", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public JsonResponseResult addUser(TMember member) {
		JsonResponseResult result = null;
		try {

			member.setMobile("13726245005");
			member.setMType(15);
			member.setRegisterDate(SysUtil.getTime());
			member.setLastLogin(SysUtil.getTime());
			member.setName("吴宝丰");
			userService.addNewUser(member);
			result = JsonResponseResult.createSuccess();

		} catch (Exception e) {
			e.printStackTrace();
			result = JsonResponseResult.createFalied("");
		}
		return result;
	}

	@PostMapping(value = "/doLogin", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public JsonResponseResult doLogin(TMember member, HttpServletRequest request) {
		JsonResponseResult result = null;
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			TMember loginMember = userService.doLogin(member.getAccount(), SysUtil.MD5(member.getPassword()));
			if (!SysUtil.isEmpty(loginMember)) {
				TokenManager.login(loginMember, Boolean.TRUE);
				// 登录后，取到之前的Request中的一些信息。
				SavedRequest saveRequest = WebUtils.getSavedRequest(request);
				String url = null;
				if (null != saveRequest) {
					url = saveRequest.getRequestUrl();
				}
				/**
				 * 我们平常用的获取上一个请求的方式，在Session不一致的情况下是获取不到的 String url = (String)
				 * request.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
				 */
				logger.info("获取登录之前的URL:" + url);
				// 如果登录之前没有地址，那么就跳转到首页。
				if (StringUtils.isBlank(url)) {
					url = request.getContextPath() + "/";
				}
				// 跳转地址
				resultMap.put("back_url", url);
			}

			result = JsonResponseResult.createSuccess();
			result.addData(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonResponseResult.createFalied("");
		}
		return result;
	}
}
