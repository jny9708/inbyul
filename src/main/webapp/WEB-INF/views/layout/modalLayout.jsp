<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<body>
	
	  <!-- Modal -->
    <div class="modal fade" id="ModalMore" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                
             <div class="modal-body" style="padding:0px;">
                    <a href="#" id="m_modify" class="md_f">
                        	수정하기
                    </a>
                    <a href="#" id="m_delete" class="md_f">
                        	삭제하기
                    </a>    
                    <a href="#" class="md_f" id="m_shared" data-dismiss="modal" data-toggle="modal" data-target="#sharedlink">
                        	공유하기
                    </a>
                    <a href="#" class="md_f" data-dismiss="modal">
                        	취소
                    </a>
                </div>
            </div>
            </div>
        </div>
        <div class="modal fade" id="otherModalMore" tabindex="-1" role="dialog" aria-labelledby="otherModalMore" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                
             <div class="modal-body" style="padding:0px;">
                    <a href="#" id="m_redirect"  class="md_f">
                        	게시물로 이동
                    </a>
                    <a href="#" class="md_f" id="m_shared" data-dismiss="modal" data-toggle="modal" data-target="#sharedlink">
                        	공유하기
                    </a>
                    <a href="#" class="md_f" data-dismiss="modal">
                        	취소
                    </a>
                </div>
            </div>
            </div>
        </div>

    <div class="modal fade" id="commentmodal" tabindex="-1" role="dialog" aria-labelledby="commentmodal" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="commentmodal">댓글 보기</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body" id="m_addcmt">

                    <!-- <div class="mymb-f">
                        <div class="mymb-s">
                            <a href="#">
                                    <img src="" width="32px" height="32px"></img>  
                            </a>
                        </div>
                        <div class="mymb-s">
                            <div class="mymb-t">
                                <div style="font-weight: bold;"></div>
                                
                                <a href="#">
                                    <i class="far fa-trash-alt"></i>
                                </a>
                            </div>
                            <div>comment contentasdsssssssssssssssss</div>
                        </div>
                    </div> -->
                    

                </div>
                <section class="comment_write">

                        <form class="comment_f">
                                <textarea placeholder="댓글 달기" id="m_cmt_bno${board.bno}"></textarea>
                                
                                <button type="button" class="comment_sub c_sbm_btn cm_sbm_btn" id="" >게시</button>
                                
                                
                            </form>
                </section>
              </div>
            </div>
          </div>
	
	
</body>
</html>