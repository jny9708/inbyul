package com.young.inbyul.like.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.young.inbyul.like.service.LikeService;
import com.young.inbyul.user.model.SecurityCustomUser;

@RestController
@RequestMapping("/like")
public class RestLikeController {
	
	@Autowired
	LikeService likeService;
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public void insertLike(@RequestParam int bno,@AuthenticationPrincipal SecurityCustomUser securityCustomUser) throws Exception{
		likeService.insertLike(bno, securityCustomUser.getUno());
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public void deleteLike(@RequestParam int bno,@AuthenticationPrincipal SecurityCustomUser securityCustomUser) throws Exception{
		likeService.deleteLike(bno, securityCustomUser.getUno());
	}
	
	

}
