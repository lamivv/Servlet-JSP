package com.yedam.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 사원번호(1001, 1002, ...)
 사원이름(김땅콩, 김치즈, ...)
 전화번호(123-4567, 123-7654, ...)
 입사일자(2021-12-12, ...)
 급여(300, 350, ...)
 */
@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //필드에 있는 것들을 모두 매개값으로 받는 생성자
public class Employee { // tbl_employees
	private int empNo; // emp_no
	private String empName; // emp_name
	private String telNo; // tel_no
	private Date hireDate; // hire_date
	private int salary; // salary
	
	public Employee(int empNO, String empName, String telNo) {
		this.empNo = empNO;
		this.empName = empName;
		this.telNo = telNo;
		this.hireDate = new Date();
		this.salary = 250;
	}
	
	public Employee(int empNO, String empName, String telNO, String hireDate, int salary) {
		this(empNO, empName, telNO);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.hireDate = sdf.parse(hireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.salary = salary;
	}
	
	// 메소드
	// 사번, 이름, 연락처, 급여 출력
	public String empInfo() {
		// 사번  이름   연락처      급여
		// -------------------------
		// 1001 홍길동 000-0000   250
		return empNo + " " + empName + " " + telNo + "   " + salary;
	}
} // end of class Employee
