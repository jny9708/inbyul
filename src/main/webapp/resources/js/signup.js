var b_uid=false;
var b_upw=false;
var b_uemail=false;
var b_uname=false;
var b_uphone=false;
var headers = {"Content-Type" : "application/json"
	,"X-HTTP-Method-Override" : "POST"};

    $(document).ready(function(){
        $("#uid").focusout(function(){
            var uid=$("#uid").val();
            var idcheck= /^[a-zA-Z0-9._]{1,12}$/;
            var idcheck2= /^[0-9]{1,12}$/;

            var htmls='';
            var alert='';
            
            var paramData = JSON.stringify({"uid":uid});
           
           
            
            if(!idcheck.test(uid)){
                console.log("문자숫자마침표밑줄");
                htmls='<i class="fas fa-exclamation-circle"></i>';
                alert='사용자 이름에는 문자, 숫자, 밑줄 및 마침표만 사용할 수 있습니다.';
                $("#uidalert").html(htmls);
                $(".alert").html(alert);

            }else{
                if(idcheck2.test(uid)){
                    console.log("숫자만하면안돼");
                    htmls='<i class="fas fa-exclamation-circle"></i>';
                    alert='사용자 이름에 숫자만 포함할 수는 없습니다.';
                    $("#uidalert").html(htmls);
                    $(".alert").text(alert);
                    
                }else{
                    console.log("완벽해");
                   
                    $.ajax({
                    	url:"/restuser/uidcheck"
                    		,headers : headers
                    		,data : paramData
                    		,type : 'POST'
                    		,dataType : 'json'
                    		,success : function(result){
                    			if(result>0){	// result가 1이면 중복이 있다는 뜻
                    				console.log("중복되는 아이디");
                                    htmls='<i class="fas fa-exclamation-circle"></i>';
                                    alert='중복되는 사용자 이름입니다.';
                                    $("#uidalert").html(htmls);
                                    $(".alert").text(alert);
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
            var uemail=$("#uemail").val();
            var emailcheck= /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
            var htmls='';
            var alert='';
            
            var paramData = JSON.stringify({"uemail":uemail});
            
            
            
            if(!emailcheck.test(uemail)){
                console.log("이메일");
                htmls='<i class="fas fa-exclamation-circle"></i>';
                alert='유효한 이메일주소가 아닙니다.';
                $("#uemailalert").html(htmls);
                $(".alert").html(alert);
            }else{
            	
            	  $.ajax({
                  	url:"/restuser/uemailcheck"
                  		,headers : headers
                  		,data : paramData
                  		,type : 'POST'
                  		,dataType : 'json'
                  		,success : function(result){
                  			if(result>0){	// result가 1이면 중복이 있다는 뜻
                  				console.log("중복되는 이메일");
                                  htmls='<i class="fas fa-exclamation-circle"></i>';
                                  alert='중복되는 이메일 주소입니다.';
                                  $("#uemailalert").html(htmls);
                                  $(".alert").text(alert);
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
            }else{
                htmls='<i class="fas fa-check-circle"></i>';
                    $("#upwalert").html(htmls);
                    $(".alert").html('');   
                    b_upw=true;
            }
        });
        
    });