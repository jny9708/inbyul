package com.young.inbyul.comment.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.young.inbyul.comment.model.CommentVO;
import com.young.inbyul.util.Criteria;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	String namespace = "com.young.inbyul.comment"; 

	@Override
	public int insertcmt(CommentVO commentVO) throws Exception {
		return sqlSession.insert(namespace + ".insertcmt",commentVO);
	}

	@Override
	public int deletecmt(int cno) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete(namespace + ".deletecmt",cno);
	}

	@Override
	public List<CommentVO> getcmtlist(int bno) throws Exception {
		//return sqlSession.selectList(namespace + ".getcmtlist", bno);
		return null;
	}
	
	@Override
	public void addcmtcnt(int bno) throws Exception{
		sqlSession.update(namespace + ".addcmtcnt" , bno);
	}

	@Override
	public void subcmtcnt(int bno) throws Exception {
		sqlSession.update(namespace + ".subcmtcnt" , bno);
		
	}

	@Override
	public List<CommentVO> getcmtlist(Criteria criteria) throws Exception {
		
		return sqlSession.selectList(namespace + ".getcmtlist", criteria);
	}
	
	

}
