package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.Student;

public class StudentDAO extends DAO {
	// 학생 인적사항 등록
	public boolean addStudent(Student student) {
		String sql = "insert into tbl_student (student_no, student_name, phone, address) " //
				+ "values(?, ?, ?, ?)";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setString(1, student.getStdNo()); // 첫번째 ?에 값을 할당
			psmt.setString(2, student.getStdName());
			psmt.setString(3, student.getStdPhone());
			psmt.setString(4, student.getStdAddress());
			
			// 쿼리실행
			int r = psmt.executeUpdate(); // 처리된 건수반환
			if(r>0) {
				return true; // 등록 성공
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 등록 실패
	}
	
	
	// 학생목록을 반환하는 메소드 // 참고) EmpDAO.search()
	public List<Student> studentList() {
		List<Student> stdList = new ArrayList<>();
		String qry = "select * from tbl_student ";
		qry += "order by student_no";
//		System.out.println(qry);

		try {
			psmt = getConnect().prepareStatement(qry);
			rs = psmt.executeQuery();

			while (rs.next()) {
				Student stdl = new Student();
				stdl.setStdNo(rs.getString("student_no"));
				stdl.setStdName(rs.getString("student_name"));
				stdl.setStdPhone(rs.getString("phone"));
				stdl.setStdAddress(rs.getString("address"));

				stdList.add(stdl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stdList;
	} // end of studentList()
	
}
