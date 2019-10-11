<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal.username" var="username"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Pacifico&display=swap" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet">
    <script src="https://use.fontawesome.com/releases/v5.10.2/js/all.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    
    <title>INBYUL</title>
</head>
	<script>
	var socket = null;
	$(document).ready( function() {
	    connectWS();	
	});
	function connectWS() {
	    console.log("tttttttttttttt")
	    var ws = new WebSocket("ws://localhost:8080"+root+"/echo/websocket");
	    socket = ws;
	    ws.onopen = function () {
	        console.log('Info: connection opened.');
	    };
	    ws.onmessage = function (event) {
		    var json = JSON.parse(event.data);
		    var htmls='';
		    
		    if(json.cmd=='comment'){
	            htmls+='<div>';
	            htmls+= json.sender+'님이 '+ json.recipient +'님의 <a href="${pageContext.request.contextPath}/boardContent/'+ json.target +'">'+ json.target+'번</a> 게시글에 댓글을 남겼습니다.';
	            htmls+='</div>';
	            $(".notice").html(htmls);
	            $(".notice").addClass("alert alert-success");
	          	
			 }else if(json.cmd=='follow'){
				 htmls+='<div>';
		            htmls+= json.notice.sender+'님이 '+ json.notice.recipient +'님을 팔로우하였습니다.';
		            htmls+='</div>';
		            $(".notice").html(htmls);
		            $(".notice").addClass("alert alert-success");
			}else if(json.cmd=='like'){
				htmls+='<div>';
	            htmls+= json.sender+'님이 '+ json.recipient +'님의 <a href="${pageContext.request.contextPath}/boardContent/'+ json.target +'">'+ json.target+'번</a> 게시글을 좋아합니다.';
	            htmls+='</div>';
	            $(".notice").html(htmls);
	            $(".notice").addClass("alert alert-success");
			}
		
            setTimeout('alertremove()', 3000);
	    };
	    ws.onclose = function (event) { 
	        console.log('Info: connection closed.');
	        //setTimeout( function(){ connect(); }, 1000); // retry connection!!
	    };
	    ws.onerror = function (err) { console.log('Error:', err); };
	}

	function alertremove(){
        $(".notice div").remove()
        $(".notice").removeClass("alert alert-success");
    }
	</script>
<body>
	<header>
    <div class="f_bar">
        <div class="ff_bar">
        <div class="s_bar">
            <div class="t_bar">
                <div class="logo">
                    <a href="${pageContext.request.contextPath}/home" style="margin:0px;">Inbyul</a>
                </div>
            </div>
            <div class="t_bar">
                <input placeholder="검색"/>
            </div>
            <div class="t_bar">
                <a href="#">
                    <i class="far fa-compass fa-2x"></i>
                </a>
                <a href="notice">
                    <i class="far fa-heart fa-2x"></i>
                    <span></span>
                </a>
                <a href="${pageContext.request.contextPath}/${username}">
                    <i class="far fa-user fa-2x"></i>
                </a>
                
            </div>

        </div>
    </div>
    
    <div class="notice">

    </div>
    </div>
    </header>
</body>
</html>