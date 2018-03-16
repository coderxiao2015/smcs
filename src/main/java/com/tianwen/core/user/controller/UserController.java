package com.tianwen.core.user.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tianwen.common.shiro.token.TokenManager;
import com.tianwen.core.user.entity.TMember;
import com.tianwen.core.user.service.UserService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/userInfo")
	public ModelAndView toLoginSuccess(){
		return new ModelAndView("user/info");
	}
	
	@RequestMapping(value = "/toCouponList", method = RequestMethod.POST)
	public ModelAndView toCouponList(@RequestParam("pid") Integer pid,@RequestParam("quantity") Integer quantity){
		System.out.println("pid:"+pid+"------quantity:"+quantity);
		TMember tMember = TokenManager.getToken();
		//查询所有可用优惠券
		List<HashMap<String,Object>> vlist = userService.findValidCouponByMid(tMember.getMid());
		
		//查询所有已失效优惠券
		List<HashMap<String,Object>> ilist = userService.findInvalidCouponByMid(tMember.getMid());
		
		ModelAndView mView = new ModelAndView();
		mView.addObject("vlist",vlist);
		mView.addObject("ilist",ilist);
		mView.setViewName("/usercenter/couponList");
		return mView;
	}
	
	
}
