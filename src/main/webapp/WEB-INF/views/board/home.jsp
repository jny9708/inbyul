<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="../layout/header.jsp"></jsp:include>
    <link href="${root}/resources/css/home.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
    <title>INBYUL</title>
</head>

<c:url var="getReListURL" value="/restboard/getReList"></c:url>

<c:url var="writeFormURL" value="/writeform"></c:url>
<c:url var="getBorListURL" value="/restboard/getBorList"></c:url>
<style>

</style>

<script>
	var root = '${pageContext.request.contextPath}';
	var windowWidth=window.innerWidth;;
	var uno = '<sec:authentication property="principal.uno"/>';
	var username = '<sec:authentication property="principal.username"/>';
	var _csrf_name = "${_csrf.headerName}";
	var _csrf_token = "${_csrf.token}";
	var page  = 1; 
	var addbool = true;
	var p_uno;
	
	$(document).ready(function(){
	    var isRecomendUser = ${isRecomendUser};
	    var htmls= '';
	    if(isRecomendUser){
	    	showfirst();
	    }else{
	    	showBorList();
	    }

	    function showfirst(){
	   	 $.ajax({
          	url:"${getReListURL}"
          		,type : 'POST'
          		,dataType : 'json'
              	,beforeSend : function(xhr){
              		xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
	                    	}
          		,success : function(result){
          			  htmls+='<div class="art_f">';
          		        htmls+='<div class="n-f">';
          		        htmls+='<div class="n-s">';
          		        htmls+='<i class="fas fa-home fa-4x"></i>';
          		        htmls+='</div>';
          		        htmls+='<div class="n-s">';
          		        htmls+='<div><span>Inbyul에 오신 것을 환영합니다.</span></div>';
          		        htmls+='</div>';
          		        htmls+='<div class="n-s">';
          		        htmls+='<div><span>다른 사람을 팔로우하면 피드에서 상대방의 사진을 볼 수 있습니다.</span></div>';
          		        htmls+='</div>';
          		        htmls+='</div>';
          		        htmls+='<div class="u-list-wrap">';
          		        
          		       $.each(result,function(){
          		            htmls+='<div class="u-list-f">';
          		            htmls+='<div class="u-list-s">';
          		           	htmls+='<a href="'+ root+'/'+ this.uid +'">'		            
          		            htmls+='<img src="${pageContext.request.contextPath}/resources/images/' + this.uicon + '" style="width: 44px; height:44px;" >';
          		           	htmls+='</a>';
          		            htmls+='</div>';
          		            htmls+='<div class="u-list-s">';
          		            htmls+='<div>';
          		            htmls+='<span style="font-weight: bold; color:#404040">' + this.uid + '</span>';
          		            htmls+='<span>' + this.uname + '</span>';
          		            htmls+='</div>';
          		            htmls+='</div>';
          		            htmls+='<div class="u-list-s">';
          		            if(this.presence==0){
          		            	htmls+='<button class="btn follow_btn" id="number'+this.uno+'">팔로우</button>';
              		        }else{
              		        	htmls+='<button class="btn follow_cc_btn" id="number'+this.uno+'">팔로잉</button>';
                  		    }
          		            
          		            htmls+='</div>';
          		            htmls+='</div>';
          		       })
          		       
          		        htmls+='</div>';
          		        htmls+='</div>';
          		            
          		        $("#loading").empty();
          		       	removem_2();
          		        $("#add_html").html(htmls);
          			}
          		
          })  		
		}
	    
		function showBorList(){
			var paramData={"page":page};
			var headers = {"Content-Type" : "application/json"
				,"X-HTTP-Method-Override" : "POST"
			  };
	    	   $.ajax({
          	url:"${getBorListURL}"
          		,type : 'POST'
          	    , headers: headers
          		, data : JSON.stringify(paramData)
          		,dataType : 'json'
              	,beforeSend : function(xhr){
              		xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
	                    	}
          		,success : function(result){
              		console.log(result);
              		if(result.length==0&&page==1){
              			showfirst();
                  		}
          			htmls+='<div>';

          			$.each(result,function(){
          			htmls+='<article class="art_f">';
          			htmls+='<header  style="border-bottom: 1px solid #e6e6e6;">';
          			htmls+='<div class="hea_f">';	  
          			htmls+='<div class="hea_s">';                              
          			htmls+='<div class="hea_t">';
          			htmls+='<a href="'+ root+'/'+ this.user.uid +'">';
          			htmls+='<img src="${pageContext.request.contextPath}/resources/images/' + this.user.uicon + '" width="32px" height="32px"></img>';
          			htmls+='</a>';
          			htmls+='</div>';
          			htmls+='<div class="hea_t">';
          			htmls+='<span class="boardwriter">' + this.user.uid + '</span>';
          			htmls+='</div>';
						
          			htmls+='<div class="hea_t">';
          			//여기서부터 변경
          			htmls+='<a href="#"  data-toggle="modal" data-target="';
					if(this.user.uno==uno){
						htmls+='#ModalMore"'
					}else{
						htmls+='#otherModalMore"'
					}
					htmls+='data-whatever="bno' + this.bno + '">';	
					// 여기까지 변경
          			htmls+='<img src="https://img.icons8.com/material-outlined/24/000000/more.png" style="height: 24px;">';
          			htmls+='</a>';
          			htmls+='</div>';
          			
          			htmls+='</div>';
          			htmls+='</div>';
          			htmls+='</header>';

          			if(this.fileArr.length>1){
              			<!-- 사진이 여러장일때 캐러셀 -->
          				htmls+='<div id="article'+ this.bno +'" class="carousel slide carousel_f" data-ride="carousel" data-pause="hover" data-interval="false" data-wrap="false" >';							
          				htmls+='<div class="carousel-inner">';
          				
							$.each(this.fileArr,function(i){
								
								if(i==0){
								
									htmls+='<div class="carousel-item active">';
									htmls+='<img class="content d-block w-100" src="${pageContext.request.contextPath}'+ this.file_path + '"  alt="...">';
									htmls+='</div>';	
								}else{
								
									htmls+='<div class="carousel-item">';
									htmls+='<img src="${pageContext.request.contextPath}' + this.file_path + '" class="d-block w-100" alt="...">';
									/* 갑자기 사진이 이상하게 뜨면 class에 content추가해보삼 */
									htmls+='</div>';
								}
							})
							htmls+='</div>';
							htmls+='<a class="carousel-control-prev" href="#article'+ this.bno +'" role="button" data-slide="prev">';
							htmls+='<span class="carousel-control-prev-icon" aria-hidden="true"></span>';
							htmls+='<span class="sr-only">Previous</span>';
							htmls+='</a>';
							htmls+='<a class="carousel-control-next" href="#article'+ this.bno +'" role="button" data-slide="next">';
							htmls+='<span class="carousel-control-next-icon" aria-hidden="true"></span>';
							htmls+='<span class="sr-only">Next</span>';
							htmls+='</a>';
							htmls+='</div>';
							<!-- 캐러셀 끝 -->
              		}else{
              			<!-- 사진이 한장일 때 -->
              			htmls+='<img class="content" src="'+ root + this.fileArr[0].file_path + '">';
                  	}

          			htmls+='<div style="width: 100%;">';
          			htmls+='<div class="foot_f">';                 
          			htmls+='<section class="fun_f">';
          			htmls+='<div class="fun_s">';
          			htmls+='<a style="cursor:pointer;" id="likebtn' + this.bno + '">';
          			if(this.heart==1){
          				htmls+='<i class="fas fa-heart" style="width: 29px; height:29px; color:red;"></i>';
              		}else{
              			htmls+='<i class="far fa-heart" style="width: 29px; height:29px; "></i>';
                  	}
          			
          			htmls+='</a>'
          			htmls+='<a href="' + root + '/boardContent/' + this.bno + '">'
          			htmls+='<i class="far fa-comment" style="width: 29px; height:29px;"></i>'
          			htmls+='</a>';
          			htmls+='<a href="#">';
          			htmls+='<i class="far fa-share-square" style="width: 29px; height:29px;"></i>';
          			htmls+='</a>';
          			htmls+='</div>';
          			htmls+='</section>';
          			htmls+='<section class="fun_s">';
          			htmls+='<span style="font-size:13px; font-weight: bold;">좋아요 ';
          			htmls+='<span id="likecnt">'+ this.likecnt +'</span>';
          			htmls+=' 개</span>';
          			htmls+='</section>';
          			htmls+='<section>';
          			htmls+='<div class="fun_s">';
          			htmls+='<span style="font-weight: bold;margin-right: 10px;">' + this.user.uid + '</span>';
          			htmls+='<span>' + this.bcontent + '</span>';
          			htmls+='</div>';
          			htmls+='</section>';
          			htmls+='<section>';
          			htmls+='<div class="fun_s">';
          			htmls+='<a href="' + root + '/boardContent/' + this.bno + '" class="comment_all">';
          			htmls+='<span>댓글</span> <span style="font-weight: bold;" id="cmtcnt' + this.bno + '">' + this.commentcnt + '</span><span>개 모두 보기</span>';
          			htmls+='</a>';
          			htmls+='</div>';
          			htmls+='<div class="fun_s" id="addcmtlayout' + this.bno + '">';		/* 커맨트관련 여기서부터 시작 */
          			
          			
          			htmls+='</div>';
          			
          			htmls+='</section>';
          			htmls+='<section>';
          			htmls+='<div class="fun_s" style="font-size:9px;">';
          			htmls+='<span style="color:#B0B0B0">' + this.timestring + '</span>';
          			htmls+='</div>';
          			htmls+='</section>';
          			htmls+='</div>';
          			htmls+='<section class="comment_write">';
          			htmls+='<div>';
          			htmls+='<div>';
          			
          			/* 댓글달기부분 */
          			htmls+='<form class="comment_f">';
          			htmls+='<textarea placeholder="댓글 달기" id="cmt_bno' + this.bno + '"></textarea>';
          			htmls+='<button type="button" class="comment_sub c_sbm_btn" id="sbm' + this.bno + '" >게시</button>';
          			htmls+='</form>';
          			htmls+='</div>';
          			htmls+='</div>';
          			htmls+='</section>';
          			htmls+='</div>';
          			htmls+='</article>';
								
          			 })
                     htmls+='</div>';  

          			$("#loading").empty();
      		      	removem_2();
      		      	if(page==1){
      		      		$("#add_html").html(htmls);
      		      		page+=1;	
          		    }else{
              		    if(result.length>0){
              		    	$("#add_html").append(htmls);
              		    	page+=1;
                  		}
              		    else{
							addbool=false;
                  		}
          		    	
              		}

      		       	
      		       	
	    			}

	    	   });
		}  


		$(window).scroll(function(){   //스크롤이 최하단 으로 내려가면 리스트를 조회하고 page를 증가시킨다.
		     if($(window).scrollTop() >= $(document).height() - $(window).height()){
			     if(addbool==true){
			    	 showBorList();
				 }
		    	 
		     } 
		});
	
	    
	    $( window ).resize(function() {
	    	   //창크기 변화 감지
	    	 windowWidth = window.innerWidth;   
				if(windowWidth < 1000) {
					$(".m_2").attr('style','');
				}
	    	});
	  
	});
	 function removem_2(){
	    	if(windowWidth < 1000) {
		    	var el = document.getElementById('m_2');
		    	el.removeAttribute('style');
			}else{
				$(".m_2").css("display","block");
				}
		    } 
</script>
<body>
     

    <section class="m_container">
        <main> 
            <section class="m_1" id="add_html" >
            
            </section>
                
            <aside class="m_1">
                <div class="m_2" style="display:none;" id="m_2">   
                    <div class="info_f">
                        <div class="info_s">
                        	<a href="${root}/<sec:authentication property="principal.username"/>">
                            <img src="${pageContext.request.contextPath}/resources/images/<sec:authentication property="principal.uicon"/>" style="width: 50px; height:50px;" >
                            </a>
                        </div>
                        <div class="info_s">
                            <div class="info_t">
                                <div>
                                    <span><sec:authentication property="principal.username"/></span>
                                </div>
                                <div>
                                    <span style="font-size:13px; color:#b0b0b0"><sec:authentication property="principal.uname"/></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <a href="${writeFormURL}" style="text-decoration: none;">
                    <button type="button" class="btn btn-block" style="background-color:#3897f0;color:white;" >
                        글쓰기
                    </button>
                    </a>
                </div>
            </aside>
            


        </main>
    
    </section>

    <div class="fixed-write">
            <a href="write.html" style="text-decoration: none; cursor:pointer;"><i class="fas fa-plus-circle fa-4x"></i></a>
    </div>
    
    <jsp:include page="../layout/modalLayout.jsp"></jsp:include>
  
	
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
	
	
	<script src="${root}/resources/js/homeModal.js"></script>
	<script src="${root}/resources/js/HomeAjax.js"></script>
	<script src="${root}/resources/js/cmtAjax.js"></script>
	<script src="${root}/resources/js/likeAjax.js"></script>
	<script src="${root}/resources/js/personalAjax.js"></script>
	<script src="${root}/resources/js/followModal.js"></script>
</body>
</html>