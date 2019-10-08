<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<c:set var="root" value="${pageContext.request.contextPath}" />
<sec:authentication property="principal.username" var="username"/>
<sec:authentication property="principal.uno" var="uno"/>
<head>
<meta charset="UTF-8">
<jsp:include page="../layout/header.jsp"></jsp:include>
<link href="${root}/resources/css/home.css" rel="stylesheet">
<link href="${root}/resources/css/personal.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
<title>INBYUL</title>
</head>
<script>
var root = '${pageContext.request.contextPath}';
var _csrf_name = "${_csrf.headerName}";
var _csrf_token = "${_csrf.token}";
var page  = 1; 
var addbool = true;
var uno = ${uno};
</script>
<body>
	 <section class="m_container">
            <main> 
               <header class="p-h-f">
                   <div class="p-h-s">
                        <img src="${root}/resources/images/${user.uicon}" width="150px" height="150px" style="border-radius:50%;"></img>  
                   </div>
                   <div class="p-h-s">
                        <div class="s-f">
                            <h1 class="idfont" id="number${user.uno}">
                                ${user.uid}
                            </h1>
                            <div class="s-s">
                                <div id="addbtn">
                                
                                <c:choose>
							         <c:when test = "${userauth}">
								         <a style="text-decoration: none; margin-left:20px;" href="#">
	                                        <button>
	                                            	프로필 편집
	                                        </button>
	                                     </a>
	                                     <a style="text-decoration: none; cursor:pointer; margin-left:10px;" data-toggle="modal" data-target="#settingmodal" data-whatever="">
    
                                        <svg width="24" height="24" xmlns="http://www.w3.org/2000/svg" fill-rule="evenodd" clip-rule="evenodd"><path d="M12 8.666c-1.838 0-3.333 1.496-3.333 3.334s1.495 3.333 3.333 3.333 3.333-1.495 3.333-3.333-1.495-3.334-3.333-3.334m0 7.667c-2.39 0-4.333-1.943-4.333-4.333s1.943-4.334 4.333-4.334 4.333 1.944 4.333 4.334c0 2.39-1.943 4.333-4.333 4.333m-1.193 6.667h2.386c.379-1.104.668-2.451 2.107-3.05 1.496-.617 2.666.196 3.635.672l1.686-1.688c-.508-1.047-1.266-2.199-.669-3.641.567-1.369 1.739-1.663 3.048-2.099v-2.388c-1.235-.421-2.471-.708-3.047-2.098-.572-1.38.057-2.395.669-3.643l-1.687-1.686c-1.117.547-2.221 1.257-3.642.668-1.374-.571-1.656-1.734-2.1-3.047h-2.386c-.424 1.231-.704 2.468-2.099 3.046-.365.153-.718.226-1.077.226-.843 0-1.539-.392-2.566-.893l-1.687 1.686c.574 1.175 1.251 2.237.669 3.643-.571 1.375-1.734 1.654-3.047 2.098v2.388c1.226.418 2.468.705 3.047 2.098.581 1.403-.075 2.432-.669 3.643l1.687 1.687c1.45-.725 2.355-1.204 3.642-.669 1.378.572 1.655 1.738 2.1 3.047m3.094 1h-3.803c-.681-1.918-.785-2.713-1.773-3.123-1.005-.419-1.731.132-3.466.952l-2.689-2.689c.873-1.837 1.367-2.465.953-3.465-.412-.991-1.192-1.087-3.123-1.773v-3.804c1.906-.678 2.712-.782 3.123-1.773.411-.991-.071-1.613-.953-3.466l2.689-2.688c1.741.828 2.466 1.365 3.465.953.992-.412 1.082-1.185 1.775-3.124h3.802c.682 1.918.788 2.714 1.774 3.123 1.001.416 1.709-.119 3.467-.952l2.687 2.688c-.878 1.847-1.361 2.477-.952 3.465.411.992 1.192 1.087 3.123 1.774v3.805c-1.906.677-2.713.782-3.124 1.773-.403.975.044 1.561.954 3.464l-2.688 2.689c-1.728-.82-2.467-1.37-3.456-.955-.988.41-1.08 1.146-1.785 3.126"/></svg>
                                    </a>
							         </c:when>
							         <c:otherwise>
							            <c:choose>
							            	<c:when test = "${followpresence eq 1}">
							        			<a id ="follow_cctag" style="text-decoration: none; margin-left:20px;" data-toggle="modal" data-target="#followmodal" data-whatever="">
			                                            <button>
			                                                	팔로잉
			                                            </button>
			                                    </a>    	
							            	</c:when>
							            	<c:when test = "${followpresence eq 0}">
							            		<a style="text-decoration: none; margin-left:20px;" id="followtag">
			                                            <button>
			                                                	팔로우
			                                            </button>
			                                    </a>
							            	</c:when>
							            </c:choose>
							         </c:otherwise>
							      </c:choose>
  
                                </div>


                                    
                            </div>
                        </div>
                        <ul class="s-f">
                                <li>
                                    <span>게시물</span> <span>${user.boardcnt}</span>  
                                </li>
                                <li>
                                	<a href="#" data-toggle="modal" data-target="#followermodal" data-whatever="">
                                    	<span>팔로워</span> <span id="followercnt">${user.followercnt}</span>  
                                    </a>
                                </li>
                                <li>
                                	<a href="#" data-toggle="modal" data-target="#followingmodal" data-whatever="">
                                    	<span>팔로우</span> <span id="followingcnt">${user.followingcnt}</span>
                                    </a>  
                                </li>
                            </ul>
                            <div>
                                <h1 class="rhpdm">
                                    ${user.uname}
                                </h1>
                                <br>
                                <span>
                                    
                                </span>
                            </div> 

                   </div>
               </header>
               
               <ul class="channel_list">
                  
            
                </ul>
            

            </main>
        </section>

        <div class="modal fade" id="followmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    
                 <div class="modal-body" style="padding:0px;">
                        <a href="#"  id="follow_cc" class="md_f">
                                팔로우 취소하기
                        </a>
                        <a href="#" class="md_f" data-dismiss="modal">
                                취소
                        </a>
                    </div>
                </div>
                </div>
            </div>

            <div class="modal fade" id="settingmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        
                     <div class="modal-body" style="padding:0px;">
                            <a href="${root}/logout"  id="follow_cc" class="md_f">
                                    	로그아웃 하기
                            </a>
                            <a href="#"  id="follow_cc" class="md_f">
                                    	알림
                            </a>
                            <a href="#" class="md_f" data-dismiss="modal">
                                    	취소
                            </a>
                        </div>
                    </div>
                    </div>
                </div>
                
                
                  <div class="modal fade" id="followermodal" tabindex="-1" role="dialog" aria-labelledby="followermodal" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable" role="document">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h5 class="modal-title" id="commentmodal">팔로워</h5>
                              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                              </button>
                            </div>
                            <div class="modal-body">
            
                                                             
                          </div>
                        </div>
                      </div>
                </div>

                <div class="modal fade" id="followingmodal" tabindex="-1" role="dialog" aria-labelledby="followingmodal" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable" role="document">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h5 class="modal-title" id="commentmodal">팔로우</h5>
                              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                              </button>
                            </div>
                            <div class="modal-body">
            
                                    
                          </div>
                        </div>
                      </div>
                </div>
                
                
                
	<script src="${root}/resources/js/personalAjax.js"></script>
	<script src="${root}/resources/js/followModal.js"></script>
	<script>
	var p_uno_id = $(".m_container").find(".idfont").attr("id");
	 var p_uno = p_uno_id.replace(/[^0-9]/g,'');
		$(document).ready(function(){
			uid= $.trim($(".idfont").text());
			getPersonalBoard(uid);
		});
	</script>

</body>
</html>