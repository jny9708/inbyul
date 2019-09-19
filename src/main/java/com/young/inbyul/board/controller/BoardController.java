package com.young.inbyul.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {

	@RequestMapping("/writeform")
	public String writeform() throws Exception{
		return "board/write";
	}
	
	
}
