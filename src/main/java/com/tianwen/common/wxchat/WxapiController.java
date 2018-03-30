package com.tianwen.common.wxchat;

import com.tianwen.common.constant.SysConstant;
import com.tianwen.common.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;

@Controller
@Scope(value = "prototype")
@RequestMapping(value = "/wxapi")
public class WxapiController {

    private static final Logger logger = Logger.getLogger(WxapiController.class);

    @RequestMapping("/doValidJSSDK")
    @ResponseBody
    public JsonResponseResult doValidJSSDK(HttpServletRequest request) {
           System.out.println("开始");
        JsonResponseResult result = null;
            String jsapi_ticket =WxapiController.getJsapiTicket();
            if(StringUtils.isEmpty(jsapi_ticket)){
                throw  new NullPointerException("jsapi_ticket没有获取到");
            }
           System.out.println("获取到数据");
            String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);// 随机字符串
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);// 时间戳

           System.out.println("时间戳：" + timestamp + "\n随机字符串：" + noncestr);
            String url = request.getParameter("url");
           System.out.println("url：" + url);
            String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
            String signature=SignUtil.SHA1(str);
        System.out.println("生成的签名："+signature);
            result = JsonResponseResult.createSuccess();
            result.addData(PublicAccount.getAppid());
            result.addData(noncestr);
            result.addData(timestamp);
            result.addData(jsapi_ticket);
            result.addData(signature);
            return result;
    }

    public static String getJsapiTicket(){
        String url= SysConstant.PCPT+"/core/wxapi.getJsApiTicket.do";
        HashMap<String,Object> param=new HashMap<>();
        param.put("test","test");
        String result=SysUtils.postHttpReq(url,param);
        JSONObject jsonObject=JSONObject.fromObject(result);
        JSONArray arrays=jsonObject.getJSONArray("data");
        String lastRe=String.valueOf(arrays.get(0));
       System.out.println(lastRe);
        return lastRe;
    }

}