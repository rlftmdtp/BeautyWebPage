<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("utf-8");  %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "board.*" %>
<%@ page import = "java.util.*" %>
<link rel="stylesheet" href="daumeditor/css/editor.css" type="text/css" charset="utf-8"/>
<script src="daumeditor/js/editor_loader.js?" type="text/javascript" charset="utf-8"></script>

<html>
	<head>
		<title>게시판 글 열람</title>
	</head>
	<body>
	<%@ include file ="head.jsp" %>
		<h3>게시판 글 열람</h3>
		<table>
			<tr>
				<td colspan="4" align="right"><a href="boardList.bbs">[목록으로]</a></td>
			</tr>
			<tr>
				<td>글 제목</td>
				<td colspan="3"><input type="text" name="subject" maxlength="50" size="50" value="${boardRead.subject}" disabled="disabled"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="name" maxlength="20" size="20" value="${boardRead.name}" disabled="disabled"></td>
				<td>조회수:${boardRead.readCnt},</td>
				<td>답글수:${boardRead.childCnt}</td>
			</tr>
			<tr>
				<td>본문</td>
				<td>${boardRead.content}</td>
			</tr>
		</table>
	
		<div>
		<c:forEach items="${boardReply2Form}" var="dto">
		ID:${dto.userid }  ${dto.writeDate } ${dto.writeTime } <br>
		${dto.content }
		<c:if test="${dto.userid==sessionScope.userid }" >
		<a href="boardReply2.bbs?num=${boardRead.num }">[수정]</a>
					   <a href="">[삭제]</a>
		</c:if>		
		<br><br>
		</c:forEach>
	    </div>
	   
		<form action="boardReply2.bbs?num=${boardRead.num}" method="post">
		<div class = "box-reply">
		<input type="hidden" name="userid" value="${userid }">
		<textarea name="content" id="content" cols="50" rows="2" maxlength="6000" style="overflow: hidden; line-height: 14px; height: 39px;"></textarea>
		<input type="submit" value="댓글" >
		</div>
		</form>
		
		
		<table>
		<tr>
				<td colspan="4" align="right">
				<c:if test="${boardRead.name==sessionScope.userid }">
					<a href="boardUpdateCheck.bbs?num=${boardRead.num}">[수정]</a>
					<a href="boardDeleteCheck.bbs?num=${boardRead.num}">[삭제]</a>
				</c:if>
					<a href="boardReplyForm.bbs?num=${boardRead.num}">[답글]</a>
				</td>
			</tr>
		</table>
	
		<%@ include file ="foot.jsp" %>
	</body>
</html>