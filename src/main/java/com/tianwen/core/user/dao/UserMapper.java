package com.tianwen.core.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tianwen.core.user.entity.TMember;

@Repository
public interface UserMapper {

	void addNewMember(@Param("member") TMember tMember);
	
	TMember findMemberByActPwd(@Param("account") String account, @Param("password") String password);
	
	void updMemberByMid(@Param("member") TMember tMember);
	
	int countNoPayedOrder(TMember tMember);
	
}
