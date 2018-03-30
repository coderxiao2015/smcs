package com.tianwen.common.constant;

/**
 * 用于常量定义类
 * 
 * @author
 * @date
 */

@SuppressWarnings("serial")
public class SysConstant {
	public static final String CONTEXT_PATH = "contextPath";/***项目根路径*/
	
	public static final int BLACK_USER_STATUS = 0;
	
	public static final String SESSION_USERLOGINFO = "session_userlogininfo";
	public static final String TW_ISMEMBER = "tw003"; // 是否登陆过

	/*图片路径*/
	public static final String IMAGE_PATH="http://sale.51towin.com";

	/*common*/
	public static final String WEB_BROWSER = "web_browser";

	/*pcpt项目*/
	public static  final  String PCPT="http://p.51towin.com";
	//public static  final  String PCPT="http://coder-xiao-1993.oicp.io:15843";
	/*toothPt*/
	public static  final  String TOOTHPT="http://s.51towin.com";
	//public static  final  String TOOTHPT="http://18eb075862.iask.in:25267";

	//market
	public static final  String MARKER="http://localhost:8030";

	public static final String TW_PARENETOPENID = "tw002"; // 分享人的openId
	public static final String TW_ISSALE = "twIssale"; // 是否是直销人员
	public static final String TW_OPENID = "tw001"; // 自己的openId
	public static final String TW_ISFANS = "isfans";
	public static final String TW_UNIONID = "tw004"; //unionid


	/**
	 * Cookie生命周期
	 */
	public static final int COOKIE_LIFRCYCLE_DELETE = 0; // 立即删除
	public static final int COOKIE_LIFRCYCLE_NOWADAY = -1; // 无生命周期
	public static final int COOKIE_LIFRCYCLE_AWEEK = 60 * 60 * 24 * 7; // 一周
	public static final int COOKIE_LIFRCYCLE_AMONTH = 60 * 60 * 24 * 30; // 30天
	public static final int COOKIE_LIFRCYCLE_HOUR = 60 * 30; // 半小时
	public static final int COOKIE_LIFRCYCLE_THREEDAY = 60 * 60 * 24 * 3; // 三天

}
