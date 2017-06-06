<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("euc-kr");  %>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>  
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
</head>

<body>

<%@ include file="head.jsp" %>

 <section class="main">
        <div class="ia-container">
          <figure>
            <img src="banner/images/01.png" alt="image01" />
            <input type="radio" name="radio-set" checked="checked"/>
            <figcaption><span>Differentiation</span></figcaption>

            <figure>
              <img src="banner/images/02.png" alt="image02" />
              <input type="radio" name="radio-set" />
              <figcaption><span>Differentiation</span></figcaption>

              <figure>
                <img src="banner/images/03.png" alt="image03" />
                <input type="radio" name="radio-set" />
                <figcaption><span>Categorization</span></figcaption>

                <figure>
                  <img src="banner/images/04.png" alt="image04" />
                  <input type="radio" name="radio-set" />
                  <figcaption><span>Categorization</span></figcaption>

                  <figure>
                    <img src="banner/images/05.png" alt="image05" />
                    <input type="radio" name="radio-set" />
                    <figcaption><span>Personalization</span></figcaption>

                    <figure>
                      <img src="banner/images/06.png" alt="image06" />
                      <input type="radio" name="radio-set" />
                      <figcaption><span>Personalization</span></figcaption>

                      <figure>
                        <img src="banner/images/07.png" alt="image07" />
                        <input type="radio" name="radio-set" />
                        <figcaption><span>Beauty Sort</span></figcaption>

                        <figure>
                          <img src="banner/images/08.png" alt="image08" />
                          <input id="ia-selector-last" type="radio" name="radio-set" />
                          <figcaption><span>Beauty Sort</span></figcaption>
                        </figure>
                      </figure>
                    </figure>
                  </figure>
                </figure>
              </figure>
            </figure>
          </figure>
        </div><!-- ia-container -->
   </section>

<%@ include file="foot.jsp" %>
</body>
</html>