package com.young.inbyul.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.young.inbyul.board.model.Board;
import com.young.inbyul.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService; 

	@RequestMapping("/writeform")
	public String writeform() throws Exception{
		return "board/write";
	}
	
	@RequestMapping("/boarddelete")
	public String boardDelete(@RequestParam int bno) throws Exception{
		boardService.boardDelete(bno);
		return "board/home";
	}
	
	@RequestMapping(value="/boardmodify",method=RequestMethod.GET)
	public String boardModify(@RequestParam int bno, Model model) throws Exception{
		Board board = boardService.getBoard(bno);
		model.addAttribute("board", board);
		return "board/write";
	}
	
}
