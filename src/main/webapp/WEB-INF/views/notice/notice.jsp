<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="root" value="${pageContext.request.contextPath}" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="../layout/header.jsp"></jsp:include>
    <link href="${root}/resources/css/home.css" rel="stylesheet">
    <link href="${root}/resources/css/boardcontent.css" rel="stylesheet">
    <link href="${root}/resources/css/ntc.css" rel="stylesheet">
    <title>INBYUL</title>
</head>
<script>
 var uid = '<sec:authentication property="principal.username"/>';
 var root = '${pageContext.request.contextPath}';
 var _csrf_name = "${_csrf.headerName}";
	var _csrf_token = "${_csrf.token}";
	var page  = 1; 
	var addbool = true;
</script>
<body>
	<section class="m_container">
        <main style="flex-direction: column;">
         
        </main>
    </section>
    
    
    	<div id="loading">
		<svg version="1.1" id="L9" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
  			viewBox="0 0 100 100" enable-background="new 0 0 0 0" xml:space="preserve"  class="svg-sp">
		    <path fill="green" d="M73,50c0-12.7-10.3-23-23-23S27,37.3,27,50 M30.9,50c0-10.5,8.5-19.1,19.1-19.1S69.1,39.5,69.1,50">
		      <animateTransform 
		         attributeName="transform" 
		         attributeType="XML" 
		         type="rotate"
		         dur="1s" 
		         from="0 50 50"
		         to="360 50 50" 
		         repeatCount="indefinite" />
		  </path>
		</svg>
	</div>
	<script src="${root}/resources/js/noticeAjax.js"></script>
	<script>
		$(document).ready(function(){
			showntcList();
			});
	</script>
</body>

</html>