package com.young.inbyul.follow.repository;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FollowRepositoryImpl implements FollowRepository{

	@Autowired
	SqlSessionTemplate sqlSession;
	
	private String namespace = "com.young.inbyul.follow";
	
	@Override
	public void insertFollow(int follower_no, int following_no) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("follower_no", follower_no);
		map.put("following_no", following_no);
		sqlSession.insert(namespace + ".insertFollow",map);
	}

	@Override
	public void deleteFollow(int follower_no, int following_no) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("follower_no", follower_no);
		map.put("following_no", following_no);
		sqlSession.delete(namespace + ".deleteFollow",map);
		
	}

	@Override
	public void updateFollowCnt(int follower_no, int following_no, int num) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("follower_no", follower_no);
		map.put("following_no", following_no);
		map.put("num", num);
		sqlSession.delete(namespace + ".updateFollowCnt",map);
		
	}

}
