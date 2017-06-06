<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("euc-kr");  %>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Stylish CSS3 Form Effects - Hongkiat Demo</title>
  <meta name="author" content="Jake Rocheleau">
  <link rel="shortcut icon" href="http://static02.hongkiat.com/logo/hkdc/favicon.ico">
  <link rel="icon" href="http://static02.hongkiat.com/logo/hkdc/favicon.ico">
  <link rel="stylesheet" type="text/css" media="all" href="register/style.css">
  <link rel="stylesheet" type="text/css" media="all" href="register/sresponsive.css">
  
   <script src="js/jquery-3.1.0.min.js"></script>
   <script src="js/register.js"></script> 
</head>

<body>
<%@ include file="head.jsp" %>

<section id="container">
    <h2 id="type">REGISTER</h2><br/><br/>
	<form name="hongkiat" id="hongkiat-form" method="post" action="registerpro.jsp">
		<div id="wrapping" class="clearfix">
			<section id="aligned">
			<input type="text" name="name" id="name" placeholder="이름" autocomplete="off" tabindex="1" class="txtinput">

			<input type="text" name="userid" id="id" placeholder="아이디" autocomplete="off" tabindex="2" class="txtinput">
			</section>

			<section id="buttons2">
			<button type="button" name="idcheck" id="idcheck" class="idcheck">중복확인</button>
			
			<!--폼안에서는 버튼을 따로 하나만들려면 서브미트가아닌 버튼의 type을 버튼으로 지정한다(default가 submit으로 설정되어있다-->
			<!--<input type="submit" name="idcheck" id="idcheck" class="idcheck" value="중복확인">-->
			</section>

		    <section id="aligned">
					<input type="password" name="passwd" id="password" placeholder="비밀번호" autocomplete="off" tabindex="3" class="txtinput">
		
					<input type="password" name="pwcheck" id="pwcheck" placeholder="비밀번호 확인" autocomplete="off" tabindex="4" class="txtinput">
		
					<input type="text" name="birth" id="dob" placeholder="YY-MM-DD" tabindex="5" class="txtinput">
		    </section>

			<section id="aside" class="clearfix">
				<section id="recipientcase">
				  <h3>성별 : </h3>
					<select id="recipient" name="sex" tabindex="7" class="selmenu">
	  					<option >남성</option>
	  					<option >여성</option>
	  				</select>
				</section>
			</section>
		</div>
	  <section id="buttons">
	        <input type="reset" name="reset" id="resetbtn" class="resetbtn" value="초기화">
	        <input type="submit" name="submit" id="submitbtn" class="submitbtn" tabindex="8" value="가입하기">
	        <br style="clear:both;"/>
	  </section>
  </form>
 </section>

<%@ include file="foot.jsp" %>


</body>
</html>
