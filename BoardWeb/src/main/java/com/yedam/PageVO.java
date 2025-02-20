package com.yedam;

import lombok.Data;

@Data
public class PageVO {
	// 67건의 게시글,,, 1~14페이지
	// 현재 보여주는 페이지가 2페이지라면 2페이지가 포함되어 있는 것을 기준으로 1~10페이지를 보여주려고함
	// 만약 12페이지면 11~14페이지를 보여주려고 함
	private int startPage; // 첫페이지
	private int endPage; // 마지막페이지
	private int currentPage; // 현재페이지
	private boolean prev, next; // 이전, 이후 페이지의 여부를 담아두기 위한 변수

	// 생성자
	public PageVO(int page, int totalCnt) {
		currentPage = page;
		endPage = (int) Math.ceil(currentPage / 10.0) * 10; // 0.2를 올림하면 1 거기에 10을 곱하면 10페이지
		startPage = endPage - 9; // 계산상의 start, end

		int realEnd = (int) Math.ceil(totalCnt / 5.0); // 실제 마지막 페이지
		endPage = endPage > realEnd ? realEnd : endPage;
		
		prev = startPage == 1 ? false : true;
		next = endPage == realEnd ? false : true;
	}
}
