package com.tianwen.core.share.controller;


import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;
import com.tianwen.base.util.Pager;
import com.tianwen.base.util.PropsLoader;
import com.tianwen.common.SysConstant;
import com.tianwen.common.redisutil.RedisUtil;
import com.tianwen.common.shiro.token.TokenManager;
import com.tianwen.common.util.*;
import com.tianwen.core.share.entity.ProductEntity;
import com.tianwen.core.share.service.ShareService;

import com.tianwen.core.user.entity.TMember;
import jxl.read.biff.SharedBooleanFormulaRecord;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.omg.CORBA.FloatHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.net.www.http.HttpClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

@Controller
@Scope(value = "prototype")
@RequestMapping("/share")
public class ShareController {

    private static final Logger logger = Logger.getLogger(ShareController.class);

    @Autowired
    private ShareService shareService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PropsLoader propsLoader;



    /*分享赚钱首页*/
    @GetMapping(value = "/shareHome")
    public ModelAndView shareHome(String mid, String openid) {
        HashMap<String, Object> param = new HashMap<>();
        TMember tMember = TokenManager.getToken();
        param.put("mid", tMember.getMid());
        param.put("openid", tMember.getOpenid());
        HashMap<String, Object> getEarnedMoney = shareService.getShare(param);
        return new ModelAndView("/share/shareHomePage", "map", getEarnedMoney);
    }

    /*攻略*/
    @GetMapping(value = "/guide")
    public ModelAndView guide() {
        return new ModelAndView("/share/guidePage");
    }

    /*问答*/
    @GetMapping(value = "/guideAnswer")
    public ModelAndView guideAnswer() {
        return new ModelAndView("/share/guideAnswerPage");
    }

    /*分享记录*/
    @GetMapping(value = "/shareRecord")
    public ModelAndView shareRecord(String mid) {
        ModelAndView modelAndView = new ModelAndView();
        HashMap<String, Object> param = new HashMap<>();
        TMember tMember = TokenManager.getToken();
        param.put("mid", tMember.getMid());
        param.put("openid", tMember.getOpenid());
        //HashMap<String, Object> getEarnedMoney=shareService.getShareRecord(param);

        return modelAndView;
    }

    /*获取商品信息*/
    @RequestMapping(value = "getProductInfo", method = RequestMethod.GET)
    public ModelAndView getProductInfo() {
        Pager pager=new Pager();
        HashMap<String, Object> param = new HashMap<>();
        Integer infoSum = shareService.getAllProductCount(param);
        pager.setTotalRows(param, infoSum);
        return new ModelAndView("/share/productList", "pager", pager);
    }

    /**
     * @author xiaoyi
     * 加载更多
     */
    @PostMapping(value = "loadMoreByPage")
    @ResponseBody
    public Pager loadMoreByPage(ProductEntity productEntity) {
        Pager pager=new Pager();
        HashMap<String,Object> parm= SysUtil.transBean2Map(productEntity);
        pager.setPageNo(productEntity.getPageNo());
        ArrayList<ProductEntity> resut=shareService.getAllProductByPage(parm);
            pager.addDataAll(resut);
            pager.createSuccess();
        return pager;
    }

    /**
     * @author xiaoyi
     * 发送请求给pcpt的sale.saleApplicate 方法，让pcpt去处理绑定的业务
     * @param mid,pid
     */
    @RequestMapping(value = "/bindSaleMan", method = RequestMethod.GET)
    public void bindSaleMan(HttpServletRequest request,HttpServletResponse response){
        HashMap<String,Object> param=new HashMap<>();
        String pid=request.getParameter("pid");
        param.put("pid",pid);
        String url= SysConstant.PCPT+"/core/sale.saleApplicate.do";
        SysUtils.postHttpReq(url,param);
    }

    /**
     *@author xiaoyi
     * 分享赚钱页面
     */
    @RequestMapping(value = "/shareDetail")
    public ModelAndView shareDetail(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("/share/shareDetailPage");
        TMember tMember=TokenManager.getToken();

        String mid=request.getParameter("mid");
        if(StringUtil.isBlank(mid))
            mid=String.valueOf(tMember.getMid());
        String openid=tMember.getOpenid();
        String pid=request.getParameter("pid");
        HashMap<String,Object> param=new HashMap<>();
        param.put("mid",mid);
        param.put("pid",pid);
        if(!StringUtil.isBlank(openid))
            param.put("openid",openid);
        modelAndView.addObject("map",param);
        return modelAndView;
    }

    /**
     *拼装套餐详情页的url地址，供分享接口调用
     */
    private  StringBuilder createProdutcUrl(String pid){
        StringBuilder productUrl=new StringBuilder(200);
        productUrl.append(SysConstant.PCPT);
        productUrl.append("/core/product.toProductDetail.do?");
        productUrl.append("cgVariable.pid="+pid);
        productUrl.append("&isCard=1");
        return productUrl;
    }


    @RequestMapping("/doHandleShareLink")
    public void doHandleShareLink(HttpServletRequest request,HttpServletResponse response) throws Exception {
        TMember tMember=TokenManager.getToken();
        //分享者信息
        String parentOpenId=request.getParameter("parentOpenId");
        String parentMid=request.getParameter("parenMid");
        if(!StringUtil.isBlank(parentOpenId))
        parentOpenId = AESUtil.encrypt(parentOpenId); //编码
        parentMid = AESUtil.encrypt(parentMid);
        logger.info("parentOpenId : " + parentOpenId + "  parentMid:" + parentMid);

        //分享附带信息
        String selfUrl = request.getParameter("selfUrl"); //当前url地址
        String pid=request.getParameter("pid");
        String productUrl=String.valueOf(this.createProdutcUrl(pid));
        logger.info("selfUrl : " + parentOpenId + "  productUrl:" + parentMid);

        //绑定者信息
        String openId=tMember.getOpenid();
        String mid=String.valueOf(tMember.getMid());
        if(!StringUtil.isBlank(openId))
        openId = AESUtil.encrypt(openId); //编码
        mid = AESUtil.encrypt(mid);
        logger.info("openId : " + openId + "  mid:" + mid);


        //判断当前的打开的用户是本人还是其他人
        if(parentMid.equals(mid))
            response.sendRedirect(selfUrl);  //若是本人则重定向当前页面


        //调用pcpt的方法绑定关系和打开页面
        HashMap<String,Object> param=new HashMap<>();
        param.put("parentOpenId",parentOpenId);
        param.put("parentMid",parentMid);
        param.put("selfUrl",selfUrl);
        param.put("productUrl",productUrl);
        param.put("openId",openId);
        param.put("mid",mid);
        param.put("isSale","1");

        String doHandlerUrl=SysConstant.MARKER+"/core/share.bindingRelationship.do";
        SysUtils.postHttpReq(doHandlerUrl,param);

        //跳转到套餐详情页面
        response.sendRedirect(productUrl);

        }





 @RequestMapping("/testException")
    public void testException(){
     throw  new NullPointerException("空指针异常");
 }


}
