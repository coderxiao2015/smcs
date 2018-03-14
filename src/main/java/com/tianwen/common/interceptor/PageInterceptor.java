package com.tianwen.common.interceptor;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.tianwen.common.util.SysUtil;


@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PageInterceptor implements Interceptor {

	private int pageSize;
	private int pageNo;

	/**
	 * 拦截器执行方法
	 * 
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// 分页拦截点在StatementHandler
		StatementHandler handle = (StatementHandler) invocation.getTarget();
		// StatementHandler接口的实现类BaseStatementHandler中mappedStatement包含了sql信息,
		// 但是是private 所以需要metaobject工具类去取
		MetaObject object = SystemMetaObject.forObject(handle);

		/**
		 * 这一段没搞明白
		 */
		while (object.hasGetter("h")) {
			Object obj = object.getValue("h");
			object = SystemMetaObject.forObject(obj);
		}

		while (object.hasGetter("target")) {
			Object obj = object.getValue("target");
			object = SystemMetaObject.forObject(obj);
		}

		/*************************************************/

		MappedStatement mappedStatement = (MappedStatement) object.getValue("delegate.mappedStatement");
		// mapper执行的sql语句的id
		String id = mappedStatement.getId();
		// 正则匹配所有以byPage结尾的id才执行分页操作
		if (id.matches(".+ByPage$")) {
			// 获取执行参数
			@SuppressWarnings("unchecked")
			HashMap<String, Object> param = (HashMap<String, Object>) handle.getBoundSql().getParameterObject();
			pageSize = SysUtil.isEmpty(param.get("pageSize")) ? 20 : (int) param.get("pageSize");
			pageNo = Integer.parseInt((String) param.get("pageNo"));

			String sql = handle.getBoundSql().getSql();
			String suffix = "SELECT * FROM (SELECT ROWNUM MIDTAB_ID,A.* FROM (";
			String prefix = ") A) MIDDLETABLE WHERE MIDDLETABLE.MIDTAB_ID > " + ((pageNo - 1) * pageSize) + " AND MIDDLETABLE.MIDTAB_ID <= " + (pageSize * pageNo);
			sql = suffix + sql + prefix;

			object.setValue("delegate.boundSql.sql", sql);
		}

		return invocation.proceed();
	}

	/**
	 * 拦截对象
	 * 
	 * @param object
	 * @return
	 */
	@Override
	public Object plugin(Object object) {
		return Plugin.wrap(object, this);
	}

	/**
	 * 拦截配置
	 * 
	 * @param properties
	 */
	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub

	}

}
