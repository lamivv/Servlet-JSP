<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="com.yedam.dao.BoardDAO"%>
<%@page import="com.yedam.vo.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--지시자 : page라는 지시자 값으로 추가적인 정보를 담음-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- html주석문 -->
	<%
	//자바코드작성가능
	String msg = "Hello";
	System.out.println(msg);
	// boardList.do -> request라는 객체를 전달 -> boardList.jsp 
	String result = (String) request.getAttribute("msg");
	// 임포트... Ctrl + Space
	List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
	%>
	<p>
		msg의 값은<%=result%></p>
	<h3>게시글 목록</h3>
	<table border="2">
		<thead>
			<th>글번호</th>
			<th>글 제목</th>
			<th>작성자</th>
			<th>작성일자</th>
			<th>조회수</th>
		</thead>
		<tbody>
		<%
		for (BoardVO board : list) {
		%>
		<tr>
			<td><%=board.getBoardNo() %></td>
			<td><%=board.getTitle() %></td>
			<td><%=board.getWriter() %></td>
			<td><%=board.getWriteDate() %></td>
			<td><%=board.getViewCnt() %></td>
		</tr>
		<%
		}
		%>
		</tbody>
	</table>
</body>
</html>