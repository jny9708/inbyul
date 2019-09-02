<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" ></script>
    <title>INBYUL</title>
</head>
    <script>
        $(document).ready(function(){
            var massage = ${massage};
            var htmls = '';
            if(massage > 0){
                htmls+='<h2>축하합니다.</h2>';
                htmls+='<h2>회원가입이 완료되었습니다.</h2>';                
                htmls+='이제 로그인을 할 수 있습니다.<br>';
                htmls+='로그인을 하여 INBYUL을 이용해보세요!<br>';
                htmls+='<a class="login" href="${pageContext.request.contextPath}/">로그인하기</a>';
                $("#confirm").html(htmls);
                
            }else{
                htmls+='<h2>ERROR</h2>';
                htmls+='<h2>이메일 인증에 실패 하였습니다.</h2>';                
                htmls+='로그인을 할 수 없습니다.<br>';
                htmls+='이메일 인증을 다시 해주세요.<br>';
                htmls+='<a class="login" href="${pageContext.request.contextPath}/">HOME</a>';
                $("#confirm").html(htmls);
            }

        });

    </script>

    <style>
        body{
        background-color: #e6e6e6;
    }
   
        #confirm {
            text-align: center;
            border: 1px solid white;
            background-color:white;

            position: absolute;
            margin: auto;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            height: 25%;
            width: 50%;
            padding-bottom:1%;
            overflow : hidden;
        }
        .login{
            position: absolute; 
            text-decoration : none;
            color:white;
            background-color: cornflowerblue;
            padding : 10px;
            margin-top: 23px;
            left: 50%;
            transform: translate(-55%, 0%);
        }

    </style>

<body>
    <div id="confirm">
        
       
    </div>


</body>
</html>