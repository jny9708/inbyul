package com.young.inbyul.like.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.young.inbyul.like.repository.LikeRepository;

@Service
public class LikeServiceImpl implements LikeService{

	@Autowired
	LikeRepository likeRepository; 
	
	@Override
	@Transactional
	public void insertLike(int bno, int uno) throws Exception {
		likeRepository.insertLike(bno, uno);
		likeRepository.updateLikeCnt(bno, 1);
	}

	@Override
	@Transactional
	public void deleteLike(int bno, int uno) throws Exception {
		likeRepository.deleteLike(bno, uno);
		likeRepository.updateLikeCnt(bno, -1);
	}

}
