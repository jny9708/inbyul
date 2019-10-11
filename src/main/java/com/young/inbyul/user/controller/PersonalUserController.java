package com.young.inbyul.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.young.inbyul.user.model.CustomUser;
import com.young.inbyul.user.model.SecurityCustomUser;
import com.young.inbyul.user.service.CustomUserDetailsService;


//원래 usercontroller사용하려고했는데 내가 security config에 user/**는 모든 사람에게 접근허용이라고 해놨어서 따로 클래스 만듬
@Controller
public class PersonalUserController {
	
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@RequestMapping(value="/{uid}",method=RequestMethod.GET)
	public String personalBoard(@PathVariable String uid ,Model model,@AuthenticationPrincipal SecurityCustomUser securityCustomUser) throws Exception{
			CustomUser customUser = customUserDetailsService.getUserData(uid); 	
			
			boolean userauth = false;
			int followpresence = -1;
			
				if(uid.equals(securityCustomUser.getUsername())) {
					userauth = true;
					model.addAttribute("userauth", userauth);
				}else {
					followpresence = customUserDetailsService.getFollowPresence(uid, securityCustomUser.getUno());
					model.addAttribute("followpresence", followpresence);
					}
			
			
		model.addAttribute("user", customUser);
		return "/board/personalPage";
	}
}
