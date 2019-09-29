function addEditHtmls(myDropZone,file,e){

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
            buttonConfirm.classList.add("btn");
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
                        if(e!=null){
                            myDropZone.emit ( 'thumbnail', fileMap.get(e.target.id), dataURL);
                            blob.name=fileMap.get(e.target.id).name;
                            canvasMap.set(blob.name,blob);
                            console.log(blob.name);
                        }else{
                            myDropZone.emit ( 'thumbnail', file, dataURL);
                            blob.name=file.name;
                            canvasMap.set(blob.name,blob);
                            console.log(blob.name);
                        }
                        
                        
                        
                    }); 
                });

                // Remove the editor from the view
                document.body.removeChild(editor);
            });
                var  aspectRatio1= document.createElement('button');
                aspectRatio1.style.position = 'absolute';
                aspectRatio1.style.width='65px'
                aspectRatio1.style.right = '10px';
                aspectRatio1.style.top = '10px';
                aspectRatio1.style.zIndex = 9999;
                aspectRatio1.textContent = '1 : 1';
                aspectRatio1.classList.add("btn");
                editor.appendChild(aspectRatio1);
                // Create Cropper.js
                aspectRatio1.addEventListener('click', function() {
                    cropper.destroy();
                    cropper = new Cropper(image, { aspectRatio: 1,viewMode:2 });
                });
                var  aspectRatio2= document.createElement('button');
                aspectRatio2.style.width='65px'
                aspectRatio2.style.position = 'absolute';
                aspectRatio2.style.right = '10px';
                aspectRatio2.style.top = '55px';
                aspectRatio2.style.zIndex = 9999;
                aspectRatio2.textContent = '4 : 3';
                aspectRatio2.classList.add("btn");
                editor.appendChild(aspectRatio2);
                // Create Cropper.js
                aspectRatio2.addEventListener('click', function() {
                    cropper.destroy();
                    cropper = new Cropper(image, { aspectRatio: 4/3,viewMode:2 });
                });
                var  aspectRatio3= document.createElement('button');
                aspectRatio3.style.width='65px'
                aspectRatio3.style.position = 'absolute';
                aspectRatio3.style.right = '10px';
                aspectRatio3.style.top = '100px';
                aspectRatio3.style.zIndex = 9999;
                aspectRatio3.textContent = '16 : 9';
                aspectRatio3.classList.add("btn");
                editor.appendChild(aspectRatio3);
                // Create Cropper.js
                aspectRatio3.addEventListener('click', function() {
                    cropper.destroy();
                    cropper = new Cropper(image, { aspectRatio: 16/9,viewMode:2 });
                });
                         // Create an image node for Cropper.js
             var image = new Image();
             if(e!=null){
                image.src = URL.createObjectURL(fileMap.get(e.target.id));
             }else{
                image.src = URL.createObjectURL(file);
             }
             
             editor.appendChild(image);
             
             var cropper = new Cropper(image, { aspectRatio: 1,viewMode:2 });
}