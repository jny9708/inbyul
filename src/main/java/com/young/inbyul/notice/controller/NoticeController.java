package com.young.inbyul.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {

	@RequestMapping("/notice")
	public String notice() throws Exception{
		
		return "/notice/notice";
	}
}
