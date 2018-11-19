package com.bit.ms.user.service;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.ms.dao.UserDaoInterface;
import com.bit.ms.member.model.StoreVO;
import com.bit.ms.user.model.UserBoardListVO;
import com.bit.ms.user.model.UserBoardReplyVO;
import com.bit.ms.user.model.UserBoardVO;

@Service
public class UserBoardService {

	@Autowired
	private SqlSessionTemplate sessionTemplate;

	private UserDaoInterface userDaoInterface;

	// List
	// 페이지마다 보여줄 게시글의 수
	private static final int USERBOARD_COUNT_PER_PAGE = 10;

	public UserBoardListVO getUserBoardList(HttpSession session, int pageNum) {

		userDaoInterface = sessionTemplate.getMapper(UserDaoInterface.class);

		StoreVO storevo = (StoreVO) session.getAttribute("storeSelectSession");

		int store_id = storevo.getStore_id();

		System.out.println("서비스 store_id = " + store_id);

		int currentPageNum = pageNum;

		int userBoardTotalCount = 0;

		List<UserBoardVO> userBoardList = null;

		int firstRow = 0;

		// HashMap<String, Object> map = new HashMap<String, Object>();

		try {

			// 전체 게시글 구하기
			userBoardTotalCount = userDaoInterface.UserBoardTotalCount(store_id);

			if (userBoardTotalCount > 0) {

				firstRow = (pageNum - 1) * USERBOARD_COUNT_PER_PAGE;

				// map.put("firstRow", firstRow);
				// map.put("store_id", store_id);

				// System.out.println("서비스 map = " + map);

				userBoardList = userDaoInterface.UserBoardSelectList(store_id, firstRow); // mysql은 0열부터 시작 -1을 해줌

			} else {
				currentPageNum = 0;
				userBoardList = Collections.emptyList();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		int userBoardPageTotalCount = ((userBoardTotalCount - 1) / USERBOARD_COUNT_PER_PAGE) + 1;// 페이지 수

		return new UserBoardListVO(userBoardList, userBoardTotalCount, currentPageNum, userBoardPageTotalCount,
				USERBOARD_COUNT_PER_PAGE, firstRow);

	}

	public List<UserBoardVO> getUserBoardNoticeS(HttpSession session) { //공지사항 받을것

		userDaoInterface = sessionTemplate.getMapper(UserDaoInterface.class);

		StoreVO storevo = (StoreVO) session.getAttribute("storeSelectSession");

		int store_id = storevo.getStore_id();

		String admin_id = storevo.getAdmin_id();

		List<UserBoardVO> userboardnotice = null;

		try {
			userboardnotice = userDaoInterface.getUserBoardNoticeI(store_id, admin_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userboardnotice;
	}

	public int userBoardWrite(UserBoardVO userBoardVO) {

		userDaoInterface = sessionTemplate.getMapper(UserDaoInterface.class);

		int resultCnt = 0;

		try {
			resultCnt = userDaoInterface.insertUserBoard(userBoardVO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultCnt;
	}

	public int UserBoardDeleteS(int uboard_id) {

		userDaoInterface = sessionTemplate.getMapper(UserDaoInterface.class);

		int resultCnt = 0;

		try {
			resultCnt = userDaoInterface.UserBoardDeleteI(uboard_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultCnt;

	}

	public int UserBoardModifyS(UserBoardVO userBoardVO) {

		userDaoInterface = sessionTemplate.getMapper(UserDaoInterface.class);

		int resultCnt = 0;

		try {
			resultCnt = userDaoInterface.UserBoardModifyI(userBoardVO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultCnt;

	}

	// ViewViewViewViewViewViewViewViewViewViewViewViewViewViewViewViewView

	public UserBoardVO getUserBoardViewS(int uboard_id) {

		userDaoInterface = sessionTemplate.getMapper(UserDaoInterface.class);

		UserBoardVO userBoardVO = null;

		try {
			userBoardVO = userDaoInterface.getUserBoardViewI(uboard_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userBoardVO;
	}

	public int getViewPreviousNUM(HttpSession session, int uboard_id) {

		userDaoInterface = sessionTemplate.getMapper(UserDaoInterface.class);

		StoreVO storevo = (StoreVO) session.getAttribute("storeSelectSession");

		int store_id = storevo.getStore_id();

		int num = 0;

		try {
			num = userDaoInterface.getPreviousNUM(uboard_id, store_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return num;
	}

	public int getViewNextNUM(HttpSession session, int uboard_id) {

		userDaoInterface = sessionTemplate.getMapper(UserDaoInterface.class);

		StoreVO storevo = (StoreVO) session.getAttribute("storeSelectSession");

		int store_id = storevo.getStore_id();

		int num = 0;

		try {
			num = userDaoInterface.getNextNUM(uboard_id, store_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return num;
	}

	public int getViewTotalCount(HttpSession session) {

		userDaoInterface = sessionTemplate.getMapper(UserDaoInterface.class);

		StoreVO storevo = (StoreVO) session.getAttribute("storeSelectSession");

		int store_id = storevo.getStore_id();

		int num = 0;

		try {
			num = userDaoInterface.UserBoardTotalCount(store_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return num;
	}

	// 댓글
	public List<UserBoardReplyVO> getUserBoardReplyListS(int uboard_id) {

		userDaoInterface = sessionTemplate.getMapper(UserDaoInterface.class);

		List<UserBoardReplyVO> UserBoardList = null;

		try {
			UserBoardList = userDaoInterface.getUserBoardReplyListI(uboard_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return UserBoardList;

	}

	public int UserBoardReplyInsertS(UserBoardReplyVO userBoardReplyVO) {

		userDaoInterface = sessionTemplate.getMapper(UserDaoInterface.class);

		int resultCnt = 0;
		try {
			resultCnt = userDaoInterface.UserBoardReplyInsertI(userBoardReplyVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultCnt;
	}

	public int UserBoardReplyDeleteS(int uboard_reply_id) {

		userDaoInterface = sessionTemplate.getMapper(UserDaoInterface.class);

		int resultCnt = 0;
		try {
			resultCnt = userDaoInterface.UserBoardReplyDeleteI(uboard_reply_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultCnt;
	}

	public int UserBoardReplyModifyS(UserBoardReplyVO userBoardReplyVO) {

		userDaoInterface = sessionTemplate.getMapper(UserDaoInterface.class);

		int resultCnt = 0;
		try {
			resultCnt = userDaoInterface.UserBoardReplyModifyI(userBoardReplyVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultCnt;
	}

}
