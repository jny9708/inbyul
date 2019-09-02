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
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    
    <title>INBYUL</title>
</head>
<c:url var="uidCheckURL" value="/restuser/uidcheck"></c:url>
<c:url var="uemailCheckURL" value="/restuser/uemailcheck"></c:url>
<c:url var="userInsertURL" value="/user/insertuser"></c:url>
<script type="text/javascript">
	var b_uid=false;
	var b_upw=false;
	var b_uemail=false;
	var b_uname=false;
	var b_uphone=false;
	var headers = {"Content-Type" : "application/json; charset=UTF-8"
		,"X-HTTP-Method-Override" : "POST"};
	
	    $(document).ready(function(){
	        $("#uid").focusout(function(){
	            var uid=$("#uid").val();
	            var idcheck= /^[a-zA-Z0-9._]{1,12}$/;
	            var idcheck2= /^[0-9]{1,12}$/;
	
	            var htmls='';
	            var alert='';
	            
	            var paramData = {"uid":uid};
	           
	           
	            
	            if(!idcheck.test(uid)){
	                console.log("문자숫자마침표밑줄");
	                htmls='<i class="fas fa-exclamation-circle"></i>';
	                alert='사용자 이름에는 문자, 숫자, 밑줄 및 마침표만 사용할 수 있습니다.';
	                $("#uidalert").html(htmls);
	                $(".alert").html(alert);
	                b_uid=false;
	            }else{
	                if(idcheck2.test(uid)){
	                    console.log("숫자만하면안돼");
	                    htmls='<i class="fas fa-exclamation-circle"></i>';
	                    alert='사용자 이름에 숫자만 포함할 수는 없습니다.';
	                    $("#uidalert").html(htmls);
	                    $(".alert").text(alert);
	                    b_uid=false;
	                }else{
	                    console.log("완벽해");
	                   
	                    $.ajax({
	                    	url:"${uidCheckURL}"
	                    		,data : paramData
	                    		,type : 'POST'
	                    		,dataType : 'json'
		                    	,beforeSend : function(xhr){
		                    		xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
			                    	}
	                    		,success : function(result){
	                    			if(result>0){	// result가 1이면 중복이 있다는 뜻
	                    				console.log("중복되는 아이디");
	                                    htmls='<i class="fas fa-exclamation-circle"></i>';
	                                    alert='중복되는 사용자 이름입니다.';
	                                    $("#uidalert").html(htmls);
	                                    $(".alert").text(alert);
	                                    b_uid=false;
	                    			}else{
	                    				htmls='<i class="fas fa-check-circle"></i>';
	                                    $("#uidalert").html(htmls);
	                                    $(".alert").html('');
	                                    b_uid=true;
	                    			}
	                    		}
	                    })
	                    
	                    
	                }
	            }
	        });
	  
	        $("#uemail").focusout(function(){
	            var uemail = $("#uemail").val();
	            var emailcheck= /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	            var htmls='';
	            var alert='';
	            
	            var paramData = {"uemail" : uemail };
	            
	            if(!emailcheck.test(uemail)){
	                console.log("이메일");
	                htmls='<i class="fas fa-exclamation-circle"></i>';
	                alert='유효한 이메일주소가 아닙니다.';
	                $("#uemailalert").html(htmls);
	                $(".alert").html(alert);
	                b_uemail=false;
	            }else{
	            	
	            	  $.ajax({
	                  	url:"${uemailCheckURL}"
	                  		,data : paramData
	                  		,type : 'POST'
	                  		,dataType : 'json'
	                  		,beforeSend : function(xhr){
		                    	xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
			                 }	
	                  		,success : function(result){
	                  			if(result>0){	// result가 1이면 중복이 있다는 뜻
	                  				console.log("중복되는 이메일");
	                                  htmls='<i class="fas fa-exclamation-circle"></i>';
	                                  alert='중복되는 이메일 주소입니다.';
	                                  $("#uemailalert").html(htmls);
	                                  $(".alert").text(alert);
	                                  b_uemail=false;
	                  			}else{
	                  				htmls='<i class="fas fa-check-circle"></i>';
	                                $("#uemailalert").html(htmls);
	                                $(".alert").html('');
	                                b_uemail=true;
	                  			}
	                  		}
	                  })
	            	
	            	
	            	
	                
	            }
	        });
	
	        $("#uphone").focusout(function(){
	            var uphone=$("#uphone").val();
	            var phonecheck= /^01[0-9]{9}$/;
	            var htmls='';
	            var alert='';
	            if(!phonecheck.test(uphone)){
	                console.log("폰");
	                htmls='<i class="fas fa-exclamation-circle"></i>';
	                alert='유효한 전화번호가 아닙니다.';
	                $("#uphonealert").html(htmls);
	                $(".alert").html(alert);
	                b_uphone=false;
	            }else{
	                htmls='<i class="fas fa-check-circle"></i>';
	                    $("#uphonealert").html(htmls);
	                    $(".alert").html('');   
	                    b_uphone=true;
	            }
	        });
	
	        $("#uname").focusout(function(){
	            var uname=$("#uname").val();
	            var namecheck= /^[ㄱ-힣a-zA-Z]*$/;
	            var htmls='';
	            var alert='';
	            if(!namecheck.test(uname)){
	                console.log("이름");
	                htmls='<i class="fas fa-exclamation-circle"></i>';
	                alert='유효한 이름을 적어주세요';
	                $("#unamealert").html(htmls);
	                $(".alert").html(alert);
	                b_uname=false;
	            }else{
	                htmls='<i class="fas fa-check-circle"></i>';
	                    $("#unamealert").html(htmls);
	                    $(".alert").html('');   
	                    b_uname=true;
	            }
	        });
	
	        $("#upw").focusout(function(){
	            var upw=$("#upw").val();
	            var pwcheck= /^\S{6,}$/;
	            var htmls='';
	            var alert='';
	            if(!pwcheck.test(upw)){
	                console.log("비번");
	                htmls='<i class="fas fa-exclamation-circle"></i>';
	                alert='비밀번호는 6자이상이며 공백문자가 없어야합니다.';
	                $("#upwalert").html(htmls);
	                $(".alert").html(alert);
	                b_upw=false;
	            }else{
	                htmls='<i class="fas fa-check-circle"></i>';
	                    $("#upwalert").html(htmls);
	                    $(".alert").html('');   
	                    b_upw=true;
	            }
	        });

			$("form").submit(function(){
				console.log("asd");
				if(b_uid==true && b_upw==true && b_uemail==true && b_uname==true && b_uphone==true){
					return true;	
				}else{
					alert("회원가입 조건을 충족시켜주세요.")
					return false;
				}
				})
	        
	    });
</script>

<style>
    
    body{
        background-color: #e6e6e6;
    }
   
</style>

<body>
    <div class="s_ro">
        <div class="s_gr">
            <span><b>INBYUL</b></span>
            <form action="${userInsertURL}" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="ipst">
                    <div class="form-group">
                        <div class="icon">
                            <div id="uemailalert"></div>        
                            <input class="form-control" type="text" placeholder="이메일 주소" id="uemail" name="uemail">
                        </div>    
                    </div>
                    <div class="form-group">
                        <div class="icon">
                            <div id="uphonealert"></div>        
                        <input class="form-control" type="text" placeholder="휴대폰 번호(-없이)" id="uphone" name="uphone">
                        </div>
                    </div>
                    <div class="form-group">
                        <div  class="icon">
                            <div id="uidalert"></div>        
                            <input class="form-control" type="text" placeholder="사용자 이름" id="uid" name="uid">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="icon">
                            <div id="unamealert"></div>
                            <input class="form-control" type="text" placeholder="성명" id="uname" name="uname">    
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="icon">
                            <div id="upwalert"></div>
                            <input class="form-control" type="password" placeholder="비밀번호" id="upw" name="upw">
                        </div>
                    </div>
                    
                    <button type="submit" class="btn btn-primary btn-block" >회원가입</button>
                </div>
                
                <div class="alert" ></div>
            </form>
        </div>
        <div class="s_gr_1">
            계정이 있으신가요? <a href="#">로그인하기</a>   
        </div>
    </div>
</body>
</html>