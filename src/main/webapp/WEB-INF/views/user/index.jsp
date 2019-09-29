<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
    <title>INBYUL</title>
</head>


<script>
  
  $(document).ready(function(){

	  var errormsg = '${errormsg}';
		if(errormsg != ''){ 
			console.log("asd?");
				Swal.fire({
	                title: 'FAIL',
	                html: errormsg,
	                type: 'error',
	                confirmButtonText: 'OK'
	                });
		}

		setTimeout(function() {
		    $('.tracking-in-expand').css("display","none");
		      $('.hero').css("display","block");
		      $('.ro').css("display","block");
		      
		      
		  }, 1500);
    


	
    var message = ${message};
    var origin = '${origin}';

    if(origin == 'emailconfirm'){
  		if(message > 0){
  			Swal.fire({
                title: 'SUCCESS',
                html: '회원가입이 완료되었습니다.<br>로그인을 하여 INBYUL을 이용해보세요!',
                type: 'success',
                confirmButtonText: 'OK'
                })
  		}else{
  			Swal.fire({
                title: 'FAIL',
                html: '이메일 인증에 실패하였습니다.<br>이메일 인증을 다시 해주세요.',
                type: 'error',
                confirmButtonText: 'OK'
                })	
  		}
            
    }else if(origin == 'insertuser'){
    	if(message > 0){
    		Swal.fire({
                title: 'SUCCESS',
                html: '인증메일이 발송되었습니다.<br>이메일 인증을 하여 회원가입을 완료해주세요.',
                type: 'success',
                confirmButtonText: 'OK'
                })
  		}else{
  			Swal.fire({
                title: 'FAIL',
                html: '회원가입에 실패했습니다.',
                type: 'error',
                confirmButtonText: 'OK'
                })
  		}	
    }
  	

    

});


  
  

</script>

<body>

  <span class="tracking-in-expand">
    <b>INBYUL</b>
  </span> 
  <div class="hero">
    
      <div class="ro">
        <div class="gr">
          <span><b>INBYUL</b></span>
          <form action="<c:url value='/login'/>" method="post">
           <input type ="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <div class="ipst">
                    <input class="form-control" type="text" placeholder="사용자아이디 혹은 이메일" name="loginid">
                  </div>
            </div>
            <div class="form-group">
                <div class="ipst">
                    <input class="form-control" type="password" placeholder="비밀번호" name="password">
                  </div>
            </div>
            <div class="ipst">
              <button class="btn btn-primary btn-block">로그인</button>
            </div>
          </form>
        
	
        </div>
        
        <div class="gr_1">
          계정이 없으신가요? <a href="${pageContext.request.contextPath}/user/signup">가입하기</a>
        </div>
      </div>
    
  </div>
</body>
</html>