package com.yedam;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.DataSource;
import com.yedam.mapper.ReplyMapper;

public class TTestExe {
	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory = DataSource.getInstance();
		SqlSession sqlSession = sqlSessionFactory.openSession();
//		
//		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		List<Map<String, Object>> list = mapper.fullData();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		System.out.println(gson.toJson(list));
			
//		
//		SearchVO search = new SearchVO(1, "T", "헤헤");
//		
//		int row = mapper.getTotalCount(search);
//		System.out.println("건수: " + row);
	}
}
