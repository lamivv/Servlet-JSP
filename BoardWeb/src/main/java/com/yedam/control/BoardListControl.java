package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.common.PageVO;
import com.yedam.common.SearchVO;
import com.yedam.dao.Control;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// boardList.do?page=1
		String page = req.getParameter("page");
		page = page == null ? "1" : page;
		// boardList.do?searchCondition=T&keyword=연습
		String sc = req.getParameter("searchCondition");
		String kw = req.getParameter("keyword");
		sc = sc == null ? "" : sc; // null값 처리
		kw = kw == null ? "" : kw; // null값 처리
		
		// SearchVO : 파라미터
		SearchVO search = new SearchVO(Integer.parseInt(page), sc, kw);
		
		String name = "홍길동";
		// boardList.do -> (BoardListControl) -> boardList.jsp
		req.setAttribute("msg", name);

//		BoardDAO bdao = new BoardDAO();
//		List<BoardVO> list = bdao.selectBoard(search);
		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		List<BoardVO> list = mapper.selectBoard(search);
				
		req.setAttribute("list", list);

		// 페이징
//		int totalCnt = bdao.getTotalCount(search); //실제건수
		int totalCnt = mapper.getTotalCount(search);
		
		PageVO paging = new PageVO(Integer.parseInt(page), totalCnt);
		req.setAttribute("paging", paging);
		// 페이징 -> searchCondition, keyword 전달
		req.setAttribute("searchCondition", sc);
		req.setAttribute("keyword", kw);
		
		// 요청재지정(url: boardList.do (boardList.jsp))
		req.getRequestDispatcher("board/boardList.tiles").forward(req, resp); // 연결하고 싶은 페이지
		// 현재보여주는 페이지가 url에 boardList를 치면 처리하는 결과를 내가 원하는 jsp페이지로 전달
	}
}
