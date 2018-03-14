package com.tianwen.common.util;

import javax.annotation.PostConstruct;

import com.tianwen.base.util.PropsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;


@Component
public class PublicAccount {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private static String appID;//wxf55ae9b01364b2a7 //wxbdf595b236d85a1c //wx44a26b8a7ef26809
	private static String appsecret;//83914cdd6c7db891a7a8832f0a39196d //93abbe487d77f294c7cbcbd7e8e99f25 //ce5575b427a386ce491e2549a515c4b0

	@Autowired
    private PropsLoader propsLoader;
	
	@PostConstruct
 	public void init() {
		appID = propsLoader.props.getProperty("appID");
		appsecret = propsLoader.props.getProperty("appsecret");
		logger.info("appid:---" + appID + "---appsecret:---" + appsecret);
 	}
	
	public static String getAppid() {
		return appID;
	}
	
	public static String getAppsecret() {
		return appsecret;
	}

}
