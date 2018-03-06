package com.tianwen.common.shiro.manager.impl;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;

import com.tianwen.common.shiro.manager.ShiroManager;
import com.tianwen.common.util.config.INI4j;

public class ShiroManagerImpl implements ShiroManager {
	private Log logger = LogFactory.getLog(this.getClass());
	final static Class<? extends ShiroManagerImpl> CLAZZ = ShiroManagerImpl.class;

	// 注意/r/n前不能有空格
	private static final String CRLF = "\r\n";

	public String loadFilterChainDefinitions() {
		logger.info("shiro权限配置初始化开始------" + CLAZZ.getName());
		StringBuffer sb = new StringBuffer();
		sb.append(getFixedAuthRule());// 固定权限，采用读取配置文件
		return sb.toString();
	}

	/**
	 * 从配额文件获取固定权限验证规则串
	 */
	private String getFixedAuthRule() {
		String fileName = "shiro_base_auth.ini";
		ClassPathResource cp = new ClassPathResource(fileName);
		INI4j ini = null;
		try {
			ini = new INI4j(cp.getFile());
		} catch (IOException e) {
			logger.info(e + "\n加载文件出错。file:[%s]" + fileName);
		}
		String section = "base_auth";
		Set<String> keys = ini.get(section).keySet();
		StringBuffer sb = new StringBuffer();
		for (String key : keys) {
			String value = ini.get(section, key);
			sb.append(key).append(" = ").append(value).append(CRLF);
		}
		logger.info("\n加载权限文件成功。file:[%s]" + fileName);
		return sb.toString();
	}

}
