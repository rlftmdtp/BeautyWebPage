<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "ch11.logon.LogonDBBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<%
String userid = request.getParameter("userid");
String passwd = request.getParameter("passwd");

LogonDBBean manager = LogonDBBean.getInstance();
int check = manager.userCheck(userid, passwd);
String name = manager.getName(userid);

if(check == 1)
{
	session.setAttribute("userid",userid);
	session.setAttribute("name",name);
}
else
{
	out.println("<script language=\"javascript\">");
	out.println("alert('아이디나 패스워드가 다릅니다')");
	out.println("history.back();");
	out.println("</script>");
	out.flush();
}
%>
<% response.sendRedirect("index.jsp"); %>
</body>
</html>