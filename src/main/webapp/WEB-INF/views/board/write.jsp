<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<jsp:include page="../layout/header.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/resources/css/write.css" rel="stylesheet">
<link href="https://unpkg.com/cropperjs/dist/cropper.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/resources/js/dropzone.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
<title>Insert title here</title>
</head>
<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="mode" value="write" />
<c:if test="${not empty board}">
	<c:set var="mode" value="modify" />
</c:if>

<c:url var="uploadURL" value="restboard/upload"/>
<body>
    <section class="m_container">
        <main class="m_container_f">
            <div class="write_container">
                
                <div class="write_f">
                    <div class="write_s">
                        
                            <img src="${pageContext.request.contextPath}/resources/images/<sec:authentication property="principal.uicon"/>"alt="..." class="pro_icon" >
                        
                    </div>
                    <div class="write_s">
                        <textarea name="bcontent" value="" placeholder="무슨 일이 일어나고 있나요?" id="autosize" class="autosize"></textarea>
                        <input type="hidden" value="<sec:authentication property="principal.uno"/>" name="uno" id="uno">
                        
                    </div>
                </div>
                <div class="write_f">
                    
                    <form  class="dropzone" id="dropzoneFrom" enctype="multipart/form-data" action=${uploadURL} >
                    	
                        <div class="message-wrap">
                        <div class="dz-default dz-message">
                            <span style="font-weight:bold;">여기에 사진을 놓거나 클릭하여 업로드하세요.</span>
                        </div>
                        </div>
                        
                         <c:if test="${mode eq 'modify'}">
                        	<c:forEach var="file" items="${board.fileArr}" varStatus="status"> 
	                        	<div id="fileno${file.fno}" style="display:inline-block;">
			                        <div style="width:120px; height: 120px; margin:10px;" class="pre" >
			                                <div style="position:relative;">
			                                    <img data-dz-thumbnail src="${root}${file.file_path}" style="width:120px; height: 120px;"/>
			                                    
			                                    <div data-dz-remove style="position:absolute; top:50%; left:50%; transform: translate( -50%, -50% ); "class="rmv" onclick="remove(${file.fno})" ><i class="far fa-trash-alt fa-2x"></i></div>
			                                </div>
			                                
			                        </div>
	                        	</div>
                        	</c:forEach>
                        </c:if>	
                        
                        
                    </form>
                

                    <div id="preview" style="display:none;">
                            <div style="width:120px; height: 120px; display:inline-block; margin:10px;" class="pre">
                                    <div style="position:relative;">
                                        <img data-dz-thumbnail />
                                        <div data-dz-remove style="position:absolute; top:50%; left:50%; transform: translate( -50%, -50% ); "class="rmv" ><i class="far fa-trash-alt fa-2x"></i></div>
                                    </div>
                                    
                                </div>
                    </div>
                </div>
                
            
            
        </div>
            <div class="btn-wrap">
            <c:choose>

				<c:when test="${mode eq 'write'}">
					<button id="submit-all" class="btn">등록하기</button>
				</c:when>
				<c:when test="${mode eq 'modify'}">
					<button id="submit-all" class="btn" style="margin-right:10px;">수정하기</button>
					<button id="mf_cc" class="btn">취소</button>
				</c:when>
			</c:choose>

            </div>
        </main>
    </section>
    
    <div id="loading" style="display:none;">
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
     <script src="https://unpkg.com/cropperjs"></script>
</body>
<script>
var mode = '${mode}';    
var root = '${root}';    

    var rmvFileArr = new Array(); //배열선언
    var canvasMap = new Map();
    
    $('#mf_cc').click(function(){    
        location.href='<c:url value="/home"/>';
    });

    if('modify'=='${mode}'){
        document.getElementsByName('bcontent').value = '${board.bcontent}';
         $("#autosize").val('${board.bcontent}');
    }

    function remove(fno){

         var fileObj = new Object();     
        var src = $("#fileno"+fno+" img").attr('src');
        	fileObj.fno=fno;
            fileObj.file_path=src;
            rmvFileArr.push(fileObj);
        
      
        console.log(rmvFileArr);
        $("#fileno"+fno).empty();
          
    }

    $("textarea.autosize").on('keydown keyup', function () {
      $(this).height(1).height( $(this).prop('scrollHeight')+12 );  
    });

    Dropzone.options.dropzoneFrom = { 
        autoProcessQueue: false,
        acceptedFiles:".png,.jpg,.gif,.bmp,.jpeg",
        uploadMultiple : true,
        parallelUploads: 100,
        paramName: "uploadFileArr",
        init: function(){
            var submitButton = document.querySelector('#submit-all');
            myDropzone = this;
            submitButton.addEventListener("click", function(){
                var newcount= myDropzone.files.length;
                if(mode=='write'){
                    if(newcount>0){
                        console.log("pro");
                        myDropzone.processQueue();
                    }else{
                        Swal.fire({
                            title: 'FAIL',
                            html: '사진을 추가해주세요.',
                            type: 'error',
                            confirmButtonText: 'OK'
                        })  
                    }
                }else{
                    
                    var orgcount = $('.pre').length - 1;
                    console.log("m_m");
                   
                    if(newcount<1||rmvFileArr.length<1){
                        var formData = new FormData();
                        formData.append("bcontent", document.getElementById("autosize").value);
                        for(var i =0; i<rmvFileArr.length; i++){
                            formData.append("rmvFileArr["+ i +"].fno", rmvFileArr[i].fno);
                            formData.append("rmvFileArr["+ i +"].file_path", rmvFileArr[i].file_path);
                        }
                      
                        /* var paramData = JSON.stringify({"bcontent":document.getElementById("autosize").value,

                                                        "filevo.rmvFileArr":rmvFileArr}); */

                        var headers = {"Content-Type" : "application/json"
                            ,"X-HTTP-Method-Override" : "POST"
                          };
                       
                        $.ajax({
                             url: root + "/restboard/update"
                                 ,contentType: false
                                 ,processData: false
                                 ,type : 'POST'
                                 ,data : formData
                                 ,dataType : 'json'
                                 ,beforeSend : function(xhr){
                                     xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
                                     $('.m_container').css('opacity','0.5');
                                    $('#loading').css('display','block');
                                        }
                                 ,success : function(){
                                     location.href='<c:url value="/home"/>'
                                 }
                        })
                    }

                    if(orgcount > 0||newcount > 0){

                        console.log("m_pro");
                        myDropzone.processQueue();
                        
                       }else{
                           Swal.fire({
                            title: 'FAIL',
                            html: '사진을 추가해주세요.',
                            type: 'error',
                            confirmButtonText: 'OK'
                        })
                    }
                }
            });
            this.on("addedfile", function(file){
                console.log("asd");
                var myDropZone = this;
                 // Create the image editor overlay
                var editor = document.createElement('div');
                editor.style.position = 'fixed';
                editor.style.left = 0;
                editor.style.right = 0;
                editor.style.top = 0;
                editor.style.bottom = 0;
                editor.style.zIndex = 9999;
                editor.style.backgroundColor = '#000';
                document.body.appendChild(editor);

                // Create confirm button at the top left of the viewport
                var buttonConfirm = document.createElement('button');
                buttonConfirm.style.position = 'absolute';
                buttonConfirm.style.left = '10px';
                buttonConfirm.style.top = '10px';
                buttonConfirm.style.zIndex = 9999;
                buttonConfirm.textContent = 'Confirm';
                editor.appendChild(buttonConfirm);
                buttonConfirm.addEventListener('click', function() {

                    // Get the canvas with image data from Cropper.js
                    var canvas = cropper.getCroppedCanvas({
                        width: 612,
                        height: 612
                    });
                    // Turn the canvas into a Blob (file object without a name)
                    canvas.toBlob(function(blob) {
                        // 새로운 Dropzone 파일 썸네일 
                        myDropZone.createThumbnail ( 
                            blob, 
                            myDropZone.options.thumbnailWidth,
                            myDropZone.options.thumbnailHeight, 
                            myDropZone.options.thumbnailMethod, 
                            false, 
                            function (dataURL) {
                            
                            // Dropzone 파일 썸네일 업데이트 
                            myDropZone.emit ( 'thumbnail', file, dataURL); 
                            // 파일을 Dropzone으로 반환 
                            blob.name=file.name;
                            canvasMap.set(file.name,blob);
                        
                        }); 
                    });

                    // Remove the editor from the view
                    document.body.removeChild(editor);
                });

                 // Create an image node for Cropper.js
                 var image = new Image();
                    image.src = URL.createObjectURL(file);
                    editor.appendChild(image);
                    
                    // Create Cropper.js
                    var cropper = new Cropper(image, { aspectRatio: 1 });
            });      

			this.on("removedfile", function(file){
				
				
            });

            this.on("sendingmultiple", function(file, xhr, formData){

                xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
                formData.append("bcontent", document.getElementById("autosize").value);
                formData.append("user.uno", document.getElementById("uno").value);
                formData.append("mode", mode);
               
                if(mode=='modify'){

                    for(var i =0; i<rmvFileArr.length; i++){
                        formData.append("rmvFileArr["+ i +"].fno", rmvFileArr[i].fno);
                        formData.append("rmvFileArr["+ i +"].file_path", rmvFileArr[i].file_path);
                    }

                    console.log(formData.get("rmvFileArr"))
                    //formData.append("rmvFileArr", rmvArr);
                }                    
            });

            this.on("processingmultiple", function(){
                
                $('.m_container').css('opacity','0.5');
                $('#loading').css('display','block');
              
            }); 

            this.on("completemultiple", function(){
                //location.href='<c:url value="/home"/>'
            });

            
            
        },
        transformFile: function(file, done) {

            done(canvasMap.get(file.name));
           
        },
         previewTemplate: document.querySelector('#preview').innerHTML
    };
    $(document).ready(function(){



    });
    </script>

</html>