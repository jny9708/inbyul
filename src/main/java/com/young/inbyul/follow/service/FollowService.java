package com.young.inbyul.follow.service;

public interface FollowService {
	public void insertFollow( int follower_no,int following_no) throws Exception;
	public void deleteFollow(int follower_no,int following_no) throws Exception;
}
