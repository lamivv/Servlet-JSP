package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.BoardDAO;
import com.yedam.dao.Control;
import com.yedam.vo.BoardVO;

public class BoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청재지정(url: boardList.do (boardList.jsp))
		String bno = req.getParameter("bno");
		
		BoardDAO bdao = new BoardDAO();
		// 먼저 상세목록을 보여준 후 조회수 업데이트 == 클릭하면 조회수 증가하기 전의 조회수가 나온다.
		BoardVO board = bdao.getboard(Integer.parseInt(bno)); // 문자열 "14" -> int i4 
		bdao.updateCount(Integer.parseInt(bno));
		
		// 요청정보의 attribute활용
		req.setAttribute("board", board); 
		req.getRequestDispatcher("/WEB-INF/views/board.jsp").forward(req, resp); // 연결하고 싶은 페이지
		// 현재보여주는 페이지가 url에 boardList를 치면 처리하는 결과를 내가 원하는 jsp페이지로 전달
	}

}
