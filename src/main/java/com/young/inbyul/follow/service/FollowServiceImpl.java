package com.young.inbyul.follow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.inbyul.follow.repository.FollowRepository;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowRepository followRepository;

	@Override
	public void insertFollow(int follower_no, int following_no) throws Exception {
		followRepository.insertFollow(follower_no, following_no);
	}

	@Override
	public void deleteFollow(int follower_no, int following_no) throws Exception {
		followRepository.deleteFollow(follower_no, following_no);
	}
	
	
	
}
