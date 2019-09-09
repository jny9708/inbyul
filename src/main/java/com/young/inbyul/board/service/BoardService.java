package com.young.inbyul.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.inbyul.board.repository.BoardRepository;
import com.young.inbyul.user.model.CustomUser;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository; 
	
	public int getFollowingCount(int uno) throws Exception {
		return boardRepository.getFollowingCount(uno);
	}
	
	public boolean checkRecommendUser(int uno)  throws Exception {
		int followingcount = boardRepository.getFollowingCount(uno);
		int postcount = boardRepository.getUserBoardCount(uno);
		if(followingcount<1 && postcount<1) {
			System.out.println("reok");
			return true;
		}else {
			return false;
		}
		
	}
	
	public List<CustomUser> getReUser() throws Exception{
		List<Integer> popUserList = boardRepository.getPopUserList();
		return boardRepository.getReUseListr(popUserList);
	}
	
}
