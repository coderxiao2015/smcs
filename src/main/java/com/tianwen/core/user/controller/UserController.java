package com.tianwen.core.user.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Scope("prototype")
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@GetMapping(value = "/userInfo")
	public ModelAndView toLoginSuccess(){
		return new ModelAndView("user/info");
	}
	
//	@GetMapping(value = "/addNewUser")
//	public String addUser(TMember tMember){
//		JsonResponseResult result = null;
//		try {
//			
//			
//			
//		} catch (Exception e) {
//			
//		}
//	}
	
}
