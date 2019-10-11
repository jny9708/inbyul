package com.young.inbyul.notice.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.young.inbyul.notice.model.NoticeVO;
import com.young.inbyul.util.Criteria;

@Repository
public class NoticeRepositoryImpl implements NoticeRepository {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	String namespace = "com.young.inbyul.notice";

	@Override
	public void insertNotice(NoticeVO noticeVO) throws Exception {
		sqlSession.insert(namespace + ".insertNotice",noticeVO);
	}

	@Override
	public void updateNotice(int ntc_no) throws Exception {
		sqlSession.update(namespace + ".updateNotice",ntc_no);
		
	}

	@Override
	public List<NoticeVO> getNoticeList(String uid,Criteria criteria) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("uid",uid);
		map.put("criteria",criteria);
		return sqlSession.selectList(namespace + ".getNoticeList",map);
	}

	@Override
	public int getUnreadCnt(String uid) throws Exception {
		return sqlSession.selectOne(namespace + ".getUnreadCnt",uid);
	}

}
