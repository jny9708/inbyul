package com.young.inbyul.follow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.young.inbyul.follow.repository.FollowRepository;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowRepository followRepository;

	@Override
	@Transactional
	public void insertFollow(int follower_no, int following_no) throws Exception {
		followRepository.insertFollow(follower_no, following_no);
		followRepository.updateFollowCnt(follower_no, following_no, 1);
	}

	@Override
	@Transactional
	public void deleteFollow(int follower_no, int following_no) throws Exception {
		followRepository.deleteFollow(follower_no, following_no);
		followRepository.updateFollowCnt(follower_no, following_no, -1);
	}

	@Override
	public List<Map<String, Object>> getFollowerList(int p_uno, int uno) throws Exception {
		return followRepository.getFollowerList(p_uno, uno);
	}

	@Override
	public List<Map<String, Object>> getFollowingList(int p_uno, int uno) throws Exception {
		return followRepository.getFollowingList(p_uno, uno);
	}
	
	
	
}
