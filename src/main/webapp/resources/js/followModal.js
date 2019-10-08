

$('#followingmodal').on('show.bs.modal', function (event) {
            getFollowingList(p_uno)
            
        });
  
  $('#followermodal').on('show.bs.modal', function (event) {
	  console.log("follower");
	  getFollowerList(p_uno);
  }); 
  
  $(document).on("click",".follow_btn",function(e){
	    var target  = e.target;
	    var targetid = target.id;
	    var no = targetid.replace(/[^0-9]/g,'');
	    console.log(no);
	    $(target).removeClass('follow_btn');
	    $(target).addClass('follow_cc_btn');
	    $(target).text("팔로잉");
	    insertfollow(no,uno);
	    if(uno==p_uno){
	        var cnt = ($("#followingcnt").text()*1)+1
	        $("#followingcnt").text(cnt);
	    }
	});

	$(document).on("click",".follow_cc_btn",function(e){
	    var target  = e.target;
	    var targetid = target.id;
	    var no = targetid.replace(/[^0-9]/g,'');
	    console.log(no);
	    $(target).removeClass('follow_cc_btn');
	    $(target).addClass('follow_btn');
	    $(target).text("팔로우");
	    deletefollow(no,uno);
	    if(uno==p_uno){
	        var cnt = ($("#followingcnt").text()*1)-1
	        $("#followingcnt").text(cnt);
	    }
	});
