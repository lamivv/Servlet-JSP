package com.yedam.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yedam.vo.ReplyVO;

public interface ReplyMapper {
	public List<Map<String, Object>> chartData();
	
	public int replyCount(int boardNo);
	
	//public List<ReplyVO> replyList(int boardNo, int page);
	public List<ReplyVO> replyList(@Param("boardNo") int boardNo, @Param("page") int page);
	
	public ReplyVO selectReply(int replyNo);
	
	public int insertReply(ReplyVO reply);
	
	public int deleteReply(int replyNo);
}
