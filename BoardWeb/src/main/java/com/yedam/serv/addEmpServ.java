package com.yedam.serv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.EmpDAO;
import com.yedam.vo.Employee;

// init - service - destroy : 서블릿의 생명주기
@WebServlet("/addEmpServlet")
public class addEmpServ extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8"); // 한글타입 표현가능해짐
		String eno = req.getParameter("empNo"); // input name : empNo의 param에 담겨있는 값을 반환
		String ename = req.getParameter("empName");
		String tel = req.getParameter("telNo");

		// db등록
		EmpDAO edao = new EmpDAO();
		boolean result = edao.registerEmp(new Employee(Integer.parseInt(eno), ename, tel));
		if (result) {
			resp.getWriter().print("처리성공");
//			resp.sendRedirect("sample"); // addEmpServlet -> sample 페이지이동 // 목록보여주는 페이지
		} else {
			resp.getWriter().print("처리실패");
		}
	}
}
