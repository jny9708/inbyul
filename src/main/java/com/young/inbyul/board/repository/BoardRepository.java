package com.young.inbyul.board.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.young.inbyul.board.model.Board;
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
	
	public List<CustomUser> getReUseList(int uno) throws Exception{
		return sqlSession.selectList(namespace_user + ".getReUserList",uno);
	}
	
	
	public int insertBoard(Board board) throws Exception{
		return sqlSession.insert(namespace_board + ".insertBoard",board);
	}
	
	public int insertImages(Board board) throws Exception{
		return sqlSession.insert(namespace_board + ".insertImages",board);
	}
	
	public List<Integer> getFollowerList(int uno) throws Exception{
		return sqlSession.selectList(namespace_board + ".getFollowerList",uno);
	}
	
	public List<Board> getBoardList(int uno, List<Integer> followerList) throws Exception{
		Map<String,Object> map = new HashMap<>();
		map.put("uno",uno);
		map.put("followerList",followerList);
		return sqlSession.selectList(namespace_board + ".getBoardList", map);
	}
	
}
