package com.bit.ms.admin.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.ms.dao.AdminDaoInterface;
import com.bit.ms.member.model.SeatVO;

@Service
public class AdminSeatService {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	private AdminDaoInterface adminDaoInterface;

	public List<SeatVO> getSeatListS() {

		adminDaoInterface = sqlSessionTemplate.getMapper(AdminDaoInterface.class);

		List<SeatVO> list = null;

		try {
			list = adminDaoInterface.getSeatListI();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addSeatS(SeatVO seatVO) {

		adminDaoInterface = sqlSessionTemplate.getMapper(AdminDaoInterface.class);

		int resultCnt = 0;

		try {
			resultCnt = adminDaoInterface.addSeatI(seatVO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultCnt;
	}

	public int deleteSeatS(int seat_id) {
		
		adminDaoInterface = sqlSessionTemplate.getMapper(AdminDaoInterface.class);

		int resultCnt = 0;

		try {
			resultCnt = adminDaoInterface.deleteSeatI(seat_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultCnt;
	}
}
