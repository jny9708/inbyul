package com.young.inbyul.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.inbyul.board.model.Board;
import com.young.inbyul.notice.model.NoticeVO;
import com.young.inbyul.notice.repository.NoticeRepository;
import com.young.inbyul.util.Criteria;
import com.young.inbyul.util.TimeAgo;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	NoticeRepository  noticeRepository;

	@Override
	public List<NoticeVO> getNoticeList(String uid,Criteria criteria) throws Exception {
		List<NoticeVO> noticeList = noticeRepository.getNoticeList(uid,criteria);
		TimeAgo timeAgo = new TimeAgo();
		for(NoticeVO noticeVO : noticeList) {
			String timestring = timeAgo.timeString(noticeVO.getNtc_reg_dt());
			noticeVO.setTimeago(timestring);
		}
		return noticeList;
	}

	@Override
	public void readStatusUpdate(int ntc_no) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
