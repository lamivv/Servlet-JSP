<%@page import="com.yedam.PageVO"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="com.yedam.dao.BoardDAO"%>
<%@page import="com.yedam.vo.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- boardList.jsp -->
<!--지시자 : page라는 지시자 값으로 추가적인 정보를 담음-->
<!-- 액션태그: 현재 페이지의 해당 위치에 다른 페이지를 불러들일 수 있는 기능 -->
<!-- html주석문 -->
<!-- <//%
//자바코드작성가능
String msg = "Hello";
System.out.println(msg);
// boardList.do -> request라는 객체를 전달 -> boardList.jsp 
String result = (String) request.getAttribute("msg");
// 임포트... Ctrl + Space
// BoardListControl의 list의 값을 읽어오기
List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
// Control에서 paging의 값을 얻어오기
PageVO paging = (PageVO) request.getAttribute("paging");
%> -->

<!-- <p>msg의 값은<//%=paging%></p>  -->
<!-- ${list }
${paging } -->

<h3>게시글 목록(boardList.jsp)</h3>
<!-- 게시글 검색 -->
<form action="boardList.do">
	<div class="center">
		<div class="row">
			<div class="col-sm-4">
				<select class="form-control" name="searchCondition" >
					<option value="">선택하세요</option>
					<option value="T" ${searchCondition == "T" ? "selected" : "" }>제목</option>
					<option value="W" ${searchCondition == "W" ? "selected" : "" }>작성자</option>
					<option value="TW" ${searchCondition == "TW" ? "selected" : "" }>제목&작성자</option>
				</select>
			</div>
			<div class="col-sm-5">
				<input type="text" class="form-control" name="keyword" value="${keyword }">
			</div>
			<div class="col-sm-2">
				<input type="submit" value="조회" class="btn btn-primary">
			</div>
		</div>
	</div>
</form>

<!-- 게시글 목록 -->
<table class="table table-striped">
	<thead>
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일자</th>
		<th>조회수</th>
	</thead>
	<tbody>
		<c:forEach var="board" items="${list }">
			<tr>
				<td>
					<c:out value="${board.boardNo }"></c:out>
				</td>
				<td><a href="board.do?bno=${board.boardNo }">
						<c:out value="${board.title }"></c:out>
					</a></td>
				<td>
					<c:out value="${board.writer }"></c:out>
				</td>
				<td>
					<c:out value="${board.writeDate }"></c:out>
				</td>
				<td>
					<c:out value="${board.viewCnt }"></c:out>
				</td>
			</tr>
		</c:forEach>
		<!-- <//%
		for (BoardVO board : list) {
		%>
		<tr>
			<td><//%=board.getBoardNo()%></td>
			<td><a href="board.do?bno=<//%=board.getBoardNo()%>"><//%=board.getTitle()%></a></td>
			<td><//%=board.getWriter()%></td>
			<td><//%=board.getWriteDate()%></td>
			<td><//%=board.getViewCnt()%></td>
		</tr>
		<//%
		}
		%> -->
	</tbody>
</table>

<!-- paging 시작지점 -->
<nav aria-label="...">
	<ul class="pagination">
		<!-- 이전 페이지 여부 -->
		<c:choose>
			<c:when test="${paging.prev }">
				<li class="page-item"><a class="page-link" href="boardList.do?page=${paging.startPage - 1 }&searchCondition=${searchCondition}&keyword=${keyword}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled"><span class="page-link">Previous</span></li>
			</c:otherwise>
		</c:choose>
		<!-- <//% if(paging.isPrev()){ %>
		<li class="page-item"><a class="page-link" href="boardList.do?page=<//%=paging.getStartPage()-1 %>">Previous</a></li>
		<//%} else {%>
		<li class="page-item disabled"><span class="page-link">Previous</span></li>
		<//%}%> -->

		<!-- 페이지 start ~ end 반복 시작지점 -->
		<c:forEach var="p" begin="${paging.startPage }" end="${paging.endPage }">
			<c:choose>
				<c:when test="${p == paging.currentPage }">
					<li class="page-item active" aria-current="page"><span class="page-link">
							<c:out value="${p }"></c:out>
						</span></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="boardList.do?page=${p }&searchCondition=${searchCondition}&keyword=${keyword}">
							<c:out value="${p }"></c:out>
						</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<!-- <//%for(int p = paging.getStartPage(); p <= paging.getEndPage(); p++) {%>
		<//% if (p == paging.getCurrentPage()) {%>
		<li class="page-item active" aria-current="page"><span class="page-link"><//%=p %></span></li>
		<//%} else {%>
		<li class="page-item"><a class="page-link" href="boardList.do?page=<//%=p %>"><//%=p %></a></li>
		<//%}} %>  -->
		<!-- 페이지 start ~ end 반복 종료시점 -->

		<!-- 이후 페이지 여부 -->
		<c:choose>
			<c:when test="${paging.next }">
				<li class="page-item"><a class="page-link" href="boardList.do?page=${paging.endPage + 1 }&searchCondition=${searchCondition}&keyword=${keyword}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled"><span class="page-link">Next</span></li>
			</c:otherwise>
		</c:choose>
		<!-- <//% if(paging.isNext()){ %>
		<li class="page-item"><a class="page-link" href="boardList.do?page=<//%=paging.getEndPage()+1 %>">Next</a></li>
		<//%} else {%>
		<li class="page-item disabled"><span class="page-link">Next</span></li>
		<//%}%>  -->
	</ul>
</nav>
<!-- paging 종료지점 -->
