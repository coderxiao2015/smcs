package com.tianwen.core.user.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianwen.common.log.SystemLog;
import com.tianwen.core.user.dao.UserMapper;
import com.tianwen.core.user.entity.TMember;
import com.tianwen.core.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	public void addNewUser(TMember tMember) {
		userMapper.addNewMember(tMember);
	}

	public TMember doLogin(String account, String password) {
		return userMapper.findMemberByActPwd(account, password);
	}

	public void updMemberByMid(TMember member) {
		userMapper.updMemberByMid(member);
	}

	@Override
	@SystemLog(opType="123", opDescription="456", opModel="789")
	public HashMap<String, Object> getPerCenterInfo(TMember tMember) {
		HashMap<String,Object> infoMap = new HashMap<String,Object>();
		infoMap.put("orderCount", userMapper.countNoPayedOrder(tMember));//我的订单(未付款数量)
		return infoMap;
	}

}
