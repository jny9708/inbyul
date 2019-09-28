package com.young.inbyul.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@RequestMapping(value="/getBorList" ,method =RequestMethod.POST)
	public List<Board> getBorList(@AuthenticationPrincipal SecurityCustomUser securityCustomUser) throws Exception{
		List<Board> list = boardService.getBoardList(securityCustomUser.getUno());
		return list;
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(@ModelAttribute Board board,HttpServletRequest request) throws Exception{
		String path = request.getSession().getServletContext().getRealPath("/resources/images/postimages");
		if(request.getParameter("mode").equals("write")) {
			if(board.getUploadFileArr() != null) {
				logger.info("write");
				
				boardService.insertBoard(board);
				
			}		
		}else {
			logger.info("modify");
			logger.info("filevo " + board.getBcontent());
			logger.info("filevo " + board.getRmvFileArr().get(0).getFile_path());
			logger.info("filevo " + board.getUploadFileArr().get(0).getOriginalFilename());
			
		}
		
		return "board/home";
	}
	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public int update(Board board) throws Exception{
		
		logger.info("filevo " + board.getBcontent());
		logger.info("filevo " + board.getRmvFileArr().get(0).getFile_path());
		
		return 1;
	}
	

	
}
