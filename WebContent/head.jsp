<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("euc-kr");  %>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> Beauty Sort </title>
<link rel="stylesheet" type="text/css" href="css/base.css" media="all" />
<link rel="stylesheet" type="text/css" href="css/main.css" media="all" />
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script> <!-- 제이쿼리 라이이브러리 연동 -->
<link rel="stylesheet" type="text/css" href="css/style.css" /><!-- 터치 슬라이드 스타일(CSS) 연동 -->
<script type="text/javascript" src="js/swipe.js"></script> <!-- 터치 슬라이드 플러그인 연동 -->
<script type="text/javascript" src="js/jquery.bxslider.min.js"></script>  <!-- bxSlider 플러그인 연동 -->
<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.min.js"></script> <!-- UI 플러그인 연동 -->
<script type="text/javascript" src="js/jquery.cookie.js"></script>  <!-- 쿠키 플러그인 연동 -->
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/main.js"></script>

<!-- 로그인 폼 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<title>Custom Login Form Styling</title>
<meta name="description" content="Custom Login Form Styling with CSS3" />
<meta name="keywords" content="css3, login, form, custom, input, submit, button, html5, placeholder" />
<meta name="author" content="Codrops" />
<link rel="shortcut icon" href="../favicon.ico"> 
<link rel="stylesheet" type="text/css" href="loginwindow/css/style.css" />
<script src="js/modernizr.custom.63321.js"></script>
<!--[if lte IE 7]><style>.main{display:none;} .support-note .note-ie{display:block;}</style><![endif]-->
 
</head>

<body>
<div id="wrap">
<dl class="hide">
 <dt>스킵 메뉴</dt>
 <dd><a href="#">본문 바로가기</a></dd>
</dl>
<div id="header">
<div class="logo">
  <h1 class="logo"><a href="index.jsp"><img src="images/logo.jpg" alt="Beauty Sort" /></a></h1>
  </div>
  <dl id="util_menu">
     <dt class="hide">유틸 메뉴</dt>
     <dd class="util_first">
        <ul>
          <%
 String userid="";
 try{
  userid=(String)session.getAttribute("userid");
  
  if(userid==null||userid.equals("")){%>
           <li class="login_wrap">
           <a href="#">
           <img src="images/util_menu_1.gif" alt="로그인" />
           </a>
             <!-- 로그인 폼 -->
           <section class="main">
				<form class="form-5 clearfix" action=loginpro.jsp method=post id="login_f">
				    <p>
				        <input type="text" id="login" name="userid" placeholder="Username">
				        <input type="password" name="passwd" id="password" placeholder="Password"> 
				        
				    </p>
				    <button type="submit" name="submit">
				    	<i class="icon-arrow-right"></i>
				    	<span>Sign in</span>
				    </button>     
				</form>
			</section>		
           </li>
            <% }else{ %>
              <li>
              <a href="logout.jsp">
             <img src="images/logout.gif" alt="로그아웃" />
              </a>
            <% }}catch(Exception e){out.print("지금잘못됫다");} %>
           
           <li class="join_wrap">
            <a href="register.jsp"><img src="images/util_menu_2.gif" alt="회원가입" /></a>
           </li>
           <li>
           <a href="mypage.jsp"><img src="images/mypage.gif" alt="마이페이지"/></a>
           </li>
        </ul>
     </dd>
 
  </dl>
 
 
  <h2 class="hide">메인메뉴</h2>
  <ul id="gnb">
     <li><a href="#"><img src="images/gnb_1_out.gif" alt="" /></a>
        <ul class="sub1">
           <li><a href="#">홈으로이동</a></li>
        </ul>
     </li>
     <li><a href="#"><img src="images/gnb_2_out.gif" alt="" /></a>
        <ul class="sub2">
           <li><a href="#">Beauty Sort 소개</a></li>
        </ul>
     </li>
     <li><a href="cosmetic.bbs"><img src="images/gnb_3_out.gif" alt="" /></a>
        <ul class="sub3">
           <li><a href="cosmetic_toner.jsp">토너&에멀젼</a></li>
           <li><a href="#">썬크림</a></li>
           <li><a href="#">리무버</a></li>
        </ul>
     </li>
     <li><a href="boardList.bbs"><img src="images/gnb_4_out.gif" alt="커뮤니티" /></a>
        <ul class="sub4">
           <li><a href="boardList.bbs">복합성</a></li>
           <li><a href="#">건성</a></li>
           <li><a href="#">중성</a></li>
           <li><a href="#">지성</a></li>
           <li><a href="#">민감성</a></li>                      
        </ul>
     </li>
  </ul>
  <p id="total_btn">
      <a href="#">
        <img src="images/allmenu_btn_out.gif" alt="전체 메뉴" />
      </a>
  </p>
  <div id="total_menu">
     <dl>
        <dt><img src="images/allmenu_title_1.gif" alt="홈" /></dt>
        <dd>
           <ul>
              <li><a href="#">홈으로이동</a></li>
           </ul>
        </dd>
     </dl>
     <dl>
        <dt><img src="images/allmenu_title_2.gif" alt="프로필" /></dt>
        <dd>
           <ul>
              <li><a href="#">회사소개</a></li>
              <li><a href="#">출간분야</a></li>
              <li><a href="#">찾아오시는 길</a></li>
           </ul>
        </dd>
     </dl>
     <dl>
        <dt><img src="images/allmenu_title_3.gif" alt="화장품" /></dt>
        <dd>
           <ul>
              <li><a href="#">신간도서</a></li>
              <li><a href="#">인기도서</a></li>
              <li><a href="#">추천도서</a></li>
           </ul>
        </dd>
     </dl>
     <dl>
        <dt><img src="images/allmenu_title_4.gif" alt="커뮤니티" /></dt>
        <dd>
           <ul>
              <li><a href="#">고객문의</a></li>
              <li><a href="#">저자문의</a></li>
              <li><a href="#">자료요청</a></li>
              <li><a href="#">자주묻는 질문</a></li>
           </ul>
        </dd>
     </dl>
     <p id="total_close">
        <a href="#">
            <img src="images/allmenu_close_btn.gif" alt="전체메뉴 닫기" />
        </a>
     </p>
  </div>
  <p id="date_wrap">
      <span class="year">0000</span> 년
      <span class="month">00</span> 월
      <span class="date">00</span>일
   </p>
</div>
<hr />

</body>
</html>