package com.young.inbyul.follow.repository;

public interface FollowRepository {

	public void insertFollow( int follower_no,int following_no) throws Exception;
	public void deleteFollow(int follower_no,int following_no) throws Exception;
}
