<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("utf-8");  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ page import = "ch11.logon.CosmeticDBBean" %>
<%@ page import = "ch11.logon.CosmeticDataBean"%>
<%@ page import = "java.util.List" %> 
<title> Cosmetic </title>
<meta charset="utf-8" />
<link rel="stylesheet" href="cosmetic/assets/css/main (2).css" />
</head>
<body>

<%@ include file = "head.jsp" %>
<div class="cosmetic_wrap">
	<h1 class="cosmetic_h">&nbsp;&nbsp;Cosmetic</h1>
	<c:forEach items="${cosmeticList}" var="dto">
	  <div class="cosmetic_div">				
	    <img src = "${dto.image }" height="200px" width="200px"></img>
	    <div style="width:200px; text-overflow:ellipsis; overflow:hidden;"><nobr>제품명 : ${dto.productname }</nobr></div>
	    <div style="width:200px; text-overflow:ellipsis; overflow:hidden;"><nobr>평가내용 :${dto.contents } </nobr></div>
	    <div style="width:200px; text-overflow:ellipsis; overflow:hidden;"><nobr> 피부타입 : ${dto.beautytype }</nobr></div>
	    <div style="width:200px; text-overflow:ellipsis; overflow:hidden;"><nobr> 연령대 : ${dto.age }</nobr></div>
	  </div>
	</c:forEach>

<!--버튼 넘기는 부분 -->
<div class="cosmetic_btn_wrap">
<%
int count = 0;
CosmeticDBBean dbPro = CosmeticDBBean.getInstace();
count = dbPro.getArticleCount();
int pageSize = 5;
String curPage = request.getParameter("curPage");
if(curPage==null) curPage="1";

if (count > 0) {
   int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
   int startPage = 0;
   int currentPage = Integer.parseInt(curPage);

   if(currentPage % pageSize != 0)
      startPage = (int)(currentPage/pageSize)*pageSize + 1;
   else
      startPage = ((int)(currentPage/pageSize)-1)*pageSize + 1;
	
   
   int pageBlock = 5; //페이지들의 블럭단위 지정
   int endPage = startPage + pageBlock-1;
   
   if (endPage > pageCount) endPage = pageCount;
        
   if (startPage > pageBlock) {%>
       <a href="cosmetic.bbs?curPage=<%=startPage - pageBlock%>">[이전]</a>
      &nbsp;
<% }
   for (int i = startPage ; i <= endPage ; i++) {  
     if(currentPage == i){%>
     <a href="cosmetic.bbs?curPage=<%= i%>">[<%= i%>]</a>
     
    <%}else{ %> 
          <a href="cosmetic.bbs?curPage=<%= i%>">[<%= i%>]</a>
    <%}%>
      &nbsp; 
<% }
   if (endPage < pageCount) {  %>
      &nbsp;
      <a href="cosmetic.bbs?curPage=<%=startPage + pageBlock%>">[다음]</a>

<%
   }
}//108라인 if꺼
%>
</div>
<!--버튼 넘기는 부분 -->
</div>
<%@ include file = "foot.jsp" %>
    
</body>
</html>