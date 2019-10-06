package com.young.inbyul.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.young.inbyul.board.model.Board;
import com.young.inbyul.board.service.BoardService;
import com.young.inbyul.user.model.SecurityCustomUser;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService; 

	@RequestMapping("/writeform")
	public String writeform() throws Exception{
		return "board/write";
	}
	
	@RequestMapping("/deleteboard/{bno}")
	public String boardDelete(@PathVariable int bno) throws Exception{
		boardService.deleteBoard(bno);
		return "redirect:/home";
	}
	
	@RequestMapping(value="/modifyboard/{bno}",method=RequestMethod.GET)
	public String boardModify(@PathVariable int bno, Model model) throws Exception{
		Board board = boardService.getBoard(bno,0);
		model.addAttribute("board", board);
		return "board/write";
	}
	
	@RequestMapping(value="/boardContent/{bno}",method=RequestMethod.GET)
	public String boardContent(@PathVariable int bno,Model model,@AuthenticationPrincipal SecurityCustomUser securityCustomUser) throws Exception{
		Board board = boardService.getBoard(bno,securityCustomUser.getUno());
		model.addAttribute(board);
		return "board/boardContent";
	}
	
	
}
