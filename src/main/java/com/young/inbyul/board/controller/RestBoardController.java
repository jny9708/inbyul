package com.young.inbyul.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.young.inbyul.board.service.BoardService;
import com.young.inbyul.user.model.CustomUser;

@RestController
@RequestMapping("/restboard")
public class RestBoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/getReList" ,method =RequestMethod.POST)
	public List<CustomUser> getReList() throws Exception{
		return boardService.getReUser();
	}
	
	//@RequestMapping(value="/getReList" ,method =RequestMethod.POST)
	
	
}
