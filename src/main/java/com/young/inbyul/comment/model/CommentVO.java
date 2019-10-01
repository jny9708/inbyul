package com.young.inbyul.comment.model;

import java.util.Date;

import com.young.inbyul.user.model.CustomUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentVO {

	int cno;
	int bno;
	String ccontent;
	CustomUser user;
	Date c_reg_dt;
	
}
