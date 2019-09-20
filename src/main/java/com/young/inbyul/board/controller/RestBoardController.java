package com.young.inbyul.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.young.inbyul.board.model.Board;
import com.young.inbyul.board.service.BoardService;
import com.young.inbyul.user.model.CustomUser;
import com.young.inbyul.user.model.SecurityCustomUser;

@RestController
@RequestMapping("/restboard")
public class RestBoardController {

	private static final Logger logger = LoggerFactory.getLogger(RestBoardController.class); 
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/getReList" ,method =RequestMethod.POST)
	public List<CustomUser> getReList(@AuthenticationPrincipal SecurityCustomUser securityCustomUser) throws Exception{
		return boardService.getReUser(securityCustomUser.getUno());
	}
	
//	@RequestMapping(value="/getBorList" ,method =RequestMethod.POST)
//	public List<Board> getBorList() throws Exception{
//		
//	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(Board board,HttpServletRequest request) throws Exception{
		
//		Map<String, MultipartFile> fileMap = board.getFile();

		
		String path = request.getSession().getServletContext().getRealPath("/resources/images/postimages");

		if(board.getFile()!=null) {
			boardService.insertBoard(board,path);
		}		
		 
		 
		return "board/home";
	}
	
	
}
