

var headers = {"Content-Type" : "application/json"
		,"X-HTTP-Method-Override" : "POST"
	  };

function showntcList(){
	
	var paramData={"page":page,"perPageNum":11};
	
$.ajax({
 		  url: root+"/restntc/"+uid
 			  , headers: headers
 			  , data : JSON.stringify(paramData)
 			  , type : 'POST'
 			  , beforeSend : function(xhr){
                		xhr.setRequestHeader(_csrf_name, _csrf_token);
 	            }
 			  , success: function(result){
 				 $("#loading").empty();
 				 var htmls='';
 				 $.each(result,function(){
 					 
 					
 					 if(this.cmd == 'comment'){
 						 
 						htmls+='<div class="ntc_f" id="ntc_no'+ this.ntc_no +'">';
 		 				htmls+='<div><a href="'+root+'/'+ this.sender +'">'+ this.sender +'님</a>이 '+ this.recipient +'님의 <a href="'+root+'/boardContent/'+ this.target +'">'+ this.target +'번</a> 게시글에 댓글을 남겼습니다. </div>';
 		 				htmls+='<div style="position: absolute;bottom: 5px;right: 5px;">'+ this.timeago +'</div>';
 		 				htmls+='</div>';
 					 }else if(this.cmd == 'follow'){ 
 						htmls+='<div class="ntc_f" id="ntc_no'+ this.ntc_no +'">';
 		 				htmls+='<div><a href="'+root+'/'+ this.sender +'">'+ this.sender +'님</a>이 '+ this.recipient +'님을 팔로우 하였습니다. </div>';
 		 				htmls+='<div style="position: absolute;bottom: 5px;right: 5px;">'+ this.timeago +'</div>';
 		 				htmls+='</div>';
 					 }else if(this.cmd == 'like'){
 						htmls+='<div class="ntc_f" id="ntc_no'+ this.ntc_no +'">';
 						htmls+='<div><a href="'+root+'/'+ this.sender +'">'+ this.sender +'님</a>이 '+ this.recipient +'님의 <a href="'+root+'/boardContent/'+ this.target +'">'+ this.target +'번</a> 게시글을 좋아합니다. </div>';
 		 				htmls+='<div style="position: absolute;bottom: 5px;right: 5px;">'+ this.timeago +'</div>';
 		 				htmls+='</div>';
 					 }
 				 });

	 				if(page==1){
	 					$("main").html(htmls);
	  		      		page+=1;	
	      		    }else{
	          		    if(result.length>0){
	          		    	$("main").append(htmls);
	          		    	page+=1;
	              		}
	          		    else{
							addbool=false;
	              		}
	          		}
 			    }

 			  , error: function(request,status,error){
 				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

 				}
 		  });

}


$(window).scroll(function(){   //스크롤이 최하단 으로 내려가면 리스트를 조회하고 page를 증가시킨다.
    if($(window).scrollTop() >= $(document).height() - $(window).height()){
	     if(addbool==true){
	    	 showntcList();
		 }
   	 
    } 
});



