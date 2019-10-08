package com.young.inbyul.user.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomUser {
	private int uno;
	private String uid;
	private String upw;
	//private String authority;
	private String uemail;
	private String uphone;
	private String uname;
	private String uicon;
	private List<UserRole> roles;
	
	public int followercnt;
	public int followingcnt;
	public int boardcnt;
	
	

	
	
}
