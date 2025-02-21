<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="includes/header.jsp"></jsp:include>
<!-- <//%
	String logId = (String) session.getAttribute("loginId");
%> -->
<h3>글등록화면(addForm.jsp)</h3>
<form action="addBoard.do" method="post" enctype="multipart/form-data"> <!-- 파일을 추가할때는 enctype를 추가해주어야 한다 -->
	<table class="table">
		<tr>
			<th>제목</th>
			<td><input class="form-control" type="text" name="title"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea class="form-control" rows="3" cols="45"
					name="content"></textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<!-- <td><input class="form-control" type="hidden" name="writer" value="<//%=logId %>"><//%=logId %></td> -->
			<td><input class="form-control" type="hidden" name="writer" value="${loginId }">${loginId}</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><input type="file" name="img" class="form-control"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input class="btn btn-primary" type="submit" value="등록"> 
			<input class="btn btn-warning" type="reset" value="취소"></td>
		</tr>
	</table>
</form>
<jsp:include page="includes/footer.jsp"></jsp:include>