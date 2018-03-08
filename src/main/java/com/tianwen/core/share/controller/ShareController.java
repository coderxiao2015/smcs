package com.tianwen.core.share.controller;


import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;
import com.tianwen.common.util.StringUtils;
import com.tianwen.core.share.service.ShareService;

import jxl.read.biff.SharedBooleanFormulaRecord;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.omg.CORBA.FloatHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Hashtable;

@Controller
@Scope(value = "prototype")
@RequestMapping("/share")
public class ShareController {
	
    private static final Logger logger = Logger.getLogger(ShareController.class);
    
    @Autowired
    private ShareService shareService;

    /*分享赚钱首页*/
    @GetMapping(value = "/shareHome")
    public ModelAndView shareHome(String mid, String openid) {
            HashMap<String, Object> param = new HashMap<>();
            param.put("mid", mid);
            param.put("openid", openid);
            HashMap<String, Object> getEarnedMoney=shareService.getShare(param);
        return new ModelAndView("/share/shareHomePage", "map11", getEarnedMoney);
    }

    /*攻略*/
    @GetMapping(value = "/guide")
    public ModelAndView guide() {
        return new ModelAndView("/share/guidePage");
    }

    /*分享记录*/
    @GetMapping(value="/shareRecord")
    public ModelAndView shareRecord(String mid){
    	ModelAndView modelAndView=new ModelAndView();
    	HashMap<String, Object> param=new HashMap<>();
    	param.put("mid", mid);
    	//HashMap<String, Object> getEarnedMoney=shareService.getShareRecord(param);
    	
    	return modelAndView;
    }

    /*获取商品信息*/
    @RequestMapping(value = "getProductInfo", method = RequestMethod.POST)
    public ModelAndView getProductInfo(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        try {

            modelAndView.setViewName("productList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }




}
