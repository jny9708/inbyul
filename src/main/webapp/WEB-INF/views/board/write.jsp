<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../layout/header.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/resources/css/write.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/dropzone.js"></script>
<title>Insert title here</title>
</head>
<body>
	
    <section class="m_container">
        <main class="m_container_f">
            <div class="write_container">
                <form action="#">
                <div class="write_f">
                    <div class="write_s">
                        
                            <img src="${pageContext.request.contextPath}/resources/images/<sec:authentication property="principal.uicon"/>"alt="..." class="pro_icon" >
                        
                    </div>
                    <div class="write_s">
                        <textarea name="write_content"  placeholder="무슨 일이 일어나고 있나요?" class="autosize" ></textarea>
                    </div>
                </div>
                <div class="write_f">
                    
                    <div  class="dropzone" id="dropzoneFrom">
                        <!-- <div class="fallback">
                            <input name="file" type="file" multiple />
                        </div> -->
                        <div class="message-wrap">
                        <div class="dz-default dz-message">
                            <span style="font-weight:bold;">여기에 사진을 놓거나 클릭하여 업로드하세요.</span>
                        </div>
                        </div>
                    </div>
                

                    <div id="preview" style="display:none;">
                            <div style="width:120px; height: 120px; display:inline-block; margin:10px;" class="pre">
                                    <div style="position:relative;">
                                        <img data-dz-thumbnail />
                                        <div data-dz-remove style="position:absolute; top:50%; left:50%; transform: translate( -50%, -50% ); "class="rmv" ><i class="far fa-trash-alt fa-2x"></i></div>
                                    </div>
                                    
                                </div>
                    </div>
                </div>
                
            </form>
            
        </div>
            <div class="btn-wrap">
                <button id="submit-all" class="btn">등록하기</button>
            </div>
        </main>
    </section>
</body>
<script>

    //var count=0;
        $("textarea.autosize").on('keydown keyup', function () {
          $(this).height(1).height( $(this).prop('scrollHeight')+12 );	
        });

        Dropzone.options.dropzoneFrom = {
            url:"/upload",
            autoProcessQueue: false,
            acceptedFiles:".png,.jpg,.gif,.bmp,.jpeg",
            
            init: function(){
                var submitButton = document.querySelector('#submit-all');
                myDropzone = this;
                submitButton.addEventListener("click", function(){
                    var count= myDropzone.files.length;
                    if(count>0){
                        myDropzone.processQueue();
                    }else{
                        Swal.fire({
                            title: 'FAIL',
                            html: '사진을 추가해주세요.',
                            type: 'error',
                            confirmButtonText: 'OK'
                        })	
                    }
                    
                });
                this.on("sending", function(file, xhr, formData){
                    formData.append("filename", file.name);
                    formData.append("write_content", $("#autosize").val);
                });
                    
            },
             previewTemplate: document.querySelector('#preview').innerHTML
        };

        $(document).ready(function(){

        });
    </script>

</html>