package com.young.inbyul.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.young.inbyul.comment.model.CommentVO;
import com.young.inbyul.comment.repository.CommentRepository;
import com.young.inbyul.util.Criteria;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	@Transactional
	public void insertcmt(CommentVO commentVO) throws Exception {
		commentRepository.insertcmt(commentVO);
		commentRepository.addcmtcnt(commentVO.getBno());
	}

	@Override
	@Transactional
	public void deletecmt(int cno,int bno) throws Exception {
		commentRepository.deletecmt(cno);
		commentRepository.subcmtcnt(bno);
	}

	@Override
	public List<CommentVO> getcmtlist(int bno) throws Exception {
		// TODO Auto-generated method stub
		return commentRepository.getcmtlist(bno);
	}

	@Override
	public List<CommentVO> getcmtlist(int bno, Criteria criteria) throws Exception {
		// TODO Auto-generated method stub
		return commentRepository.getcmtlist(bno,criteria);
	} 
	
}
