<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- 이것 뭐에요....c (core)라는 태그를 써서 라이브러리를 정리하겟습니다... 아무거나 써도 상관 없음-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 연습</title>
</head>
<body>
	<h3>안녕하세요</h3>
	<c:set var="msg" value="Hello"></c:set>
	<p>msg의 값은 <c:out value="${msg}}"></c:out></p>
	
	<c:set var="myAge" value="25"></c:set>
	<!-- <c:set var="myAge" value="25" /> <!-- 마지막에 /를 입력하면 닫는태그를 갈음함 -->
	<c:if test="${myAge >= 20}">
		<p>당신은 성인입니다1</p>
	</c:if>
	
	<c:choose>
		<c:when test="${myAge >= 20} }">
			<p>당신은 성인입니다2</p>
		</c:when>
		<c:otherwise>
			<p>당신은 미성년자입니다</p>
		</c:otherwise>
	</c:choose>
 	
 	<c:forEach var="i" begin="1" end="10" step="2"><!-- step 증가값을 따로 정의하지 않으면 기본값은 1이다 -->
 		<p>i의 값은 ${i + 2}</p>
 	</c:forEach> 
</body>
</html>