package com.yedam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.control.AddBoardControl;
import com.yedam.control.BoardListControl;
import com.yedam.dao.Control;

/*
 * MVC에서 Control역할
 * url요청 -> 서블릿
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	Map<String, Control> map; // key = url , value = servlet
	
	// 생성자
	public FrontController() {
		map = new HashMap<>(); // map 필드의 초기화
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
//		map.put("url", "servlet"); // addStudent.do AddStudentServlet
		map.put("/boardList.do", new BoardListControl());
		map.put("/addBoard.do", new AddBoardControl());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("front control");
		// http://localhost:8080/BoardWeb/addStudent.do => url
		// 프로토콜 서버 포트를 뺀 다음에 가져오는 정보를 uri라고 함
		// /BoardWeb/addStudent.do => uri
		String uri = req.getRequestURI();
		String context = req.getContextPath();  // 프로젝트 이름 "/BoardWeb"
		String page = uri.substring(context.length());
		
		System.out.println(page);
		
		// get 메소드 : map컬렉션에서 key를 입력하면 value를 반환
		Control control = map.get(page);
		control.exec(req, resp);
	}

}
