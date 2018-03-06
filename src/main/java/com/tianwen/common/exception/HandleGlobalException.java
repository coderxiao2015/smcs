package com.tianwen.common.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianwen.common.util.JsonResponseResult;

@ControllerAdvice
public class HandleGlobalException {
	
	private JsonResponseResult result = JsonResponseResult.createFalied("");

	@ExceptionHandler(Exception.class)
	@ResponseBody
	JsonResponseResult handleGlobalException(Exception e){
		result.addData("未知异常!");
		return result;
	}
	
	@ExceptionHandler(UserException.class)
	ModelAndView UserException(UserException ue){
		ModelAndView mView = new ModelAndView();
		mView.addObject("套餐异常");
		mView.addObject(ue.getStackTrace());
		mView.setViewName("/error/user_error");
		return mView;
	}
	
	@ExceptionHandler(ProductException.class)
	@ResponseBody
	JsonResponseResult ProduceException(ProductException pe){
		result.addData(pe.getStackTrace());
		return result;
	}
	
}
