package com.tianwen.core.share.dao;

import com.tianwen.core.share.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

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

}
