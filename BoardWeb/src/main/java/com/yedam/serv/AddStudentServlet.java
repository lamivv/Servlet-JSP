package com.yedam.serv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.StudentDAO;
import com.yedam.vo.Student;

@WebServlet("/addStudentServ")
public class AddStudentServlet extends HttpServlet {
	// param의 값을 활용 -> db입력
	// 처리성공/ 처리실패 메시지
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // 요청정보의 한글처리 form method가 post 타입일 때
		resp.setContentType("text/html;charset=utf-8");
		String sno = req.getParameter("std_no");
		String sname = req.getParameter("std_name");
		String sphone = req.getParameter("tel_no");
		String saddr = req.getParameter("std_addr");
		
		StudentDAO sdao = new StudentDAO();
		Student addstd = new Student(sno,sname,sphone,saddr);
		
		// db등록 처리결과
		boolean result =sdao.addStudent(addstd);
		if(result) {
			resp.getWriter().print("처리성공");
//			resp.sendRedirect("sample");
		} else {
			resp.getWriter().print("처리실패");
		}
	}
}
