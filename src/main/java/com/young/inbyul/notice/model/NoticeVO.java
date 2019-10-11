package com.young.inbyul.notice.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeVO {
	
	int ntc_no;
	String cmd;
	String sender;
	String recipient;
	int target;
	boolean read;
	String timeago;
	Date ntc_reg_dt;
}
