package com.tianwen.core.user.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tianwen.core.user.entity.TMember;

@Repository
public interface UserDao {

	void addNewMember(@Param("member") TMember tMember);
	
	TMember findMemberByActPwd(@Param("account") String account, @Param("password") String password);
	
	TMember findMemberByMid(@Param("mid") Integer mid);
	
	void updMemberByMid(@Param("member") TMember tMember);
	
	int countNoPayedOrder(TMember tMember);
	
	int countTMember(HashMap<String, Object> map);
	
	List<TMember> findTMemberByPage(HashMap<String, Object> map);
	
}
