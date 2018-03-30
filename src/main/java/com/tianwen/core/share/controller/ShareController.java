package com.tianwen.core.share.controller;


import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.tianwen.base.util.Pager;
import com.tianwen.base.util.PropsLoader;
import com.tianwen.common.constant.SysConstant;
import com.tianwen.common.redisutil.RedisComponetUtil;
import com.tianwen.common.redisutil.RedisUtil;
import com.tianwen.common.shiro.token.TokenManager;
import com.tianwen.common.util.*;
import com.tianwen.core.share.entity.ProductEntity;
import com.tianwen.core.share.entity.TRelationRecordEntity;
import com.tianwen.core.share.service.ShareService;

import com.tianwen.core.user.entity.TMember;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import javax.management.monitor.Monitor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisComponetUtil redisComponetUtil;




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
       /* TMember tMember = TokenManager.getToken();
        param.put("mid", tMember.getMid());
        param.put("openid", tMember.getOpenid());*/
        modelAndView.setViewName("/share/shareRecord");

        return modelAndView;
    }

    /*获取商品信息*/
    @RequestMapping(value = "/getProductInfo", method = RequestMethod.GET)
    public ModelAndView getProductInfo(HttpServletRequest request,HttpServletResponse response) {
        ModelAndView modelAndView=new ModelAndView();
        Pager pager=new Pager();
        HashMap<String, Object> param = new HashMap<>();
        Integer infoSum = shareService.getAllProductCount(param);
        String pids=shareService.getPids();
        pager.setTotalRows(param, infoSum);
        modelAndView.setViewName("/share/productList");
        modelAndView.addObject("pager",pager);
        modelAndView.addObject("pids",pids);
        return modelAndView;
    }

    /**
     * @author xiaoyi
     * 加载更多
     */
    @PostMapping(value = "loadMoreByPage")
    @ResponseBody
    public Pager loadMoreByPage(HttpServletRequest request,HttpServletResponse response) {
        Pager pager=new Pager();
        HashMap<String,Object> parm= new HashMap<>();
        parm.put("globalSearch",request.getParameter("globalSearch"));
        parm.put("categoryName",request.getParameter("categoryName"));

        pager.setPageNo(request.getParameter("pageNo"));
        Integer infoSum = shareService.getAllProductCount(parm);
        pager.setTotalRows(parm,infoSum);
        parm.put("pageNo",request.getParameter("pageNo"));
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
        String openid=tMember!=null?tMember.getOpenid():null;
        System.out.println("当前用户的openid");
        String pid=request.getParameter("pid");
        HashMap<String,Object> param=new HashMap<>();
        param.put("mid",mid);
        param.put("pid",pid);
        param.put("isCard",request.getParameter("isCard"));
        if(StringUtil.isBlank(mid) || StringUtil.isBlank(pid)){
            throw  new NullPointerException();
        }
        if(!StringUtil.isBlank(openid))
            param.put("openid",openid);
        String isCard=request.getParameter("isCard");

        //获取产品信息
        HashMap<String,Object> result=shareService.findProductByPid(param);
        modelAndView.addObject("resultMap",result);
        modelAndView.addObject("map",param);
        return modelAndView;
    }

    /**
     *拼装套餐详情页的url地址，供分享接口调用
     */
    private  StringBuilder createProdutcUrl(String pid,String isCard){
        StringBuilder productUrl=new StringBuilder(200);
        productUrl.append(SysConstant.PCPT);
        productUrl.append("/core/product.toProductDetail.do?");
        productUrl.append("cgVariable.pid="+pid);
        if(!StringUtil.isBlank(isCard)&&(isCard.equals("1"))){
            productUrl.append("&isCard=1");
        }
        return productUrl;
    }

    @RequestMapping("/doHandleShareLink")
    public void doHandleShareLink(HttpServletRequest request,HttpServletResponse response) throws Exception {
        TMember tMember=TokenManager.getToken();

        //分享者信息
        String parentOpenId=request.getParameter("parentOpenId");
        String parentMid=request.getParameter("parentMid");
        logger.info("parentOpenId : " + parentOpenId + "  parentMid:" + parentMid);
        String pid=request.getParameter("pid");
        String isCard=request.getParameter("isCard");
        String isSale = request.getParameter("isSale");
        //绑定者信息
        String openId=tMember.getOpenid();
        String mid=String.valueOf(tMember.getMid());
        logger.info("openId : " + openId + "  mid:" + mid);


        //分享附带信息
        String selfUrl = SysConstant.TOOTHPT+"/share/shareDetail?mid="+mid+"&pid="+pid+"&isCard=0";

        String productUrl=null;
        productUrl=createProdutcUrl(pid,isCard).toString();
        logger.info("selfUrl : " + parentOpenId + "  productUrl:" + parentMid);
        //判断当前的打开的用户是本人还是其他人
        if((parentMid!=null) && (parentMid.equals(mid))) {
            response.sendRedirect(selfUrl);  //若是本人则重定向当前页面
        }else{
            TRelationRecordEntity relationRecordEntity=new TRelationRecordEntity();
            if(!StringUtil.isBlank(mid))
                relationRecordEntity.setMid(Integer.valueOf(mid));
            if(!StringUtil.isBlank(parentMid))
                relationRecordEntity.setParentMid(Integer.valueOf(parentMid));
            System.out.println("parentMid==="+relationRecordEntity.getParentMid());
            relationRecordEntity.setOpenid(openId);
            relationRecordEntity.setParentOpenid(parentOpenId);
            relationRecordEntity.setShareUrl(productUrl);
            // 这表明是分享链接
            if (!StringUtil.isBlank(parentOpenId) || !StringUtil.isBlank(parentMid)) {

                logger.info("isSale : " + isSale);
                // 0代表分享 1代表分销
                if (StringUtil.isBlank(isSale)) isSale = "0";

                if (isSale.equals("0")) {
                    CookieUtil.addCookie(response, SysConstant.TW_PARENETOPENID, AESUtil.encrypt(parentMid), SysConstant.COOKIE_LIFRCYCLE_THREEDAY);
                    CookieUtil.addCookie(response, SysConstant.TW_ISSALE, isSale, SysConstant.COOKIE_LIFRCYCLE_THREEDAY);
                } else {
                    CookieUtil.deleteCookie(request, response, SysConstant.TW_PARENETOPENID);
                    CookieUtil.deleteCookie(request, response, SysConstant.TW_ISSALE);
                    //创建一个异步线程去绑定关系
                    shareService.doBindRelationship(relationRecordEntity);
                }
            }
            //跳转到套餐详情页面
            response.sendRedirect(productUrl);
        }



    }

    /**
     * 跳转到海报页面
     */
    @RequestMapping(value = "/getPostPage")
    public ModelAndView modelAndView(HttpServletRequest request,HttpServletResponse response ){
        ModelAndView modelAndView=new ModelAndView("/share/post");

        TMember tMember=TokenManager.getToken();
        String mid=request.getParameter("mid");
        if(StringUtil.isBlank(mid))
            mid=String.valueOf(tMember.getMid());
        String openid=tMember.getOpenid();
        String pid=request.getParameter("pid");
        String isCard=request.getParameter("isCard");
        HashMap<String,Object> param=new HashMap<>();
        param.put("mid",mid);
        param.put("pid",pid);
        param.put("isCard",isCard);
        if(StringUtil.isBlank(mid) || StringUtil.isBlank(pid)){
            throw  new NullPointerException();
        }
        if(!StringUtil.isBlank(openid))
            param.put("openid",openid);

        HashMap<String,Object> result=shareService.findProductByPid(param);

        modelAndView.addObject("resultMap",result);
        modelAndView.addObject("map",param);
        modelAndView.addObject("shareUser",tMember.getName());
        return modelAndView;
    }


        /*查看收益*/
        @RequestMapping(value = "/checkMoney")
    public ModelAndView checkMoney(){
            ModelAndView modelAndView=new ModelAndView("/share/myMoney");
              /*  TMember tember=TokenManager.getToken();
                Integer mid=tember.getMid();
                String openId=tember.getOpenid();
                HashMap<String,Object> param=new HashMap<>();
                param.put("mid",mid);
                param.put("openid",openId);*/

            return modelAndView;
        }




}
