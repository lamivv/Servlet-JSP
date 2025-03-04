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
import com.yedam.control.AddFormControl;
import com.yedam.control.AddMemberControl;
import com.yedam.control.AddReplyControl;
import com.yedam.control.AjaxControl;
import com.yedam.control.ApiControl;
import com.yedam.control.BoardControl;
import com.yedam.control.BoardListControl;
import com.yedam.control.ChartControl;
import com.yedam.control.ChartData;
import com.yedam.control.DataControl;
import com.yedam.control.DataTableControl;
import com.yedam.control.FullAddDataControl;
import com.yedam.control.FullControl;
import com.yedam.control.FullData;
import com.yedam.control.FullDeleteDataControl;
import com.yedam.control.ReplyCount;
import com.yedam.control.LoginControl;
import com.yedam.control.LogoutControl;
import com.yedam.control.MainControl;
import com.yedam.control.MapControl;
import com.yedam.control.MemberListControl;
import com.yedam.control.ModifyBoardControl;
import com.yedam.control.ModifyControl;
import com.yedam.control.RemoveBoardControl;
import com.yedam.control.RemoveMemberControl;
import com.yedam.control.RemoveReplyControl;
import com.yedam.control.ReplyListControl;
import com.yedam.dao.Control;

/*
 * MVC에서 Control역할
 * url요청 -> 서블릿
 */
//@WebServlet("*.do")
public class FrontController extends HttpServlet {
	Map<String, Control> map; // key = url , value = servlet
	
	// 생성자
	public FrontController() {
		map = new HashMap<>(); // map 필드의 초기화
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
//		map.put("url", "servlet"); // addStudent.do AddStudentServlet
		map.put("/main.do", new MainControl());
		map.put("/boardList.do", new BoardListControl()); // 글 목록
		map.put("/addForm.do", new AddFormControl()); // 등록 화면
		map.put("/addBoard.do", new AddBoardControl()); // 등록 처리
		map.put("/board.do", new BoardControl()); // 글 상세보기
		map.put("/modifyForm.do", new ModifyControl()); // 글 수정화면
		map.put("/modifyBoard.do", new ModifyBoardControl()); // 글 수정처리
		map.put("/removeBoard.do", new RemoveBoardControl()); // 삭제화면 삭제처리
		
		// 로그인관련
		map.put("/loginForm.do", new LoginControl()); // 로그인화면
		map.put("/login.do", new LoginControl()); // 로그인처리
		map.put("/logout.do", new LogoutControl()); // 로그아웃
		
		// 회원관련(admin)
		map.put("/memberList.do", new MemberListControl()); // 회원목록
		map.put("/testAjax.do", new AjaxControl()); //
		map.put("/testData.do", new DataControl()); //
		map.put("/removeMember.do", new RemoveMemberControl()); // 회원삭제
		map.put("/addMember.do", new AddMemberControl()); // 회원등록
		
		// 댓글관련
		map.put("/replyList.do", new ReplyListControl()); // 댓글목록
		map.put("/addReply.do", new AddReplyControl()); // 댓글등록
		map.put("/removeReply.do", new RemoveReplyControl()); // 댓글삭제
		map.put("/getReplyCnt.do", new ReplyCount()); // 댓글페이징
		
		// 차트
		map.put("/chart.do", new ChartControl());
		map.put("/chartData.do", new ChartData());
		
		// datatable 관련
		map.put("/datatable.do", new DataTableControl());
		
		// full캘린더 관련 
		map.put("/full.do", new FullControl()); // 캘린더 페이지
		map.put("/fullData.do", new FullData()); // 조회
		map.put("/fullAddData.do", new FullAddDataControl()); // db등록
		map.put("/fullDeleteData.do", new FullDeleteDataControl()); // db삭제
		
		// api
		map.put("/api.do", new ApiControl()); // api
		map.put("/map.do", new MapControl()); // map
		
		
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
