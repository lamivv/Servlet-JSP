package com.yedam.mapper;

import java.util.List;

import com.yedam.common.SearchVO;
import com.yedam.vo.BoardVO;

public interface BoardMapper {
	public int getTotalCount(SearchVO search);
	
	public int updateCount(int boardNo);
	
	public BoardVO getboard(int boardNo);
	
	public List<BoardVO> selectBoard(SearchVO search);
	
	public int insertBoard(BoardVO board);
	
	public int updateBoard(BoardVO board);
	
	public int deleteBoard(int boardNo);
	
}
