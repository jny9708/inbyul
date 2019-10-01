$(document).on("click",".c_sbm_btn",function(e){
	 var targetid = e.target.id;
	 var type = targetid.replace(/([^A-Za-z]){0,}/g,'');
     var bno=targetid.replace(/[^0-9]/g,'');
     bno *=1;
     uno *=1;
     if(type=='cmt_bno'){
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
    	 insertcmt(paramData,type);
     }
	  
});