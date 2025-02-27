package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.DataSource;
import com.yedam.dao.BoardDAO;
import com.yedam.dao.Control;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class AddBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 멀티파트 요청에 대한 처리
		// 2종류의 파일타입 처리(multipart) &title=test&stream
		String saveDir = req.getServletContext().getRealPath("images"); //getServletContext => 제일 상위 폴더.. BoardWeb
		MultipartRequest mr = new MultipartRequest(
				req // 1. 요청객체 (req)
			   ,saveDir // 2. 파일저장경로
			   ,1024*1024*5	// 3.최대파일크기(5mb)
			   ,"utf-8"// 4. 파일이름의 인코딩방식(파일이름이 한글일 수도 있으니)
			   ,new DefaultFileRenamePolicy() // 5. 리네임정책(같은이름의 파일이 추가될 경우)
				);
		
		// 3개의 파라미터 활용해서 db에 저장하고 글 목록으로 이동
		// title=등록연습&content=연습%21+&writer=user01
//		String title = req.getParameter("title"); // name속성 title의 값 = 등록연습
//		String content = req.getParameter("content"); // name속성 content의 값 = 연습
//		String writer = req.getParameter("writer"); // name속성 writer의 값 = user01
//	
		
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String img = mr.getFilesystemName("img");
		
		// insertBoard의 매개값으로 들어갈 BoardVO 개체 생성
		BoardVO bvo = new BoardVO();
		bvo.setTitle(title);
		bvo.setContent(content);
		bvo.setWriter(writer);
		bvo.setImg(img); // 추가한 img컬럼

//		BoardDAO bdao = new BoardDAO();
		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		if (mapper.insertBoard(bvo) == 1) {
			sqlSession.commit(true); // 커밋
			System.out.println("등록성공");
			resp.sendRedirect("boardList.do");
		} else {
			System.out.println("등록실패");
		}
	}
}
