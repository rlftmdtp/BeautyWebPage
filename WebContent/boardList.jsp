<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("utf-8");  %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>게시판 목록 조회</title>
	</head>
	<body>
	<%@ include file = "head.jsp" %>
	<% if(userid==null||userid.equals("")){%>
		게시판이용은 로그인을 하셔야합니다
		<%}else {%>
		<div id="board_wrap">
		<h3 id="board_h">&nbsp;&nbsp;복합성 게시판</h3>
		<table border="1" id="board_table">
			<tr>
				<td colspan="7" align="right" style="color:red;">
					<a href="boardWriteForm.bbs">[새글쓰기]</a>
				</td>
			</tr>
			<tr>
				<td>No</td>
				<td>제목</td>
				<td>작성자</td>
				<td>날짜</td>
				<td>시간</td>
				<td>조회</td>
				<td>답글</td>
			</tr>		
			<c:forEach items="${boardList}" var="dto">
				<tr>
					<td><a href="boardRead.bbs?num=${dto.num}">${dto.num}</a></td>
					
					<td>
						<c:forEach begin="1" end="${dto.lev }">
							<%= "&nbsp;&nbsp;" %>
						</c:forEach>
						<a href="boardRead.bbs?num=${dto.num }">${dto.subject }</a></td>
					<td>${dto.name }</td>
					<td>${dto.writeDate }</td>
					<td>${dto.writeTime }</td>
					<td>${dto.readCnt }</td>
					<td>${dto.childCnt }</td>
				</tr>
			</c:forEach>
			
			<tr id="board_bottom">
				<td colspan="7">
				<a href="boardList.bbs">[첫 페이지로]</a>
				<nav>
					<c:forEach var ="i" begin="1" end ="${pageCnt}">
						<a href="boardList.bbs?curPage=${i}">[${i}]</a>
					</c:forEach>
				</nav>
				</td>
			</tr>
			
			<tr>
				<td colspan="7" align="center">
					<form action="boardSearch.bbs" method="post">
						<select name="searchOption">
							<option value="subject">제목</option>
							<option value="content">본문</option>
							<option value="both">제목+본문</option>
							<option value="name">작성자</option>
						</select>
						<input type="text" name="searchWord">
						<input type="submit" value="검색">
					</form>
				</td>
			</tr>
		</table>
		</div>
		<%} %>
	<%@ include file = "foot.jsp" %>
	</body>
</html>