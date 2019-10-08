package com.young.inbyul.follow.repository;

import java.util.HashMap;
import java.util.List;
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

	@Override
	public List<Map<String,Object>> getFollowerList(int p_uno,int uno) throws Exception {
		//p_uno란 조회하고있는 유저의 pk번호
		// uno란 로그인한 유저의 pk번호
		Map<String,Object> map = new HashMap<>();
		map.put("p_uno", p_uno);
		map.put("uno", uno);
		return sqlSession.selectList(namespace+".getFollowerList", map);
	}

	@Override
	public List<Map<String, Object>> getFollowingList(int p_uno, int uno) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("p_uno", p_uno);
		map.put("uno", uno);
		return sqlSession.selectList(namespace+".getFollowingList", map);
	}
	
	

}
