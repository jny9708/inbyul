package com.young.inbyul.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.young.inbyul.user.service.CustomUserDetailsService;

@RestController
@RequestMapping(value = "/restuser")
public class RestUserController {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@RequestMapping(value="/uidcheck",method=RequestMethod.POST)
	public int uidCheck(@RequestParam("uid") String uid) throws Exception {
		return customUserDetailsService.uidCheck(uid);
	}
	
	@RequestMapping(value="/uemailcheck" , method=RequestMethod.POST)
	public int ueamilcheck(@RequestParam String uemail) throws Exception {
		
		return customUserDetailsService.uemailCheck(uemail);
	}
	
}


