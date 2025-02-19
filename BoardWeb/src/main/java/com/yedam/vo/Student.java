package com.yedam.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 학번 (student_no) S001
 * 이름 (student_name) 김땅콩
 * 전화번호 (phone) 010-1234-1234
 * 주소 (address) 대구 서구 100
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	
	// 필드 tbl_student
	private String stdNo; // student_no
	private String stdName; // student_name
	private String stdPhone; // phone
	private String stdAddress; // address
	
	@Override
	public String toString() {
		return "Student [stdNo=" + stdNo + ", stdName=" + stdName + ", stdPhone=" + stdPhone + ", stdAddress="
				+ stdAddress + "]";
	}
	
	
}
