package com.young.inbyul.comment.service;

import java.util.List;

import com.young.inbyul.comment.model.CommentVO;
import com.young.inbyul.util.Criteria;

public interface CommentService {
	
	public void insertcmt(CommentVO commentVO) throws Exception;
	public void deletecmt(int cno, int bno) throws Exception;
	public List<CommentVO> getcmtlist(int bno) throws Exception;
	public List<CommentVO> getcmtlist(int bno,Criteria criteria) throws Exception;
}
