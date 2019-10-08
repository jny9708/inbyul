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
	var following_no = myuno;
	deletefollow(follower_no,following_no);
});
$(document).on("click","#followtag",function(e){
	var follower_no_id = $(".m_container").find(".idfont").attr("id");
	var follower_no = follower_no_id.replace(/[^0-9]/g,'');
	var following_no = myuno;
	
	insertfollow(follower_no,following_no);
});

$(document).ready(function(){
	uid= $.trim($(".idfont").text());
	getPersonalBoard(uid);
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




function insertfollow(follower_no,following_no) {
	
	$.ajax({
		  url: root+"/follow/insert?follower_no="+follower_no+"&following_no="+following_no
			  , headers: headers
			  , type : 'POST'
			  , beforeSend : function(xhr){
          		xhr.setRequestHeader(_csrf_name, _csrf_token);
	            }
			  , success: function(){
				  $("#followtag").remove();
				    var htmls='';
				    htmls+='<a id ="follow_cctag" style="text-decoration: none; margin-left:20px;" data-toggle="modal" data-target="#followmodal" data-whatever="">';
				    htmls+='<button>';
				    htmls+='팔로잉';
				    htmls+='</button>';
				    htmls+='</a>';
				    $("#addbtn").html(htmls);
			  }
			  , error: function(request,status,error){
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					Swal.fire({
		                title: 'FAIL',
		                html: '실패',
		                type: 'error',
		                confirmButtonText: 'OK'
		                });
			  }
		  });
};

function deletefollow(follower_no,following_no){

	$.ajax({
		  url: root+"/follow/delete?follower_no="+follower_no+"&following_no="+following_no
			  , headers: headers
			  , type : 'POST'
		     
			  , beforeSend : function(xhr){
        		xhr.setRequestHeader(_csrf_name, _csrf_token);
	            }
			  , success: function(){
				  $("#follow_cctag").remove();
				    var htmls='';
				    htmls+='<a style="text-decoration: none; margin-left:20px;" id="followtag">';
				    htmls+='<button>';
				    htmls+='팔로우';
				    htmls+='</button>';
				    htmls+='</a>';
				    $('#followmodal').modal("hide");
				    $("#addbtn").html(htmls);
			  }
			  , error: function(request,status,error){
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					Swal.fire({
		                title: 'FAIL',
		                html: '실패',
		                type: 'error',
		                confirmButtonText: 'OK'
		                });
			  }
		  });
};



