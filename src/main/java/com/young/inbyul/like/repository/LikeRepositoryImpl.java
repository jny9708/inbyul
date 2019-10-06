package com.young.inbyul.like.repository;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LikeRepositoryImpl implements LikeRepository{

	@Autowired
	SqlSessionTemplate sqlSession;
	
	private String namespace = "com.young.inbyul.like";
	
	@Override
	public void insertLike(int bno, int uno) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("uno", uno);
		sqlSession.insert(namespace+".insertLike",map);
	}

	@Override
	public void deleteLike(int bno, int uno) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("uno", uno);
		sqlSession.insert(namespace+".deleteLike",map);
	}

	@Override
	public void updateLikeCnt(int bno,int num) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("num", num);
		sqlSession.update(namespace+".updateLikeCnt",map);
		
	}

	
}
