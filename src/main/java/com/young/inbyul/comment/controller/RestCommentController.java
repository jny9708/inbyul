package com.young.inbyul.comment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.young.inbyul.comment.model.CommentVO;
import com.young.inbyul.comment.service.CommentService;
import com.young.inbyul.user.model.SecurityCustomUser;

@RestController
@RequestMapping("/restcmt")
public class RestCommentController {

	private static final Logger logger = LoggerFactory.getLogger(RestCommentController.class);
	
	@Autowired
	private CommentService commentService; 
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public void insertcmt(@RequestBody CommentVO commentVO,@AuthenticationPrincipal SecurityCustomUser securityCustomUser) throws Exception{
		if(securityCustomUser.getUno()==commentVO.getUser().getUno()) {
			commentService.insertcmt(commentVO);
		}else {
			throw new Exception();
		}
		
		
	}
	
	@RequestMapping(value="/{uid}/{bno}/{cno}", method=RequestMethod.DELETE)
	public void deletecmt(@PathVariable int cno ,@PathVariable String uid,@PathVariable int bno
			,@AuthenticationPrincipal SecurityCustomUser securityCustomUser) throws Exception{
		if(securityCustomUser.getUsername().equals(uid)) {
			commentService.deletecmt(cno,bno);
		}else {
			throw new Exception();
		}
		
		
	}
	
	@RequestMapping(value="/{bno}",method=RequestMethod.GET)
	public List<CommentVO> getcmtlist(@PathVariable int bno) throws Exception{
		return commentService.getcmtlist(bno);
	}
	
}
