package com.yedam.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok을 활용
@Getter
@Setter
@ToString
public class BoardVO { // tbl_board
	// 필드
	private int boardNo; // board_no
	private String title; // title
	private String content; // content
	private String writer; // writer
	private Date writeDate; // write_date
	private int viewCnt; // view_cnt
}
