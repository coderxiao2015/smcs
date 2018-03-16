package com.tianwen.common.shiro.manager.impl;

import java.io.IOException;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;

import com.tianwen.common.log.LogUtils;
import com.tianwen.common.shiro.manager.ShiroManager;
import com.tianwen.common.util.config.INI4j;

public class ShiroManagerImpl implements ShiroManager {
	final static Class<? extends ShiroManagerImpl> CLAZZ = ShiroManagerImpl.class;

	// 注意/r/n前不能有空格
	private static final String CRLF = "\r\n";

	public String loadFilterChainDefinitions() {
		LogUtils.info(this.getClass(), "shiro权限配置初始化开始------" + CLAZZ.getName());
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
			LogUtils.info(this.getClass(), e + "\n加载文件出错。file:" + fileName);
		}
		String section = "base_auth";
		Set<String> keys = ini.get(section).keySet();
		StringBuffer sb = new StringBuffer();
		for (String key : keys) {
			String value = ini.get(section, key);
			sb.append(key).append(" = ").append(value).append(CRLF);
		}
		LogUtils.info(this.getClass(), "\n加载权限文件成功。file:" + fileName);
		return sb.toString();
	}

}
