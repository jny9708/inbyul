package com.young.inbyul.user.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.young.inbyul.user.model.CustomUser;
import com.young.inbyul.user.model.SecurityCustomUser;
import com.young.inbyul.user.model.UserRepository;
import com.young.inbyul.user.model.UserRole;
import com.young.inbyul.util.MailUtils;
import com.young.inbyul.util.TempKey;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String loginid) throws UsernameNotFoundException {
		System.out.println(loginid);
		CustomUser customUser = userRepository.getUserById(loginid);
		List<UserRole> roles =userRepository.getRoleById(loginid);		
		customUser.setRoles(roles);
		if(customUser==null) throw new UsernameNotFoundException("아이디 혹은 비밀번호를 잘 못 입력 하셨습니다.");
		return new SecurityCustomUser(customUser);
	}
	
	public static boolean regexp_Email_Check(String loginid) {
		String regExp_email="^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";
		boolean result = loginid.matches(regExp_email);
		return result;
	}
	
	
	@Transactional
	public int insertUser(CustomUser customUser) throws Exception{
		int user_re=-1;
		int key_re=-1;
		
		String regExp_id="^(?=.*[A-Za-z\\._])[A-Za-z\\._0-9]{1,12}$";
		String regExp_email="^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";
		String regExp_phone ="^01[0-9]{9}$";
		String regExp_name = "^[가-힣a-zA-Z]*$";
		String regExp_pw = "^\\S{6,}$";
		
		logger.info(customUser.getUname()+"!");
		
		logger.info(customUser.getUname().matches(regExp_name)+"s");
		
		
		
		
		if(customUser.getUid().matches(regExp_id) && customUser.getUemail().matches(regExp_email) 
			&& customUser.getUphone().matches(regExp_phone) && customUser.getUname().matches(regExp_name)
			&& customUser.getUpw().matches(regExp_pw)) {
			
			UserRole role = new UserRole();
			customUser.setUpw(passwordEncoder.encode(customUser.getUpw()));
			role.setRolename("BASIC");
			customUser.setRoles(Arrays.asList(role));
			user_re = userRepository.insertUser(customUser);
			int uno = userRepository.getUno(customUser.getUid());
			customUser.setUno(uno);
			userRepository.insertRole(customUser);
			
			String key = new TempKey().getKey(50,false);
			
			key_re = userRepository.createAuthKey(uno, key);
			
			
			MailUtils sendMail = new MailUtils(mailSender);
			sendMail.setSubject("[INBYUL] 회원가입 이메일 인증");
			sendMail.setText(new StringBuffer().append("<h1>[INBYUL] 이메일 인증</h1>")
							.append("<p>아래 버튼을 클릭하시면 이메일 인증이 완료됩니다.</p>")
							.append("<a href='http://localhost:8080/inbyul/user/emailconfirm?uno=")
							.append(uno)
							.append("&key=")
							.append(key)
							.append("' style='background: #eb5054; display:block;")
							.append("border-radius: 3px; cursor: pointer; font-size: 13px; font-weight:bold;")
							.append("line-: 35px; text-align: center; box-shadow: 0 1px 0 #b5b6b7; margin-left: 5px; color:#ffffff; padding:5px 10px; text-decoration:none;' target='_blank'>이메일 인증 확인</a>")
							.toString());
			sendMail.setFrom("jny9708@gmail.com", "INBYULADMIN");
			sendMail.setTo(customUser.getUemail());
			sendMail.send();
			
			if(user_re>0 && key_re>0) {
				return 1;
			}else {
				return 0;
			}
			
		}else {
			return 0;
		}
	}
	
	public int getAuthKey(int uno,String key) throws Exception {
		int result =-1;
		String AuthKey = userRepository.getAuthKey(uno);
		if(AuthKey.equals(key) ) {
			result = 1;
		}else {
			result = 0;
		}
		return result;
	}
	
	public void updateRole(int uno, String ch_role) throws Exception{
		userRepository.updateRole(uno, ch_role);
	}
	
	public void deleteAuthKey(int uno) throws Exception{
		userRepository.deleteAuthKey(uno);
	}
	
	public int uidCheck(String uid) throws Exception{
		int result = -1;
		// 중복되면 result = 1 중복이아니라면 0
		if(userRepository.getUid(uid)==null) {
			result =0;
		}else {
			result =1;
		}
		return result;
	}
	
	public int uemailCheck(String uemail) throws Exception{
		int result = -1;
		
		if(userRepository.getUemail(uemail)==null) {
			result = 0;
		}else {
			result =1;
		}
		return result;
	}
}
