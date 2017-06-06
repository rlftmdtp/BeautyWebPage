<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("utf-8");  %>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>게시판 글쓰기</title>
<script type="text/javascript" src="se2/js/HuskyEZCreator.js" charset="utf-8"></script>
</head>
<body>

<%@ include file = "head.jsp" %>
<br><br>
<form action="boardWrite.bbs" method="post">
<table>
<tr>
<td colspan="4" align="right"><a href="boardList.bbs">[목록으로]</a></td>
</tr>
<tr>
<td>글제목</td>
<td colspan="3"><input type="text" name="subject" maxlength="50" size="50"></td>
</tr>
<tr>
<td>작성자</td>
<td><input type="text" value="${sessionScope.userid }" disabled="disabled" maxlength="20" size="20">
    <input type="hidden" name="name" value="${sessionScope.userid }">
</td>

</tr>
</table>
	<textarea name="content" id="content" rows="10" cols="100" style="width:766px; height:412px; display:none;"></textarea>
	<!--textarea name="ir1" id="ir1" rows="10" cols="100" style="width:100%; height:412px; min-width:610px; display:none;"></textarea-->
	<p>
		<input type="button" onclick="submitContents(this);" value="글 작성하기" />
	</p>
</form>

<script type="text/javascript">
var oEditors = [];

nhn.husky.EZCreator.createInIFrame({

    oAppRef: oEditors,

    elPlaceHolder: "content",

    sSkinURI: "se2/SmartEditor2Skin.html",

    fCreator: "createSEditor2"

});

function submitContents(elClickedObj) {
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
	
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}

</script>
<%@ include file = "foot.jsp" %>

</body>
</html>