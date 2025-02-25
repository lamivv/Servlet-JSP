package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.Control;
import com.yedam.dao.MemberDAO;
import com.yedam.vo.MemberVO;

public class AddMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// param(아이디, 비번, 이름)
		String id = req.getParameter("mid");
		String pw = req.getParameter("mpw");
		String name = req.getParameter("mname");
		MemberVO mvo = new MemberVO();
		mvo.setMemberId(id);
		mvo.setPasswd(pw);
		mvo.setMamberName(name);
		mvo.setResponsibility("User");
		System.out.println(mvo);
		
		MemberDAO mdao = new MemberDAO(); // 추가메소드( boolean insertMemeber(MemberVO member) )
		
		boolean isOk = mdao.insertMember(mvo);
		
		// 처리결과 반환
		if(isOk) {
			// {"retCode": "OK"}
			System.out.println("됏나");
			resp.getWriter().print("{\"retCode\": \"OK\"}");
		} else {
			// {"retCode": "NG"}
			
			System.out.println("안됏나");
			resp.getWriter().print("{\"retCode\": \"NG\"}");
		}
	}

}
