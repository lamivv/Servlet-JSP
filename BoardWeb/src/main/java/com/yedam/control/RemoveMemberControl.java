package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.Control;
import com.yedam.dao.MemberDAO;

public class RemoveMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("mid");
		
		MemberDAO mdao = new MemberDAO();
		// MemberDAO에 삭제하는 기능 boolean
		boolean isOk = mdao.isOk(id);
		
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
