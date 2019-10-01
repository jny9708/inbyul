

function insertcmt(paramData,type){
	 var headers = {"Content-Type" : "application/json"
			,"X-HTTP-Method-Override" : "POST"
		  };
	$.ajax({
 		  url: root+"/restcmt/"
 			  , headers: headers
 			  , data : JSON.stringify(paramData)
 			  , type : 'POST'
 			  , beforeSend : function(xhr){
                		xhr.setRequestHeader(_csrf_name, _csrf_token);
 	            }
 			  , success: function(result){
 				 var cmtcnt = $('#cmtcnt' + paramData.bno).text()*1
 				 $('#cmtcnt' + paramData.bno).text(cmtcnt+1);
 				 
 				 var htmls='';
    				htmls+='<div>';
    				htmls+='<span style="font-weight: bold;margin-right: 10px;">' + username + '</span>';
    				htmls+='<span>' + paramData.ccontent + '</span>';                                      
    				htmls+='</div>';
    	       		
    				
    				if(type=='cmt_bno'){
    					$("#addcmtlayout"+paramData.bno).html(htmls);
    					document.getElementById("cmt_bno"+paramData.bno).value='';
    				}else{
    					document.getElementById("m_cmt_bno"+paramData.bno).value='';
    				}
    				
 				 showcmtlist(paramData.bno);
 			    }

 			  , error: function(request,status,error){
 				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

 				}
 		  });
	
}

function showcmtlist(bno){
	 
	var headers = {"Content-Type" : "application/json"
			,"X-HTTP-Method-Override" : "GET"
		  };
	 
	$.ajax({
		  url: root+"/restcmt/"+bno
			  , headers: headers
			  , type : 'GET'
			  , beforeSend : function(xhr){
              		xhr.setRequestHeader(_csrf_name, _csrf_token);
	            }
			  , success: function(result){
				  var htmls = '';
				  $.each(result,function(){
				  htmls+='<div class="mymb-f">';
				  htmls+='<div class="mymb-s">';
				  htmls+='<a href="#">';
				  htmls+='<img src="' + root + '/resources/images/' + this.user.uicon+ '" width="32px" height="32px"></img>';  
				  htmls+='</a>';
				  htmls+='</div>';
				  htmls+='<div class="mymb-s">';
				  htmls+='<div class="mymb-t">';
				  htmls+='<div style="font-weight: bold;" id="personaluid">' + this.user.uid + '</div>';
				  if(this.user.uno == uno){
					  htmls+='<a href="#" onclick="deletecmt(' + this.cno + ','+  this.bno +')">';
					  htmls+='<i class="far fa-trash-alt"></i>';
					  htmls+='</a>';  
				  }
				  
				  htmls+='</div>';
				  htmls+='<div>' + this.ccontent + '</div>';
				  htmls+='</div>';
				  htmls+='</div>';
				  });
				  $("#m_addcmt").html(htmls);
			  }
			  , error: function(request,status,error){
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			  }
		  });
	
}

function deletecmt(cno,bno){
	uid = $("#personaluid").text();
	$.trim(uid) 
	console.log(uid);
	var headers = {"Content-Type" : "application/json"
		,"X-HTTP-Method-Override" : "DELETE"
	  };
	$.ajax({
		  url: root+"/restcmt/"+uid+"/"+bno+"/"+cno
			  , headers: headers
			  , type: 'DELETE'
			  
			  , beforeSend : function(xhr){
            		xhr.setRequestHeader(_csrf_name, _csrf_token);
	            }
			  , success: function(result){
				  showcmtlist(bno);
				  var cmtcnt = $('#cmtcnt' + bno).text()*1
	 				 $('#cmtcnt' + bno).text(cmtcnt-1);
				  
			  }
			  , error: function(request,status,error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				

				}
		  });
	
}