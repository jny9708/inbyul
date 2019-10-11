$(document).on("click",".c_sbm_btn",function(e){
	var target = e.target;
	
	 var targetid = target.id;
	 var type = targetid.replace(/([^A-Za-z]){0,}/g,'');
     var bno=targetid.replace(/[^0-9]/g,'');
    
     
    var boardwriter = $(target).closest("article.art_f").find(".boardwriter").text();
    console.log(boardwriter);
 
     bno *=1;
     uno *=1;
     if(type=='sbm'){
    	 var ccontent = document.getElementById("cmt_bno"+bno).value;
     }else{
    	 var ccontent = document.getElementById("m_cmt_bno"+bno).value;
     }
     
     var paramData =  {"ccontent":ccontent
    		 			,"bno":bno
    		 			,"user":{"uno":uno}};
     
     if(ccontent==''){
    	 Swal.fire({
             title: 'FAIL',
             html: "댓글을 입력해주세요.",
             type: 'error',
             confirmButtonText: 'OK'
             });
     }else{
    	 insertcmt(paramData,type,boardwriter);
     }
	  
});