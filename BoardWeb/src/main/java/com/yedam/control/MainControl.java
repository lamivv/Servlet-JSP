package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.Control;

public class MainControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메인페이지 
		req.getRequestDispatcher("WEB-INF/views/main.jsp").forward(req, resp);
	}

}
