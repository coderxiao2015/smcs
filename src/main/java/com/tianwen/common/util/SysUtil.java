package com.tianwen.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
}
