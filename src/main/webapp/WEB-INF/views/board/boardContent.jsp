<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html lang="ko">
<c:set var="root" value="${pageContext.request.contextPath}" />
<sec:authentication property="principal.uno" var="uno"/>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="../layout/header.jsp"></jsp:include>
    <link href="${root}/resources/css/home.css" rel="stylesheet">
    <link href="${root}/resources/css/boardcontent.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
    <title>INBYUL</title>
</head>

<body>
			
	<section class="m_container">
        <main> 
            <section class="m_1" >
            	<div>
                	<article class="art_f">
                    	<header  style="border-bottom: 1px solid #e6e6e6;">
                        	<div class="hea_f">
                             	<div class="hea_s">
                                	<div class="hea_t">
                                    	<a href="#">
                                        	<img src="${root}/resources/images/${board.user.uicon}" width="32px" height="32px"></img>  
                                        </a>
                                     </div>
                                    <div class="hea_t">
                                    	<span>${board.user.uid}</span>
                                    </div>
                                    
                                    <div class="hea_t">
                                    
                                    	<c:if test="${board.user.uno eq uno}">
                                    		<a href="#"  data-toggle="modal" data-target="#ModalMore" data-whatever="bno${bno}">
                                    			<img src="https://img.icons8.com/material-outlined/24/000000/more.png" style="height: 24px;">
	                                    	</a>
                                    	</c:if>
                                    	<c:if test="${board.user.uno ne uno}">
                                    		<a href="#"  data-toggle="modal" data-target="#otherModalMore" data-whatever="bno${bno}">
                                    			<img src="https://img.icons8.com/material-outlined/24/000000/more.png" style="height: 24px;">
	                                    	</a>
                                    	</c:if>
                                    	
                                    </div>
                                    
                                </div>
                            </div>
                        </header> 
                        <!-- 사진이 여러장일때 캐러셀 -->
                        
                        <c:choose>
						    <c:when test="${fn:length(board.fileArr) > 1}">
								<div id="article" class="carousel slide carousel_f" data-ride="carousel" data-pause="hover" data-interval="false" data-wrap="false" >
                        		<div class="carousel-inner">
                        		<c:forEach var="image" items="${board.fileArr}" varStatus="status">
		                        	<c:choose>
									    <c:when test="${status eq 0}">
									    	<div class="carousel-item active">
			                                		<img class="content d-block w-100" src="${root}${image.file_path}" alt="...">
			                                </div>
									    </c:when>
									    <c:otherwise>
									        <div class="carousel-item">
			                                		<img class="content d-block w-100" src="${root}${image.file_path}" alt="...">
			                                </div>
									    </c:otherwise>
								    </c:choose>
                            	
								</c:forEach>
                            	</div>
                                    <a class="carousel-control-prev" href="#article${board.bno}" role="button" data-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <a class="carousel-control-next" href="#article${board.bno}" role="button" data-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </div>
                                <!-- 캐러셀 끝 -->
							</c:when>
							<c:otherwise>
								<!-- 사진이 한장일 때 -->
								<img class="content" src="${root}${board.fileArr[0].file_path}" alt="...">
							</c:otherwise>
						</c:choose>
                        
            			<div style="width: 100%;">
                                <div class="foot_f">
                                <section class="fun_f">
                                    <div class="fun_s">
                                        <a style="cursor:pointer;" id="likebtn${board.bno}">
                                        	<c:if test="${board.heart eq 1}">
                                                <i class="fas fa-heart" style="width: 29px; height:29px; color:red;"></i>
                                            </c:if>
                                            <c:if test="${board.heart eq 0}">
                                            	<i class="far fa-heart" style="width: 29px; height:29px;"></i>
                                            </c:if>
                                        </a>
                                        <a href="#">
                                                <i class="far fa-comment" style="width: 29px; height:29px;"></i>
                                        </a>
                                        <a href="#">
                                                <i class="far fa-share-square" style="width: 29px; height:29px;"></i>
                                        </a>
                                    </div>
                                </section>
                                <section class="fun_s">
                                    <span style="font-size:13px; font-weight: bold;">좋아요 <span id="likecnt">${board.likecnt}</span> 개</span>
                                </section>
                                <section>
                                    <div class="fun_s">
                                        <span style="font-weight: bold;">${board.user.uid}</span>
                                        <span>${board.bcontent}</span>
                                    </div>
                                </section>
                                 <section>  
                                    <div class="fun_s">
                                        <a href="#" class="comment_all"  data-toggle="modal" data-target="#commentmodal" data-whatever="bno${board.bno}">
                                            <span>댓글</span> <span style="font-weight: bold;" id="cmtcnt${board.bno}">${board.commentcnt}</span><span>개 모두 보기</span>
                                        </a>
                                    </div>
                                     <div class="fun_s" id="addcmtlayout${board.bno}">
                                        
                                    </div>
                                </section> 
                                <section>
                                    <div class="fun_s" style="font-size:9px;">
                                        <span style="color:#B0B0B0">${board.timestring}</span>
                                    </div>
                                </section>
                                </div>
                                <section class="comment_write">
                                        <div>
                                            <div>
                                                <form class="comment_f">
                                                    <textarea placeholder="댓글 달기" id="cmt_bno${board.bno}"></textarea>
                                                    
                                                    <button type="button" class="comment_sub c_sbm_btn"  id="sbm${board.bno}">게시</button>
                                                   
                                                </form>
                                            </div>
                                        </div>
                                    </section>
            
                            </div>
                            </article>
                            </div>
            </section>
        </main>
    </section>
	
	  <jsp:include page="../layout/modalLayout.jsp"></jsp:include>
    <script>
    	var root = '${root}';
    	var uno = '<sec:authentication property="principal.uno"/>';
    	var username = '<sec:authentication property="principal.username"/>';
    	var _csrf_name = "${_csrf.headerName}";
    	var _csrf_token = "${_csrf.token}";
    	var page = 1; 
    	var bno = "${board.bno}";
    </script>
    <script src="${root}/resources/js/homeModal.js"></script>
	<script src="${root}/resources/js/HomeAjax.js"></script>
	<script src="${root}/resources/js/cmtAjax.js"></script>
	<script src="${root}/resources/js/likeAjax.js"></script>
	
    
</body>
</html>