package com.tianwen.core.user.service;

import java.util.HashMap;

import com.tianwen.base.util.Pager;
import com.tianwen.core.user.entity.TMember;

public interface UserService {

	void addNewUser(TMember tMember);
	
	TMember doLogin(String account, String password);
	
	void updMemberByMid(TMember member);
	
	HashMap<String, Object> getPerCenterInfo(TMember tMember, Pager pager);
	public TMember findMemberByMid(Integer mid );
}
