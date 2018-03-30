package com.tianwen.core.share.service;

import com.tianwen.core.share.entity.ProductEntity;
import com.tianwen.core.share.entity.TRelationRecordEntity;

import java.util.ArrayList;
import java.util.HashMap;


public interface ShareService {

    public HashMap<String,Object> getShare(HashMap<String,Object> param);

    public ArrayList<ProductEntity> getAllProductByPage(HashMap<String,Object> param);

    public Integer getAllProductCount(HashMap<String,Object> param);

    public ProductEntity getProductByPid(HashMap<String,Object> param);

    public void doBindRelationship(TRelationRecordEntity relationRecordEntity);

    public String getPids();

    public int insterInfo(HashMap<String,Object> param);

    public HashMap<String,Object> findProductByPid(HashMap<String,Object> param);

    public HashMap<String,Object> findMyMoney(HashMap<String,Object> param);

    public HashMap<String,Object> findLastMoney(HashMap<String,Object> param);

    public HashMap<String,Object> findAllMoney(HashMap<String,Object> param);

    public HashMap<String,Object> findActiveMoney(HashMap<String,Object> param);


}
