	    $('#otherModalMore').on('show.bs.modal', function (event) {
	        var button = $(event.relatedTarget) // Button that triggered the modal
	        var recipient = button.data('whatever') // Extract info from data-* attributes
	        var no=recipient.replace(/[^0-9]/g,'');

	        $("#m_redirect").attr("href", "#");
	        
	    });
	    
	      $('#ModalMore').on('show.bs.modal', function (event) {
	        var button = $(event.relatedTarget) // Button that triggered the modal
	        var recipient = button.data('whatever') // Extract info from data-* attributes
	        var no=recipient.replace(/[^0-9]/g,'');

	        $("#m_modify").attr("href", root + "/modifyboard/" + no)
	        $("#m_delete").attr("href", root + "/deleteboard/"+ no)
	        
	    });
	    
	      $('#commentmodal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget) // Button that triggered the modal
            var recipient = button.data('whatever') // Extract info from data-* attributes
            var no=recipient.replace(/[^0-9]/g,'');
            $(".cm_sbm_btn").attr("id","bno"+no);
            $("#addcmtlayout"+no).empty();
            showcmtlist(no);
            
        }); 
	      
	      $(document).on("click",".plusbtn a",function(e){
	            $(".plusbtn a").css("display","none");
	            $("#L9").css("display","block");
	            showcmtlist(bno);
	        });
	      
	      $(document).on("click",".close",function(e){
	            page=1;
	            $(".plusbtn a").css("display","block");
	        });
	      
	      