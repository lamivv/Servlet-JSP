package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.BoardDAO;
import com.yedam.dao.Control;
import com.yedam.vo.BoardVO;

public class AddBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 3개의 파라미터 활용해서 db에 저장하고 글 목록으로 이동
		// title=등록연습&content=연습%21+&writer=user01
		String title = req.getParameter("title"); // name속성 title의 값 = 등록연습
		String content = req.getParameter("content"); // name속성 content의 값 = 연습
		String writer = req.getParameter("writer"); // name속성 writer의 값 = user01

		// insertBoard의 매개값으로 들어갈 BoardVO 개체 생성
		BoardVO bvo = new BoardVO();
		bvo.setTitle(title);
		bvo.setContent(content);
		bvo.setWriter(writer);

		BoardDAO bdao = new BoardDAO();
		if (bdao.insertBoard(bvo)) {
			System.out.println("등록성공");
			resp.sendRedirect("boardList.do");
		} else {
			System.out.println("등록실패");
		}
	}
}
