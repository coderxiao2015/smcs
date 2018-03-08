package com.tianwen.core.share.dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface ShareDao {
	
	/*
	 * @return 通过分享赚到的现金
	 */
    public HashMap<String,Object> getShareCount(HashMap<String,Object> param);

}
