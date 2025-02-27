package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.dao.Control;
import com.yedam.dao.ReplyDAO;
import com.yedam.mapper.ReplyMapper;

public class RemoveReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 댓글번호 
		String rno = req.getParameter("rno");
		
		// DB반영
//		ReplyDAO rdao = new ReplyDAO();
		SqlSession sqlSession = DataSource.getInstance().openSession();
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		int run = mapper.deleteReply(Integer.parseInt(rno));
		
		// json으로 반환
		if (run == 1) {
			// {"retCode": "OK"}
			resp.getWriter().print("{\"retCode\": \"OK\"}");
			sqlSession.commit(true); // 커밋
		} else {
			resp.getWriter().print("{\"retCode\": \"NG\"}");
		}
	}

}
