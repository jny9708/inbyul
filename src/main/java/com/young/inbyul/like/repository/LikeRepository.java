package com.young.inbyul.like.repository;

public interface LikeRepository {
	public void insertLike(int bno, int uno) throws Exception;
	public void updateLikeCnt(int bno,int num) throws Exception;
	public void deleteLike(int bno, int uno) throws Exception;
	
}
