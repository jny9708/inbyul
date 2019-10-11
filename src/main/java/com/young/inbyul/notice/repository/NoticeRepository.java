package com.young.inbyul.notice.repository;

import java.util.List;

import com.young.inbyul.notice.model.NoticeVO;
import com.young.inbyul.util.Criteria;

public interface NoticeRepository {
	
	public void insertNotice(NoticeVO noticeVO) throws Exception;
	public void updateNotice(int ntc_no) throws Exception;
	public List<NoticeVO> getNoticeList(String uid,Criteria criteria) throws Exception;
	public int getUnreadCnt(String uid) throws Exception;
}
