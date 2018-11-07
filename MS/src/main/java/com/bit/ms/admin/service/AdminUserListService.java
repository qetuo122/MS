package com.bit.ms.admin.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.ms.admin.dao.AdminDaoInterface;
import com.bit.ms.member.model.UserVO;

@Service
public class AdminUserListService {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	AdminDaoInterface adminDao;
	
	public List<UserVO> getUserList(){
		
		adminDao = sqlSessionTemplate.getMapper(AdminDaoInterface.class);
		
		return adminDao.getUserList();
	}
	
}