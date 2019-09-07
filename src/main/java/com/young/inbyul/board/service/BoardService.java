package com.young.inbyul.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.inbyul.board.repository.BoardRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository; 
	
	public int getFollowingCount(int uno) throws Exception {
		return boardRepository.getFollowingCount(uno);
	}
	
}
