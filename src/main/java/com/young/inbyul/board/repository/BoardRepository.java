package com.young.inbyul.board.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.young.inbyul.board.model.Board;
import com.young.inbyul.board.model.FileVO;
import com.young.inbyul.user.model.CustomUser;
import com.young.inbyul.util.Criteria;

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
	
	public List<Map<String,Object>> getReUseList(int uno) throws Exception{
		return sqlSession.selectList(namespace_user + ".getReUserList",uno);
	}
	
	
	public int insertBoard(Board board) throws Exception{
		return sqlSession.insert(namespace_board + ".insertBoard",board);
	}
	
	public int insertImage(Map<String,Object> map) throws Exception{
		return sqlSession.insert(namespace_board + ".insertImage",map);
	}
	
	
	
	public List<Board> getBoardList(int uno,Criteria criteria) throws Exception{
		Map<String,Object> map = new HashMap<>();
		map.put("uno",uno);
		map.put("criteria",criteria);
		return sqlSession.selectList(namespace_board + ".getBoardList", map);
	}
	
	public int deleteBoard(int bno) throws Exception{
		return sqlSession.delete(namespace_board + ".deleteBoard",bno);
	}
	
	public Board getBoard(int bno,int uno) throws Exception{
		Map<String,Object> map = new HashMap<>();
		map.put("uno",uno);
		map.put("bno",bno);
		return sqlSession.selectOne(namespace_board + ".getBoard",map);
	}
	
	public int updateBoard(Board board) throws Exception{
		return sqlSession.update(namespace_board+".updateBoard",board);
	}
	
	public int deleteImages(List<FileVO> rmvFileArr) throws Exception{
		// fno을 foreach로 해서 삭제하셔야합니다!
		return sqlSession.delete(namespace_board+".deleteImages",rmvFileArr);
	}
	
	public List<FileVO> getFilePaths(int bno) throws Exception{
		return sqlSession.selectList(namespace_board+".getfilepath",bno);
	}
	
	public List<Map<String,Object>> getPersonalBoard(String uid,Criteria criteria) throws Exception{
		Map<String,Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("criteria", criteria);
		return sqlSession.selectList(namespace_board + ".getPersonalBoard",map);
	}
	
}
