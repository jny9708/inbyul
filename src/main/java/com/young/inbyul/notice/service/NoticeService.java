package com.young.inbyul.notice.service;

import java.util.List;

import com.young.inbyul.notice.model.NoticeVO;
import com.young.inbyul.util.Criteria;

public interface NoticeService {
	public List<NoticeVO> getNoticeList(String uid,Criteria criteria) throws Exception;
	public void readStatusUpdate(int ntc_no) throws Exception;
	
}
