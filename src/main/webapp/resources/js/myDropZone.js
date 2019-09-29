   Dropzone.options.dropzoneFrom = { 
        autoProcessQueue: false,
        acceptedFiles:".png,.jpg,.gif,.bmp,.jpeg",
        uploadMultiple : true,
        parallelUploads: 100,
        paramName: "uploadFileArr",
        init: function(){
            var submitButton = document.querySelector('#submit-all');
            myDropZone = this;
            
            $(document).on("click",".editbutton",function(e){
                console.log(e.target.id);
                console.log(fileMap.get(e.target.id));
                addEditHtmls(myDropZone,null,e);
            });
            submitButton.addEventListener("click", function(){
                var newcount= myDropZone.files.length;
                if(mode=='write'){
                    if(newcount>0){
                        console.log("pro");
                        myDropZone.processQueue();
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
                        formData.append("bno", bno);
                        formData.append("bcontent", document.getElementById("autosize").value);
                        for(var i =0; i<rmvFileArr.length; i++){
                            formData.append("rmvFileArr["+ i +"].fno", rmvFileArr[i].fno);
                            formData.append("rmvFileArr["+ i +"].file_path", rmvFileArr[i].file_path);
                        }

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
                                     xhr.setRequestHeader(_csrf_name, _csrf_token);
                                     var wall = document.createElement('div');
                                     wall.style.width='100%';
                                     wall.style.height='100%';
                                     wall.style.zIndex=9999;
                                     wall.style.position='absolute';
                                     wall.style.top=0;
                                     wall.style.left=0;
                                     document.body.appendChild(wall);
                                     $('.m_container').css('opacity','0.5');
                                    $('#loading').css('display','block');
                                        }
                                 ,success : function(){
                                     location.href = root+'/home'
                                 }
                        })
                    }

                    if(orgcount > 0||newcount > 0){

                        console.log("m_pro");
                        myDropZone.processQueue();
                        
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
            	if(fileMap.get(file.name)==null){
            		fileMap.set(file.name,file);
            		 for (var _iterator6 = file.previewElement.querySelectorAll("[data-edmit-button]"), _isArray6 = true, _i6 = 0, _iterator6 = _isArray6 ? _iterator6 : _iterator6[Symbol.iterator]();;) {
                         var _ref5;

                         if (_isArray6) {
                             if (_i6 >= _iterator6.length) break;
                             _ref5 = _iterator6[_i6++];
                         } else {
                             _i6 = _iterator6.next();
                             if (_i6.done) break;
                             _ref5 = _i6.value;
                         }
                         var buttonElement = _ref5;
                         buttonElement.id = file.name;
                         }
                    addEditHtmls(myDropZone,file);
            	}else{
            		   myDropZone.removeFile(file);
                       Swal.fire({
                           title: 'FAIL',
                           html: '같은 이름의 파일이 이미 추가되어있습니다.',
                           type: 'error',
                           confirmButtonText: 'OK'
                       })
            	}
               
            });      

            this.on("sendingmultiple", function(file, xhr, formData){
                xhr.setRequestHeader(_csrf_name, _csrf_token);
                formData.append("bcontent", document.getElementById("autosize").value);
                formData.append("user.uno", document.getElementById("uno").value);
                formData.append("mode", mode);
               
                if(mode=='modify'){
                    for(var i =0; i<rmvFileArr.length; i++){
                    	formData.append("bno", bno);
                        formData.append("rmvFileArr["+ i +"].fno", rmvFileArr[i].fno);
                        formData.append("rmvFileArr["+ i +"].file_path", rmvFileArr[i].file_path);
                    }
                    console.log(formData.get("rmvFileArr"))
                }                    
            });

            this.on("processingmultiple", function(){
                
                $('.m_container').css('opacity','0.5');
                $('#loading').css('display','block');
                var wall = document.createElement('div');
                wall.style.width='100%';
                wall.style.height='100%';
                wall.style.zIndex=9999;
                wall.style.position='absolute';
                wall.style.top=0;
                wall.style.left=0;
                document.body.appendChild(wall);
              
            }); 

            this.on("completemultiple", function(){
            	location.href = root+'/home'
            });

            
            
        },
        transformFile: function(file, done) {
            done(canvasMap.get(file.name));
        },
         previewTemplate: document.querySelector('#preview').innerHTML
    };
   