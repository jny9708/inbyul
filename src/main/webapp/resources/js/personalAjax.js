	var headers = {"Content-Type" : "application/json"
		,"X-HTTP-Method-Override" : "POST"
	  };
	var uid; 

$(window).scroll(function(){   //스크롤이 최하단 으로 내려가면 리스트를 조회하고 page를 증가시킨다.
		     if($(window).scrollTop() >= $(document).height() - $(window).height()){
			     if(addbool==true){
			    	 getPersonalBoard(uid);
			    	 console.log("true");
				 }else{
					 console.log("fa");
				 }
			     
		     } 
		});   


$(window).on( 'resize', function () {
    $('.thumb').css("height",$('.thumb').css("width"));
}).resize(); 

	$(document).on("click","#follow_cc",function(e){
		var follower_no_id = $(".m_container").find(".idfont").attr("id");
		var follower_no = follower_no_id.replace(/[^0-9]/g,'');
		var type = "otherpage"; //로그인한 본인이 아닌 다른 사람의 페이지에서 그 사람을 팔로우 취소했을때
		deletefollow(follower_no,uno,type);
	});
	
	
	$(document).on("click","#followtag",function(e){
		var follower_no_id = $(".m_container").find(".idfont").attr("id");
		var follower_no = follower_no_id.replace(/[^0-9]/g,'');
		var type = "otherpage"; //로그인한 본인이 아닌 다른 사람의 페이지에서 그 사람을 팔로우 했을때
		insertfollow(follower_no,uno,type);
	});


function getPersonalBoard(uid){
	var paramData={"page":page,"perPageNum":9};
	
	$.ajax({
		  url: root+"/restboard/"+uid
			  , headers: headers
			  , type : 'POST'
		      , data : JSON.stringify(paramData)
			  , beforeSend : function(xhr){
            		xhr.setRequestHeader(_csrf_name, _csrf_token);
	            }
			  , success: function(result){
				 var htmls='';
				 
				 $.each(result,function(){
					 htmls+='<li class="item">';
					 htmls+='<a class="thumb" href="'+root+'/boardContent/'+this.bno+'">';
					 htmls+='<img class="image" src="'+root + this.file_path+'" width="100%" height="100%">';
					 htmls+='</a>';
					 htmls+='</li>';
				 });
	
				 if(page==1){
					 $(".channel_list").html(htmls);
					 page+=1;
				 }else{
					 if(result.length>0){
						 $(".channel_list").append(htmls);
						 page+=1;
					 }else{
						 addbool=false;
					 }
				 }
				  
			  }
			  , error: function(request,status,error){
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					Swal.fire({
		                title: 'FAIL',
		                html: '가져오기 실패',
		                type: 'error',
		                confirmButtonText: 'OK'
		                });
			  }
		  });	
}




function insertfollow(follower_no,following_no,type) {
	
	$.ajax({
		  url: root+"/follow/insert?follower_no="+follower_no+"&following_no="+following_no
			  , headers: headers
			  , type : 'POST'
			  , beforeSend : function(xhr){
          		xhr.setRequestHeader(_csrf_name, _csrf_token);
	            }
			  , success: function(){
				  if(type=="otherpage"){
					  $("#followtag").remove();
					    var htmls='';
					    htmls+='<a id ="follow_cctag" style="text-decoration: none; margin-left:20px;" data-toggle="modal" data-target="#followmodal" data-whatever="">';
					    htmls+='<button>';
					    htmls+='팔로잉';
					    htmls+='</button>';
					    htmls+='</a>';
					    $("#addbtn").html(htmls);
					    var cnt = ($("#followercnt").text()*1)+1;
					    $("#followercnt").text(cnt);
				  }
				  
			  }
			  , error: function(request,status,error){
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					if(type=="otherpage"){
						Swal.fire({
			                title: 'FAIL',
			                html: '실패',
			                type: 'error',
			                confirmButtonText: 'OK'
			                });
					}else{
						$(target).removeClass('follow_cc_btn');
					    $(target).addClass('follow_btn');
					    $(target).text("팔로우");
					}
			  }
		  });
};

function deletefollow(follower_no,following_no,type){

	$.ajax({
		  url: root+"/follow/delete?follower_no="+follower_no+"&following_no="+following_no
			  , headers: headers
			  , type : 'POST'
		     
			  , beforeSend : function(xhr){
        		xhr.setRequestHeader(_csrf_name, _csrf_token);
	            }
			  , success: function(){
				  if(type=="otherpage"){
					  $("#follow_cctag").remove();
					    var htmls='';
					    htmls+='<a style="text-decoration: none; margin-left:20px;" id="followtag">';
					    htmls+='<button>';
					    htmls+='팔로우';
					    htmls+='</button>';
					    htmls+='</a>';
					    $('#followmodal').modal("hide");
					    $("#addbtn").html(htmls); 
					    var cnt = ($("#followercnt").text()*1)-1;
					    $("#followercnt").text(cnt);
				  }
				  
			  }
			  , error: function(request,status,error){
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					if(type=="otherpage"){
					Swal.fire({
		                title: 'FAIL',
		                html: '실패',
		                type: 'error',
		                confirmButtonText: 'OK'
		                });
					}else{
						$(target).removeClass('follow_btn');
					    $(target).addClass('follow_cc_btn');
					    $(target).text("팔로잉");
					}
			  }
		  });
};


function getFollowerList(p_uno){
	
	$.ajax({
		  url: root+"/follow/getfollower?p_uno="+p_uno
			  , headers: headers
			  , type : 'POST'
			  , beforeSend : function(xhr){
      		xhr.setRequestHeader(_csrf_name, _csrf_token);
	            }
			  , success: function(result){
				  var htmls='';
				  htmls=makefollowlisthtml(result)
				  $("#followermodal .modal-body").html(htmls);
			  }
			  , error: function(request,status,error){
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					
			  }
		  });
}


function getFollowingList(p_uno){
	
	$.ajax({
		  url: root+"/follow/getfollowing?p_uno="+p_uno
			  , headers: headers
			  , type : 'POST'
			  , beforeSend : function(xhr){
      		xhr.setRequestHeader(_csrf_name, _csrf_token);
	            }
			  , success: function(result){
				  var htmls='';
				  htmls=makefollowlisthtml(result)
				  $("#followingmodal .modal-body").html(htmls);
			  }
			  , error: function(request,status,error){
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					
			  }
		  });
}

function makefollowlisthtml(result){
	var htmls='';
	  $.each(result,function(){
		  
	  htmls+='<div class="u-list-f">';
	  htmls+='<div class="u-list-s">';
	  htmls+='<a href="'+ root +'/user/'+this.uid +'">';
	  htmls+='<img src="'+root+'/resources/images/'+ this.uicon+'" style="width: 44px; height:44px;" >';
	  htmls+='</a>';      
	  htmls+='</div>';
	  htmls+='<div class="u-list-s">';
	  htmls+='<div>';
	  htmls+='<span style="font-weight: bold; color:#404040">'+ this.uid +'</span>';
	  htmls+='<span>'+ this.uname +'</span>';
	  htmls+='</div>';
	  htmls+='</div>';
	  htmls+='<div class="u-list-s">';
	  if(this.presence==0){
		  if(uno!=this.uno){
			  htmls+='<button class="btn follow_btn" id="number'+this.uno+'" >팔로우</button>';
		  }
	  }else{
		  htmls+='<button class="btn follow_cc_btn" id="number'+this.uno+'" >팔로잉</button>'; 
	  }
	  
	  htmls+='</div>';
	  htmls+='</div>';
	  });
	  return htmls;
}


