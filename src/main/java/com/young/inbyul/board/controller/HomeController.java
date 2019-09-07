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
	public String home(@AuthenticationPrincipal SecurityCustomUser customUser,Model model) throws Exception{
//		System.out.println(customUser.getUno()+"?!a");
//		System.out.println(customUser.getUid()+"?!a!");
//		int following_count = boardService.getFollowingCount(customUser.getUno());
//		model.addAttribute("customUser",customUser);
//		model.addAttribute("following_count", following_count);
		return "home/home";
	}
}
