package com.young.inbyul.comment.repository;

import java.util.List;

import com.young.inbyul.comment.model.CommentVO;
import com.young.inbyul.util.Criteria;

public interface CommentRepository {
	
	public int insertcmt(CommentVO commentVO) throws Exception;
	public int deletecmt(int cno) throws Exception;
	public List<CommentVO> getcmtlist(int bno) throws Exception;
	public void addcmtcnt(int bno) throws Exception;
	public void subcmtcnt(int bno) throws Exception;
	public List<CommentVO> getcmtlist(Criteria criteria) throws Exception;
}
