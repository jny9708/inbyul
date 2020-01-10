package com.young.inbyul.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.young.inbyul.board.model.Board;
import com.young.inbyul.board.service.BoardService;
import com.young.inbyul.user.model.CustomUser;
import com.young.inbyul.user.model.SecurityCustomUser;
import com.young.inbyul.util.Criteria;

@RestController
@RequestMapping("/restboard")
public class RestBoardController {

	private static final Logger logger = LoggerFactory.getLogger(RestBoardController.class); 
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/getReList" ,method =RequestMethod.POST)
	public List<Map<String,Object>> getReList(@AuthenticationPrincipal SecurityCustomUser securityCustomUser) throws Exception{
		return boardService.getReUser(securityCustomUser.getUno());
	}
	
	@RequestMapping(value="/getBorList" ,method =RequestMethod.POST)
	public List<Board> getBorList(@AuthenticationPrincipal SecurityCustomUser securityCustomUser, @RequestBody Criteria criteria) throws Exception{
		List<Board> list = boardService.getBoardList(securityCustomUser.getUno(),criteria);
		return list;
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public void upload(@ModelAttribute Board board,HttpServletRequest request) throws Exception{
		//path 필요없음 (20.01.10 수정)
		//String path = request.getSession().getServletContext().getRealPath("/resources/images/postimages");
		if(request.getParameter("mode").equals("write")) {
			if(board.getUploadFileArr() != null) {
				logger.info("write");
				
				boardService.insertBoard(board);		
			}		
		}else {
			logger.info("modify");
		
			boardService.updateBoard(board);
		}	
		
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public int update(Board board) throws Exception{
		
		boardService.updateBoard(board);
		return 1;
	}
	
	@RequestMapping(value="/{uid}",method=RequestMethod.POST)
	public List<Map<String,Object>> getPersonalBoard(@PathVariable String uid ,@RequestBody Criteria criteria) throws Exception{
		return boardService.getPersonalBoard(uid, criteria);
	}
	
}
