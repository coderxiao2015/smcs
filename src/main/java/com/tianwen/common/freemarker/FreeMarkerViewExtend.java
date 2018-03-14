package com.tianwen.common.freemarker;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.tianwen.common.SysConstant;
public class FreeMarkerViewExtend extends FreeMarkerView {
	private Log logger = LogFactory.getLog(this.getClass());
	
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request){
		
		try {
			super.exposeHelpers(model, request);
		} catch (Exception e) {
			logger.info("FreeMarkerViewExtend 加载父类出现异常。请检查。");
		}

		model.put(SysConstant.CONTEXT_PATH, request.getContextPath());
		model.putAll(Freemarker.initMap);
		//UUser token = TokenManager.getToken();
		//String ip = IPUtils.getIP(request);bu
//		if(TokenManager.isLogin()){
//			model.put("token", token);//登录的token
//		}

		model.put("_time", new Date().getTime());
		//model.put("NOW_YEAY", Constant.NOW_YEAY);//今年
		
		//model.put("_v", Constant.VERSION);//版本号，重启的时间
		//model.put("cdn", Constant.DOMAIN_CDN);//CDN域名
		model.put("basePath", request.getContextPath());//base目录。
		model.put("ctxImg", SysConstant.IMAGE_PATH);//图片目录
		model.put("pcptUrl", SysConstant.PCPT);//pcpt
		model.put("tooptUrl", SysConstant.TOOTHPT);//toothpt
	}
}
