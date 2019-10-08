package com.young.inbyul.follow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.young.inbyul.follow.service.FollowService;

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
	
}
