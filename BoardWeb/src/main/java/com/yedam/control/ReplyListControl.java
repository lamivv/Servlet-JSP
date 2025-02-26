package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.dao.Control;
import com.yedam.dao.ReplyDAO;
import com.yedam.vo.ReplyVO;

public class ReplyListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");
		
		// 원본 글 번호, 페이지
		String bno = req.getParameter("bno");
		String page = req.getParameter("page");
		
		// DAO활용
		ReplyDAO rdao = new ReplyDAO();
		List<ReplyVO> list = rdao.replyList(Integer.parseInt(bno),Integer.parseInt(page));
		
		// gson활용해서 json을 쉽게 ..
		//Gson gson = new GsonBuilder().create();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list); // 자바객체를 json객체의 문자열로 변환
		
		System.out.println(json); // 콘솔에 출력
		resp.getWriter().print(json); // 웹브라우저에 출력 
	}

}
