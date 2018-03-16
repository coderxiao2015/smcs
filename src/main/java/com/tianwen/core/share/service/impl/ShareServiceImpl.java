package com.tianwen.core.share.service.impl;

import com.tianwen.common.util.StringUtil;
import com.tianwen.common.util.ThreadPool;
import com.tianwen.core.share.dao.ShareDao;
import com.tianwen.core.share.entity.ProductEntity;
import com.tianwen.core.share.entity.TRelationRecordEntity;
import com.tianwen.core.share.service.ShareService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@Scope("prototype")
public class ShareServiceImpl implements ShareService {

    private final static Logger logger= LoggerFactory.getLogger(ShareServiceImpl.class);

	@Autowired
	private ShareDao shareDao;
	
    @Override
    public HashMap<String, Object> getShare(HashMap<String, Object> param) {
        return shareDao.getShareCount(param);
    }

    @Override
    public ArrayList<ProductEntity> getAllProductByPage(HashMap<String, Object> param) {
        HashMap<String,Object> newParam=this.getCondition(param);
        return shareDao.getAllProductByPage(newParam);
    }

     private HashMap<String,Object> getCondition(HashMap<String,Object> param){
        param.put("pageNo",param.get("pageNo"));
         String globalSearch=(String)param.get("globalSearch");
         if(StringUtil.isBlank(globalSearch)){
             globalSearch=null;
         }
         param.put("globalSearch",globalSearch);

         String  categoryName=(String)param.get("categoryName");
         param.put("saleCountDesc",null);
         param.put("saleCountAsc",null);
         param.put("salePriceDesc",null);
         param.put("salePriceAsc",null);
         param.put("saleRatioDesc",null);
         param.put("saleRatioAsc",null);
         if(!StringUtil.isBlank(categoryName))
         {
             if(categoryName.equals("saleCountDesc")){
                 param.put("saleCountDesc",categoryName);
             }
             if(categoryName.equals("saleCountAsc")){
                 param.put("saleCountAsc",categoryName);
             }
             if(categoryName.equals("salePriceDesc")){
                 param.put("salePriceDesc",categoryName);
             }
             if(categoryName.equals("salePriceAsc")){
                 param.put("salePriceAsc",categoryName);
             }
             if(categoryName.equals("saleRatioDesc")){
                 param.put("saleRatioDesc",categoryName);
             }
             if(categoryName.equals("saleRatioAsc")){
                 param.put("saleRatioAsc",categoryName);
             }
         }
         if(!StringUtil.isBlank(globalSearch)){
             globalSearch=globalSearch.trim();
             if(globalSearch.indexOf("男")>-1){
                 param.put("sex",1);
             }
             if(globalSearch.indexOf("已婚")>-1){
                 param.put("sex",2);
             }
             if(globalSearch.indexOf("未婚")>-1){
                 param.put("sex",4);
             }
         }
        return param;
     }


    @Override
    public Integer getAllProductCount(HashMap<String, Object> param) {
        String  categoryName=(String)param.get("categoryName");
        if(StringUtil.isBlank(categoryName))
            categoryName=null;

        String globalSearch=(String)param.get("globalSearch");
        if(StringUtil.isBlank(globalSearch)){
            globalSearch=null;
        }

        param.clear();//清空参数，重新赋值

        param.put("globalSearch",globalSearch);
        param.put("categoryName",categoryName);
        if(!StringUtil.isBlank(globalSearch)){
            globalSearch=globalSearch.trim();
            if(globalSearch.indexOf("男")>-1){
                param.put("sex",1);
            }
            if(globalSearch.indexOf("已婚")>-1){
                param.put("sex",2);
            }
            if(globalSearch.indexOf("未婚")>-1){
                param.put("sex",4);
            }
        }
        return shareDao.getAllProductCount(param);
    }

    @Override
    public ProductEntity getProductByPid(HashMap<String, Object> param) {
        return null;
    }

    @Override
    public String getPids() {
        return shareDao.getTrianglePids();
    }

    @Override
    public void doBindRelationship(final TRelationRecordEntity relationRecordEntity) {
        ThreadPool.threadPool.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                // 查找当前这个mid or openid是否是会员，如果不是则新增
                HashMap<String, Object> memberMap = shareDao.findMidByOpenId(relationRecordEntity);
                logger.info("查询用户是否是注册会员:" + memberMap);
                int mid = 0;
                if (memberMap == null || memberMap.isEmpty()) {
                    // 通过parentMid获取一级上线来作为这个Mid的二级上线
                    HashMap<String, Object> parentMemberMap = shareDao.findMidByOpenId(relationRecordEntity);
                    logger.info("通过parentMid去会员表查找parentMemberMap:" + parentMemberMap);

                    if (parentMemberMap == null || parentMemberMap.isEmpty())return;
                    relationRecordEntity.setParentMid(Integer.parseInt(parentMemberMap.get("MID").toString()));

                    logger.info("新增会员 begin");

                    HashMap<String, Object> param = new HashMap<>();
                    param.put("openid", relationRecordEntity.getOpenid());
                    param.put("levelone", relationRecordEntity.getParentMid());
                    param.put("leveltwo", parentMemberMap.get("LEVELONE"));
                    param.put("type", relationRecordEntity.getType() + 4);
                    param.put("unionid", relationRecordEntity.getUnionid());

                    mid = shareDao.addMember(param);

                    logger.info("新增会员 end, mid:" + mid);

                    relationRecordEntity.setMid(mid);

                    // 保存绑定关系轨迹
                    logger.info("保存绑定关系轨迹 begin,parentOpenId:"+
                            relationRecordEntity.getParentOpenid()+",parentMid:"+relationRecordEntity.getParentMid()
                            +",openId:"+relationRecordEntity.getOpenid()+",mid:"+relationRecordEntity.getMid());
                    shareDao.addRelationRecord(relationRecordEntity);
                    logger.info("保存绑定关系轨迹 end");
                }

            }
        });

    }
}
