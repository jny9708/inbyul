package com.young.inbyul.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.young.inbyul.board.service.BoardService;
import com.young.inbyul.user.model.SecurityCustomUser;

@Controller
public class HomeController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/home")
	public String home(@AuthenticationPrincipal SecurityCustomUser securityCustomUser,Model model) throws Exception{
//		System.out.println(customUser.getUno()+"?!a");
//		System.out.println(customUser.getUname()+"?!a!");
//		int following_count = boardService.getFollowingCount(securityCustomUser.getUno());
//		if(following_count < 1) {
//			model.addAttribute("ReUserList", boardService.getReUser());
//		}
		//유저 추천을 해줘야하는가?
		boolean isRecomendUser = boardService.checkRecommendUser(securityCustomUser.getUno());
		model.addAttribute("isRecomendUser", isRecomendUser);
		return "board/home";
	}
	
	@RequestMapping("/writeform")
	public String writeform() throws Exception{
		return "board/write";
	}
	
}
