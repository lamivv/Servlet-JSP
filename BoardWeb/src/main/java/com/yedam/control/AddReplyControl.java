package com.yedam.control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.DataSource;
import com.yedam.dao.Control;
import com.yedam.mapper.ReplyMapper;
import com.yedam.vo.ReplyVO;

public class AddReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/json;charset=utf-8");
		
		// 댓글내용, 작성자, 원본글번호
		String reply = req.getParameter("reply");
		String replyer = req.getParameter("replyer");
		String bno = req.getParameter("bno");
		
		// 매개값 
		ReplyVO rvo = new ReplyVO();
		rvo.setBoardNo(Integer.parseInt(bno));
		rvo.setReply(reply);
		rvo.setReplyer(replyer);
		rvo.setReplyDate(new Date()); 
		// Fri Feb 28 12:52:08 KST 2025
		// 	Feb 28, 2025, 2:17:26 PM
		
		SimpleDateFormat sdf = new SimpleDateFormat("M dd, yyyy, hh:mm:ss a");
		System.out.println(sdf.format(new Date()));
		
		
		// DB반영
//		ReplyDAO rdao = new ReplyDAO();
		SqlSession sqlSession = DataSource.getInstance().openSession();
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		
		int run = mapper.insertReply(rvo);
		
		// 결과값
		Map<String, Object> result = new HashMap<>();
		
		if (run == 1) {
			// {"retCode": "OK"}
			// resp.getWriter().print("{\"retCode\": \"OK\"}");
			sqlSession.commit(true); // 커밋
			result.put("retCode", "OK");
			result.put("retVal", rvo);
		} else {
			//resp.getWriter().print("{\"retCode\": \"NG\"}");
			result.put("retCode", "NG");
			result.put("retVal", rvo);
		}
		
		// json생성
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(result);
		
		resp.getWriter().print(json);
				
	}

}
