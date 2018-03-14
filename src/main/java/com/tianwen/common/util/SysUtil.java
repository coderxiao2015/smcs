package com.tianwen.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.beanutils.BeanMap;

public class SysUtil {

	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		} else {
			if (obj instanceof java.lang.String) {
				if ("".equals(obj.toString().trim())) {
					return true;
				}
			} else if (obj instanceof java.lang.StringBuffer) {
				if ("".equals(obj.toString().trim())) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getTime() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 格式大小写有区别
		String sysDatetime = fmt.format(rightNow.getTime());
		return sysDatetime;
	}

	public static String MD5(String pwd) {
		if (pwd == null)
			return null;
		if (pwd.trim().length() == 0)
			return pwd;
		Md5 md5 = new Md5();
		return md5.getMD5ofStr(pwd);
	}

	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static HashMap<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}
}
