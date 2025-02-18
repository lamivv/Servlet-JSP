package com.yedam.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.Employee;

public class EmpDAO extends DAO {
	
	
	// 상세조회
	public Employee selectEmp(int empNo) {
		String query = "select * from tbl_employees " //
				+ "where emp_no = ?";
		try {
			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, empNo); // 첫번째 ?에 매개값으로 들어온 empNo를 넣음
			
			rs = psmt.executeQuery(); // 조회
			if(rs.next()) { // 조회결과가 한 건 있으면 true
				Employee emp = new Employee();
				emp.setEmpNo(rs.getInt("emp_no")); // 칼럼값
				emp.setEmpName(rs.getString("emp_name"));
				emp.setTelNo(rs.getString("tel_no"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setSalary(rs.getInt("salary"));
				
				return emp; // Employee타입의 emp 반환
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // 조회된 결과가 없음 -> 매개값(사원번호)가 일치한 db가 없을 때
	}
	
	
	
	// 등록
	public boolean registerEmp(Employee emp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String query = "insert into tbl_employees ";
		query += "values (" + emp.getEmpNo() //
				+ ", '" + emp.getEmpName() //
				+ "', '" + emp.getTelNo() //
				+ "', '" + sdf.format(emp.getHireDate()) //
				+ "', " + emp.getSalary() + ")";
		try {
			Statement stmt = getConnect().createStatement();
			int r = stmt.executeUpdate(query);
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false; // 등록
	} // end of registerEmp()
	
	// 목록
	public List<Employee> search(Employee emp) { // 조회
		List<Employee> empList = new ArrayList<>();
		String qry = "select * from tbl_employees "
//				+ "where emp_name = nvl('"+emp.getEmpName()+"', emp_name) "
				+ "where emp_name = nvl(?, emp_name) " // number, carchar2 아무거나 들어오면 알아서 처리
				+ "order by emp_no";
		System.out.println(qry);
		try {
//			Statement stmt = getConnect().createStatement();
			PreparedStatement stmt = getConnect().prepareStatement(qry);
			stmt.setString(1, emp.getEmpName()); // 첫번째 ?에 사원이름을 대입
//			stmt.setInt(1, emp.getEmpNo()); // 첫번째 ?에 사원번호를 대입
//			ResultSet rs = stmt.executeQuery(qry);
			ResultSet rs = stmt.executeQuery();
			// 조회결과
			while (rs.next()) {
				Employee empl = new Employee();
				empl.setEmpNo(rs.getInt("emp_no"));
				empl.setEmpName(rs.getString("emp_name"));
				empl.setTelNo(rs.getString("tel_no"));
				empl.setHireDate(rs.getDate("hire_date"));
				empl.setSalary(rs.getInt("salary"));

				empList.add(empl);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}
	
}
