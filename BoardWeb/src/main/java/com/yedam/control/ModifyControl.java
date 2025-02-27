package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.dao.BoardDAO;
import com.yedam.dao.Control;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class ModifyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정화면 open
		String bno = req.getParameter("bno");

//		BoardDAO bdao = new BoardDAO();
		// 먼저 상세목록을 보여준 후 조회수 업데이트 == 클릭하면 조회수 증가하기 전의 조회수가 나온다.
		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		
		BoardVO board = mapper.getboard(Integer.parseInt(bno)); // 문자열 "14" -> int i4
		
		// 세션 아이디와 글작성자 아이디 비교
		HttpSession session = req.getSession();
		String sessionId = (String) session.getAttribute("loginId");
		String writerId = board.getWriter();
		
		if (!sessionId.equals(writerId)) {
			req.setAttribute("msg", "권한을 확인하세요");
			req.setAttribute("board", board);
			req.getRequestDispatcher("board/board.tiles").forward(req,resp);
			return; // exec 메소드 종료. 아래의 요청정보의 ...뭐시기 는 실행되지 않음
		}
		
		// 요청정보의 attribute활용
		req.setAttribute("board", board);
		req.getRequestDispatcher("board/modifyBoard.tiles").forward(req, resp); // 연결하고 싶은 페이지
		// 현재보여주는 페이지가 url에 boardList를 치면 처리하는 결과를 내가 원하는 jsp페이지로 전달
	}

}
