package com.young.inbyul.user.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityCustomUser extends User{

	private static final long serialVersionUID = 1L;
	private static final String ROLE_PREFIX = "ROLE_";
	
	private int uno; 
	private String uname;
	private String uemail;
	private String uphone;
	private String uicon;
	
	public SecurityCustomUser(CustomUser customUser ) {
		super(customUser.getUid(),customUser.getUpw(),makeGrantedAuthority(customUser.getRoles()));
	}

	private static List<GrantedAuthority> makeGrantedAuthority(List<UserRole> roles){
		List<GrantedAuthority> list = new ArrayList<>();
		
		for(int i=0; i<roles.size(); i++) {
			list.add(new SimpleGrantedAuthority(ROLE_PREFIX + roles.get(i).getRolename()));
		}
		
		return list;
	}

	

}
