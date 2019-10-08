package com.young.inbyul.follow.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.young.inbyul.follow.service.FollowService;
import com.young.inbyul.user.model.SecurityCustomUser;

@RestController
@RequestMapping("/follow")
public class RestFollowController {

	@Autowired
	FollowService followService;
	
	@RequestMapping("/insert")
	public void insertFollow(@RequestParam("following_no") int following_no
			,@RequestParam("follower_no") int follower_no) throws Exception {
		
		followService.insertFollow(follower_no,following_no);
	}
	
	@RequestMapping("/delete")
	public void deleteFollow(@RequestParam("following_no") int following_no
			,@RequestParam("follower_no") int follower_no) throws Exception {
		
		followService.deleteFollow(follower_no,following_no);
	}
	
	@RequestMapping("/getfollower")
	public List<Map<String,Object>> getFollowerList(@RequestParam("p_uno") int p_uno 
			,@AuthenticationPrincipal SecurityCustomUser securityCustomUser) throws Exception{
		return followService.getFollowerList(p_uno, securityCustomUser.getUno());
	}
	
	@RequestMapping("/getfollowing")
	public List<Map<String,Object>> getFollowingList(@RequestParam("p_uno") int p_uno 
			,@AuthenticationPrincipal SecurityCustomUser securityCustomUser) throws Exception{
		return followService.getFollowingList(p_uno, securityCustomUser.getUno());
	}
	
	
}
