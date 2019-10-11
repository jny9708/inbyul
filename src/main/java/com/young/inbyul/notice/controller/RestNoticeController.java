package com.young.inbyul.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.young.inbyul.notice.model.NoticeVO;
import com.young.inbyul.notice.service.NoticeService;
import com.young.inbyul.util.Criteria;

@RestController
@RequestMapping("/restntc")
public class RestNoticeController {
	
	@Autowired
	NoticeService noticeService;
	
	@RequestMapping("/{uid}")
	public List<NoticeVO> getntcList(@PathVariable String uid ,@RequestBody Criteria criteria) throws Exception{
		return noticeService.getNoticeList(uid,criteria);
	}
	
	@RequestMapping("/")
	public void readStatusUpdate(@RequestParam int ntc_no) throws Exception{
		System.out.println(ntc_no);
	}
}
