<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>

</head>
<script>
var root ="${pageContext.request.contextPath}";
</script>
<body>
<h2 style="text-align:center;margin-top:120px;">에러가 발생하였습니다.</h2>
<h2 style="text-align:center;margin-top:100px;">홈으로 돌아가시려면 헤더의 INBYUL버튼을 클릭해주세요.</h2>

<a href="${pageContext.request.contextPath}/home"> 홈으로 가기 </a>
</body>
</html>