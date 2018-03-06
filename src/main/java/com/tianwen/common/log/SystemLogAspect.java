package com.tianwen.common.log;

import org.aspectj.lang.JoinPoint;
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

	// 切点配置在core层所有目录,代表业务层所有方法都可以加日志
	@Pointcut("annotation(com.tianwen.common.log.SystemLog)")
	public void systemLogCut() {

	}

	/**
	 * 前置通知
	 */
	@Before("systemLogCut()")
	public void doBefore(JoinPoint joinPoint) {
		System.out.println("doBefore:" + joinPoint.getSignature().getName());
	}

	/**
	 * 后置通知
	 */
	@After("systemLogCut()")
	public void doAfter(JoinPoint joinPoint) {
		System.out.println("doAfter:" + joinPoint.getSignature().getName());
	}

	/**
	 * 环绕通知
	 */
	@Around("systemLogCut()")
	public void doAround(JoinPoint joinPoint) {
		System.out.println("doAround:" + joinPoint.getSignature().getName());
	}

	/**
	 * 后置回调通知
	 * 
	 * @param joinpoint
	 */
	@AfterReturning("systemLogCut()")
	public void doAfterReturning(JoinPoint joinPoint) {
		System.out.println("doAfterReturning:" + joinPoint.getSignature().getName());
	}

	/**
	 * 后置异常通知
	 * 
	 * @param joinpoint
	 */
//	@AfterThrowing("systemLogCut()")
//	public void doAfterThrowing(JoinPoint joinPoint) {
//		System.out.println("doAfterThrowing:" + joinPoint.getSignature().getName());
//	}

}
