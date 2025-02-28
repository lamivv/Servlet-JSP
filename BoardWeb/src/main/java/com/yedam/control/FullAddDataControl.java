package com.yedam.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.dao.Control;
import com.yedam.mapper.ReplyMapper;

public class FullAddDataControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// db
		String title = req.getParameter("title");
		String start = req.getParameter("start");
		String end = req.getParameter("end");

		SqlSession sqlSession = DataSource.getInstance().openSession();
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		int list = mapper.insertEvent(title, start, end);

		// 처리결과 반환
		if (list == 1) {
			// {"retCode": "OK"}
			sqlSession.commit(true); // 커밋
			System.out.println("됏나");
			resp.getWriter().print("{\"retCode\": \"OK\"}");
		} else {
			// {"retCode": "NG"}
			System.out.println("안됏나");
			resp.getWriter().print("{\"retCode\": \"NG\"}");
		}
	}

}
