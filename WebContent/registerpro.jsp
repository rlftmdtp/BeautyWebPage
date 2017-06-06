<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("utf-8");  %>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> Cosmetic </title>
<%@page import = "ch11.logon.LogonDBBean" %>
<jsp:useBean id = "member" class = "ch11.logon.LogonData">
<jsp:setProperty name = "member" property = "*"/>
</jsp:useBean>
</head>
<body>

<%
LogonDBBean manager = LogonDBBean.getInstance();
manager.insertMember(member);
%>
<% response.sendRedirect("index.jsp"); %>

</body>
</html>