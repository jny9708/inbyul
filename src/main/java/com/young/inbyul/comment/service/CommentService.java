package com.young.inbyul.comment.service;

import java.util.List;

import com.young.inbyul.comment.model.CommentVO;

public interface CommentService {
	
	public void insertcmt(CommentVO commentVO) throws Exception;
	public void deletecmt(int cno, int bno) throws Exception;
	public List<CommentVO> getcmtlist(int bno) throws Exception;
}
