<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("utf-8");  %>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>커뮤니티</title>  
<!--banner source-->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Image Accordion with CSS3" />
<meta name="keywords" content="accordion, images, slideshow, css3, css-only, web development, component, tutorial" />
<meta name="author" content="Ring Wing for Codrops" />
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css" href="banner/css/demo.css" />
<link rel="stylesheet" type="text/css" href="banner/css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:700,300,300italic' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/modernizr.custom.79639.js"></script>
<!--[if lte IE 8]><style>.main{display:none;} .support-note .note-ie{display:block;}</style><![endif]-->
<!--banner source finish-->

<!--네이버스마트에디터-->
<script type="text/javascript" src="se2/js/HuskyEZCreator.js" charset="utf-8"></script>
</head>

<body>

<%@ include file="head.jsp" %>
 <%
 try{
  userid=(String)session.getAttribute("userid");
  
  if(userid==null||userid.equals("")){%>
  <div id = "display_board" class="box2">로그인하세요 !게시판을 회원만 볼 수 있습니다</div>
  <% }else{ %>
 <%@ include file ="boardList.bbs" %>
<% }}catch(Exception e){out.print("지금잘못됫다");} %>
<%@ include file="foot.jsp" %>
</body>
</html>