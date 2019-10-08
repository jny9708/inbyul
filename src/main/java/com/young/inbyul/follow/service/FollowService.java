package com.young.inbyul.follow.service;

import java.util.List;
import java.util.Map;

public interface FollowService {
	public void insertFollow( int follower_no,int following_no) throws Exception;
	public void deleteFollow(int follower_no,int following_no) throws Exception;
	public List<Map<String,Object>> getFollowerList(int p_uno,int uno) throws Exception;
	public List<Map<String,Object>> getFollowingList(int p_uno,int uno) throws Exception;
	
}
