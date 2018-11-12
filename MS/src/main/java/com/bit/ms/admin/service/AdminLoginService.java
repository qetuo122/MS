package com.bit.ms.admin.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.ms.admin.model.AdminVO;
import com.bit.ms.dao.AdminDaoInterface;

@Service
public class AdminLoginService {

	@Autowired
	private SqlSessionTemplate adminSqlSession;
	
	private AdminDaoInterface adminDao;
	
	public int adminLogin_service(String admin_id, String admin_pw, int store_id,String check, HttpServletResponse response, HttpSession httpSession) {
		
		int result = 0;
		
		adminDao = adminSqlSession.getMapper(AdminDaoInterface.class);
		
		AdminVO vo = adminDao.loginAdmin(admin_id, store_id);
		
		// 입력한 아이디와 스토어id값을 통해 정보가 존재 할 경우
		if(vo != null) {
			if(vo.getAdmin_id().equals(admin_id) && vo.getAdmin_pw().equals(admin_pw) && vo.getStore_id() == store_id) {

				Cookie cookie = new Cookie("check", admin_id);
				if(check.equals("true")) {
					response.addCookie(cookie);
				} else {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				// 세션 저장하기 전에 비밀번호 변경
				vo.setAdmin_pw("");
				
				// 세션에 vo 객체 저장
				httpSession.setAttribute("adminSession", vo);
				System.out.println("세션확인 " + httpSession.getAttribute("adminSession"));
				result = 1;
			}
		}
		return result;
	}
}
