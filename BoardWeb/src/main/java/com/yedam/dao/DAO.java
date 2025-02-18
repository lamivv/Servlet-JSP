package com.yedam.dao;
/*
 * EmpDAO, StudentDAO...
 * 상속 시켜주기 위함
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAO {
	// Connection 객체, statement, preparedStatment, ResultSet
	Connection conn = null;
	Statement stmt; // SQL의 쿼리를 실행하고 결과를 반환하는 클래스
	PreparedStatement psmt; 
	ResultSet rs; // 쿼리한 결과를 담아두기 위한 Set 컬렉션

	Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 오라클DB의 접속정보 // xe는 접속할 데이터베이스 이름
		String user = "hr";
		String password = "hr";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	} // end of Connection getConnect()
}
