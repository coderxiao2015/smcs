package com.tianwen.core.user.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianwen.base.util.Pager;
import com.tianwen.common.log.SystemLog;
import com.tianwen.common.redisutil.RedisUtil;
import com.tianwen.common.util.SysUtil;
import com.tianwen.core.user.dao.UserDao;
import com.tianwen.core.user.entity.TMember;
import com.tianwen.core.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RedisUtil redisUtil;

	public void addNewUser(TMember tMember) {
		userDao.addNewMember(tMember);
	}

	public TMember doLogin(String account, String password) {
		return userDao.findMemberByActPwd(account, password);
	}
	
	public TMember findMemberByMid(Integer mid ) {
		return userDao.findMemberByMid(mid);
	}

	public void updMemberByMid(TMember member) {
		userDao.updMemberByMid(member);
	}

	@Override
	@SystemLog(opType="123", opDescription="456", opModel="789")
	public HashMap<String, Object> getPerCenterInfo(TMember tMember, Pager pager) {
		tMember = (TMember) redisUtil.getObject("Elysion2");
		int mid = tMember.getMid();
		HashMap<String,Object> infoMap = new HashMap<String,Object>();
		HashMap<String, Object> memberMap = SysUtil.transBean2Map(tMember);
		pager.setTotalRows(memberMap, userDao.countTMember(memberMap));
		pager.setList(userDao.findTMemberByPage(memberMap));
		infoMap.put("pager", pager);//我的订单(未付款数量)
		return infoMap;
	}

	@Override
	public List<HashMap<String, Object>> findValidCouponByMid(Integer mid) {
		return userDao.findValidCouponByMid(mid);
	}

	@Override
	public List<HashMap<String, Object>> findInvalidCouponByMid(Integer mid) {
		return userDao.findInvalidCouponByMid(mid);
	}

	@Override
	public TMember findMemberByMobile(String mobile) {
		return userDao.findMemberByMobile(mobile);
	}

}
