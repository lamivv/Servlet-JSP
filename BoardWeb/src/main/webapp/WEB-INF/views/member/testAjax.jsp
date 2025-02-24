<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3>Ajax연습페이지</h3>
이름: <input type="text" name="name"><br>
나이: <input type="number" name="age">

<h3>회원목록</h3>
<table class="table">
<thead>
<tr><th>아이디</th><th>비밀번호</th><th>이름</th><th>권한</th><th>삭제</th></tr>
<tbody id="list">
<!-- 회원목록 나오는 곳  -->
</tbody>
</thead>
</table>
<script src="js/member.js"></script>
