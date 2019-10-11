 $(document).on("click",".foot_f .fun_f .fun_s",function(e){
	 	var target =e.target.parentElement;
	   var targetid = e.target.parentElement.id;
	   if(targetid==''){
		   targetid = target.parentElement.id;
		   var child = e.target.parentElement;
	       var liketype = $(child).attr("data-prefix");
	   }else{
		   var child = e.target;
	       var liketype = $(child).attr("data-prefix");   
	   }
	   
	   var boardwriter = $(target).closest("article.art_f").find(".boardwriter").text()
       var type = targetid.replace(/([^A-Za-z]){0,}/g,'');
       var bno = targetid.replace(/[^0-9]/g,'');
       
       
		
		
       
       console.log(targetid);
       console.log(child);
       console.log(liketype);
               
        bno *=1;
        
       // data-prefix
       if(liketype=='far'){
    	   console.log(bno+"r");
    	   $(child).attr("data-prefix","fas");
			$(child).css("color","red");
			var parent = child.parentElement.parentElement.parentElement.parentElement;
			var likecnt = $(parent).find(".fun_s span #likecnt").text()*1;
			$(parent).find(".fun_s span #likecnt").text(likecnt+1);
			
    	   	insertlike(bno,child,boardwriter);
       }else if (liketype=='fas'){
    	   console.log(bno+"s");
    	   $(child).attr("data-prefix","far");
			$(child).css("color","");
			var parent = child.parentElement.parentElement.parentElement.parentElement;
			var likecnt = $(parent).find(".fun_s span #likecnt").text()*1;
			$(parent).find(".fun_s span #likecnt").text(likecnt-1);
    	   deletelike(bno,child);
       }
	 
});


function insertlike(bno,child,boardwriter){
	$.ajax({
		url: root+"/like/insert?bno="+bno
		,type:'POST'
		, beforeSend : function(xhr){
         		xhr.setRequestHeader(_csrf_name, _csrf_token);
          }
		, success: function(result){
			var socketData = JSON.stringify({"cmd":"like","sender":username,"recipient":boardwriter,"target" : bno});
			socket.send(socketData);
		  }
		, error: function(request,status,error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				$(child).attr("data-prefix","far");
				$(child).css("color","");
				}
	});
};

function deletelike(bno,child){
	$.ajax({
		url: root+"/like/delete?bno="+bno
		,type:'POST'
		, beforeSend : function(xhr){
         		xhr.setRequestHeader(_csrf_name, _csrf_token);
          }
		, success: function(){
			
		  }
		, error: function(request,status,error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				$(child).attr("data-prefix","fas");
				$(child).css("color","red");
				}
	});
}