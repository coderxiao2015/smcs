package com.tianwen.common.exception;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianwen.common.util.JsonResponseResult;

@ControllerAdvice
public class HandleGlobalException {
	
	private JsonResponseResult result = JsonResponseResult.createFalied("");

	@ExceptionHandler(UserException.class)
	ModelAndView UserException(UserException ue){
		ue.printStackTrace();
		ModelAndView mView = new ModelAndView();
		mView.addObject("info", "套餐异常");
		mView.addObject("detail", ue.getStackTrace());
		mView.setViewName("/error/user_error");
		return mView;
	}
	
	@ExceptionHandler(ProductException.class)
	@ResponseBody
	JsonResponseResult ProduceException(ProductException pe){
		pe.printStackTrace();
		result.addData(pe.getStackTrace());
		return result;
	}
	

	/****************************************** 系统异常 ********************************************/
	@ExceptionHandler(Exception.class)
	@ResponseBody
	JsonResponseResult handleGlobalException(Exception e){
		e.printStackTrace();
		System.out.println(e.getStackTrace());
		result.addData("未知异常!");
		return result;
	}
	
	
	/**
	 * 下标越界
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	ModelAndView NullPointerException(NullPointerException e){
		e.printStackTrace();
		ModelAndView mView = new ModelAndView();
		mView.addObject("info", "空指针异常");
		mView.addObject("detail", e.getStackTrace());
		mView.setViewName("/error/user_error");
		return mView;
	}
	
	/**
	 * 下标越界
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	ModelAndView ArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException e){
		e.printStackTrace();
		ModelAndView mView = new ModelAndView();
		mView.addObject("下标越界异常");
		mView.addObject(e.getStackTrace());
		mView.setViewName("/error/user_error");
		return mView;
	}
	
	/**
	 * 运行时
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	ModelAndView RuntimeException(RuntimeException e){
		e.printStackTrace();
		e.printStackTrace();
		ModelAndView mView = new ModelAndView();
		mView.addObject("运行时异常");
		mView.addObject("detail", e.getStackTrace());
		mView.setViewName("/error/user_error");
		return mView;
	}
	
	/**
	 * 非法参数
	 * @param e
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	ModelAndView IllegalArgumentException(IllegalArgumentException e){
		e.printStackTrace();
		ModelAndView mView = new ModelAndView();
		mView.addObject("非法参数异常");
		mView.addObject(e.getStackTrace());
		mView.setViewName("/error/user_error");
		return mView;
	}
	
	/**
	 * 类型转换
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ClassCastException.class)
	ModelAndView ClassCastException(ClassCastException e){
		e.printStackTrace();
		ModelAndView mView = new ModelAndView();
		mView.addObject("类型转换异常");
		mView.addObject(e.getStackTrace());
		mView.setViewName("/error/user_error");
		return mView;
	}
	
	/**
	 * IO
	 * @param e
	 * @return
	 */
	@ExceptionHandler(IOException.class)
	ModelAndView IOException(IOException e){
		e.printStackTrace();
		ModelAndView mView = new ModelAndView();
		mView.addObject("IO异常");
		mView.addObject(e.getStackTrace());
		mView.setViewName("/error/user_error");
		return mView;
	}
	
	/**
	 * 没有匹配方法
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NoSuchMethodException.class)
	ModelAndView NoSuchMethodException(NoSuchMethodException e){
		e.printStackTrace();
		ModelAndView mView = new ModelAndView();
		mView.addObject("没有匹配方法异常");
		mView.addObject(e.getStackTrace());
		mView.setViewName("/error/user_error");
		return mView;
	}
	
	/**
	 * 数据库异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(SQLException.class)
	ModelAndView SQLException(SQLException e){
		e.printStackTrace();
		ModelAndView mView = new ModelAndView();
		mView.addObject("数据库异常");
		mView.addObject(e.getStackTrace());
		mView.setViewName("/error/user_error");
		return mView;
	}
	
	
	/************************************************************************************************/
	
}
