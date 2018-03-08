package com.tianwen.core.share.service.impl;

import com.tianwen.core.share.dao.ShareDao;
import com.tianwen.core.share.service.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Scope("prototype")
public class ShareServiceImpl implements ShareService {

	@Autowired
	private ShareDao shareDao;
	
    @Override
    public HashMap<String, Object> getShare(HashMap<String, Object> param) {
        return shareDao.getShareCount(param);
    }
}
