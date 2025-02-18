package com.yedam.vo;
/*
 * 학번 (student_no) S001
 * 이름 (student_name) 김땅콩
 * 전화번호 (phone) 010-1234-1234
 * 주소 (address) 대구 서구 100
 */
public class Student {
	
	// 필드 tbl_student
	private String stdNo; // student_no
	private String stdName; // student_name
	private String stdPhone; // phone
	private String stdAddress; // address
	
	// 생성자
	public Student() {}
	
	public Student(String stdNo, String stdName, String stdPhone, String stdAddress) {
		this.stdNo = stdNo;
		this.stdName = stdName;
		this.stdPhone = stdPhone;
		this.stdAddress = stdAddress;
	}

	// getter, setter
	public String getStdNo() {
		return stdNo;
	}

	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public String getStdPhone() {
		return stdPhone;
	}

	public void setStdPhone(String stdPhone) {
		this.stdPhone = stdPhone;
	}

	public String getStdAddress() {
		return stdAddress;
	}

	public void setStdAddress(String stdAddress) {
		this.stdAddress = stdAddress;
	}

	@Override
	public String toString() {
		return "Student [stdNo=" + stdNo + ", stdName=" + stdName + ", stdPhone=" + stdPhone + ", stdAddress="
				+ stdAddress + "]";
	}
	
	
}
