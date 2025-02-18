package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.BoardDAO;
import com.yedam.dao.Control;
import com.yedam.vo.BoardVO;

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = "홍길동";
		// boardList.do -> (BoardListControl) -> boardList.jsp
		req.setAttribute("msg", name);
		
		BoardDAO bdao = new BoardDAO();
		List<BoardVO> list = bdao.selectBoard();
		req.setAttribute("list", list);

		// 요청재지정(url: boardList.do (boardList.jsp))
		req.getRequestDispatcher("boardList.jsp").forward(req, resp); // 연결하고 싶은 페이지
		// 현재보여주는 페이지가 url에 boardList를 치면 처리하는 결과를 내가 원하는 jsp페이지로 전달
	}
}
