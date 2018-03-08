package com.tianwen.common.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemLogAspect {

	private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

	//核心业务controller层日志
	@Pointcut(value = "execution(* com.tianwen.core..*Controller.*(..))")
	public void ControllerLogCut() {

	}
	
	//核心业务service层日志
	@Pointcut(value = "execution(* com.tianwen.core..*Impl.*(..))")
	public void ServiceLogCut() {

	}
	
	//核心业务dao层日志
	@Pointcut(value = "execution(* com.tianwen.core..*Dao.*(..))")
	public void DaoLogCut() {

	}

	/**
	 * 前置通知
	 */
	@Before("ControllerLogCut()")
	public void doControllerBefore(JoinPoint joinPoint) {
		System.out.println("doControllerBefore:" + joinPoint.getSignature().getName());
	}
	
	/**
	 * 前置通知
	 */
	@Before("ServiceLogCut()")
	public void doServiceBefore(JoinPoint joinPoint) {
		System.out.println("doServiceBefore:" + joinPoint.getSignature().getName());
	}

//	/**
//	 * 后置通知
//	 */
//	@After("ControllerLogCut()")
//	public void doControllerAfter(JoinPoint joinPoint) {
//		System.out.println("doAfter:" + joinPoint.getSignature().getName());
//	}
//
//	/**
//	 * 环绕通知
//	 * @throws Throwable 
//	 */
////	@Around("ControllerLogCut()")
////	public void doControllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
////		System.out.println("doAround1:" + joinPoint.getSignature().getName());
////		joinPoint.proceed();
////		System.out.println("doAround2:" + joinPoint.getSignature().getName());
////	}
//
//	/**
//	 * 后置回调通知
//	 * 
//	 * @param joinpoint
//	 */
//	@AfterReturning("ControllerLogCut()")
//	public void doControllerAfterReturning(JoinPoint joinPoint) {
//		System.out.println("doAfterReturning:" + joinPoint.getSignature().getName());
//	}
//
//	/**
//	 * 后置异常通知
//	 * 
//	 * @param joinpoint
//	 */
//	@AfterThrowing("ControllerLogCut()")
//	public void doControllerAfterThrowing(JoinPoint joinPoint) {
//		System.out.println("doAfterThrowing:" + joinPoint.getSignature().getName());
//	}
	
	

}
