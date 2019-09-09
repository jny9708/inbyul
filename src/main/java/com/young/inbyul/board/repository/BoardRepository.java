package com.young.inbyul.board.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.young.inbyul.user.model.CustomUser;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	String namespace_user = "com.young.inbyul.user";
	String namespace_board = "com.young.inbyul.board";
	
	public int getFollowingCount(int uno) throws Exception{
		return sqlSession.selectOne(namespace_user + ".getFollowingCount",uno);
	}
	
	public int getUserBoardCount(int uno) throws Exception{
		return sqlSession.selectOne(namespace_board + ".getUserBoardCount",uno);
	}
	
	public List<CustomUser> getReUseListr(List<Integer> list) throws Exception{
		return sqlSession.selectList(namespace_user + ".getReUserList", list);
	}
	
	public List<Integer> getPopUserList() throws Exception{
		return sqlSession.selectList(namespace_user + ".getPopUserList");
	}
	
}
