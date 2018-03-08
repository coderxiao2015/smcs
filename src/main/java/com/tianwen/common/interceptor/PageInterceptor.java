package com.tianwen.common.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PageInterceptor implements Interceptor{

	/**
	 * 拦截器执行方法
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//StatementHandler handle = 
		return null;
	}

	/**
	 * 拦截对象
	 * @param object
	 * @return
	 */
	@Override
	public Object plugin(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 拦截配置
	 * @param properties
	 */
	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}
	
}
