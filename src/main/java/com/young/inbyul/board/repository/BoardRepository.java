package com.young.inbyul.board.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	String namespace_user = "com.young.inbyul.user";
	String namespace_board = "com.young.inbyul.board";
	
	public int getFollowingCount(int uno) throws Exception{
		return sqlSession.selectOne(namespace_user + ".getFollowingCount",uno);
	}
	
}
