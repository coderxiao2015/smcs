package com.tianwen.core.share.dao;

import com.tianwen.core.share.entity.ProductEntity;
import com.tianwen.core.share.entity.TRelationRecordEntity;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.tianwen.core.share.entity.ProductEntity;

@Repository
public interface ShareDao {
	
	/**
	 * @return 通过分享赚到的现金
	 */
    public HashMap<String,Object> getShareCount(HashMap<String,Object> param);
    /**
     * @return 个检分享商品
     */
    public ArrayList<ProductEntity> getAllProductByPage(HashMap<String,Object> param);

    /**
     * @return 商品页数
     */

    public Integer getAllProductCount(HashMap<String,Object> param);

    /**
     * 获取商品信息
     */
    public ProductEntity getProductByPid(HashMap<String,Object> param);

    public HashMap<String,Object> findMidByOpenId(TRelationRecordEntity relationRecordEntity);

    public HashMap<String,Object> findMidByParentMid(TRelationRecordEntity relationRecordEntity);


    public int addMember(HashMap<String,Object> param);

    public void addRelationRecord(TRelationRecordEntity relationRecordEntity);

    public String getTrianglePids();

    public HashMap<String,Object> findProductByPid(HashMap<String,Object> param);


    public HashMap<String,Object> findMyMoney(HashMap<String,Object> param);

}
