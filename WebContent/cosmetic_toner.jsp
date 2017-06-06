<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("utf-8");  %>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> Cosmetic </title>
<%@ page import = "ch11.logon.CosmeticDBBean" %>
<%@ page import = "ch11.logon.CosmeticDataBean"%>
<%@ page import = "java.util.List" %>

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="cosmetic/assets/css/main (2).css" />
<link rel="stylesheet" href="stars/style.css" />
<script src="js/jquery-3.1.0.min.js"></script>
</head>
<body>

<%@ include file ="head.jsp" %>

<%
int pageSize = 8;
String pageNum = request.getParameter("pageNum");
if(pageNum == null)
	pageNum = "1";

int count = 0;
int currentPage = Integer.parseInt(pageNum);

List<CosmeticDataBean> articleList = null;
CosmeticDBBean dbPro = CosmeticDBBean.getInstace();
count = dbPro.getArticleCount();

if(count == (currentPage-1)*pageSize)
	currentPage = -1;

int startRow = (currentPage-1)*pageSize + 1; // 현재페이지에서의 시작글 번호

try{
	if(count>0) // 테이블에 저장된 글이 있을경우
	articleList = dbPro.TonergetArticles(startRow,pageSize);
}
catch(Exception e){System.out.println("1");}

try{
	if(articleList.isEmpty())
		count = 0;
}
catch(Exception e){System.out.println("2:");}%>
      <!-- Content -->
         <div id="content">
            <div class="inner">
            
               <!-- Post -->
                  <article class="box post post-excerpt">
                     <header>
                        <h2><a href="#">토너&에멀젼</a></h2>
                     </header>
                      <%try{ 
                     for( int i =0; i<articleList.size(); i++){
                    	 CosmeticDataBean article = articleList.get(i);%>
                     <section id="object">
                     <img src = "<%= article.getImage()%>" height="200px" width="200px"></img>
                     <p><div class="ellipsis">제품명 : <%= article.getProductname()%></div></p>
                     <p>피부타입 : <%= article.getBeautytype()%></p>
                     <p>연령대 : <%= article.getAge()%></p>
                     <p>코멘트 : <%= article.getContents() %>
                     <ul class="rating-w-fonts"> 평점 : <% int num =Integer.parseInt(article.getGrade());
                     			for(int j=0; j<num; j++){ %>
					 		 <li class="rated">R</li>
					 		 <%} %>
                     </ul>	
                     </section>
                     <% }}
                     catch(Exception e){System.out.println("3");}%>
                  </article>
            </div>
         </div>

<%@ include file ="foot.jsp" %>
</body>
</html>