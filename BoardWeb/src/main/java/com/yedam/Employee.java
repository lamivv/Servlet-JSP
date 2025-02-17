package com.yedam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
 사원번호(1001, 1002, ...)
 사원이름(김땅콩, 김치즈, ...)
 전화번호(123-4567, 123-7654, ...)
 입사일자(2021-12-12, ...)
 급여(300, 350, ...)
 */
public class Employee {
	private int empNo;
	private String empName;
	private String telNo;
	private Date hireDate;
	private int salary;
	
	//생성자
	public Employee() {}
	
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
	

	// getter, setter
	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
} // end of class Employee
