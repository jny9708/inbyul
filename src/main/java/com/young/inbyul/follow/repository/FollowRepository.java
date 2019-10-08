package com.young.inbyul.follow.repository;

import java.util.List;
import java.util.Map;

public interface FollowRepository {

	public void insertFollow( int follower_no,int following_no) throws Exception;
	public void deleteFollow(int follower_no,int following_no) throws Exception;
	public void updateFollowCnt(int follower_no,int following_no,int num) throws Exception;
	public List<Map<String,Object>> getFollowerList(int p_uno, int uno) throws Exception;
	public List<Map<String,Object>> getFollowingList(int p_uno, int uno) throws Exception;
}
