package com.young.inbyul.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.young.inbyul.user.model.CustomUser;
import com.young.inbyul.user.service.CustomUserDetailsService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@RequestMapping(value="/signup")
	public String signUp() throws Exception {
		return "user/signup";
	}
	
	
	@RequestMapping(value="/insertuser", method=RequestMethod.POST) 
	public String insertUser(RedirectAttributes redirectAttributes , CustomUser customUser) throws Exception{
		int result = -1;
		
		logger.info(customUser.getUname()+"?");
		result = customUserDetailsService.insertUser(customUser);
		
//		logger.info(customUser.getUid()+"?");
		redirectAttributes.addAttribute("origin", "insertuser");
		if(result>0) {
			redirectAttributes.addAttribute("message",1);
			 
		}else {
			redirectAttributes.addAttribute("message", 0);
		}
		return "redirect:/";
		
	}
	
	@RequestMapping(value="/emailconfirm" , method=RequestMethod.POST)
	public String emailConfirm(RedirectAttributes redirectAttributes,@RequestParam("uno") int uno
								,@RequestParam("key") String key ) throws Exception {
		int result =-1;
		result = customUserDetailsService.getAuthKey(uno, key);
		redirectAttributes.addAttribute("origin", "emailconfirm");
		if(result>0) {
			redirectAttributes.addAttribute("message", 1);
			String ch_role="USER";
			customUserDetailsService.updateRole(uno,ch_role);
			customUserDetailsService.deleteAuthKey(uno);
		}else {
			redirectAttributes.addAttribute("message", 0);
		}
		
		return "redirect:/";
		
		
	}
	
	
	 
	
}
