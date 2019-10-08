package com.young.inbyul.user.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public class UserRepository {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	String namespace = "com.young.inbyul.login";
	String usernamespace = "com.young.inbyul.user";
	
	public CustomUser getUserById(String loginid){
		Map<String, Object> map = new HashMap<>();
		map.put("loginid",loginid);
		return sqlSession.selectOne(namespace + ".selectUserById",map);
	}
	
	public List<UserRole> getRoleById(int uno){
		return sqlSession.selectList(namespace + ".getRoleById", uno);
	}
	
	public int insertUser(CustomUser customUser) throws Exception {
		return sqlSession.insert(namespace + ".insertUser",customUser);
	}
	
	public int insertRole(CustomUser customUser) throws Exception{
		return sqlSession.insert(namespace + ".insertRole",customUser); 
	}
	
	public int createAuthKey(int uno,String authKey) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("uno",uno);
		map.put("authKey",authKey);
		return sqlSession.insert( namespace + ".createAuthKey",map);
	}
	
	public String getAuthKey(int uno) throws Exception{
		return sqlSession.selectOne( namespace + ".getAuthKey",uno);
	}
	
	public void updateRole(int uno, String ch_role) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("uno",uno);
		map.put("ch_role",ch_role);
		sqlSession.update(namespace + ".updateRole",map);
	}
	
	public void deleteAuthKey(int uno) throws Exception{
		sqlSession.delete(namespace + ".deleteAuthKey", uno);
	}
	
	public String getUid(String uid) throws Exception{
		return sqlSession.selectOne(namespace + ".getUid",uid);
		//select uemail from user where uemail = ?
	}
	
	public String getUemail(String uemail) throws Exception{
		return sqlSession.selectOne(namespace + ".getUemail",uemail);
	}
	
	public int getUno(String uid) throws Exception{
		return sqlSession.selectOne(namespace + ".getUno",uid);
	}
	
	public CustomUser getUserData(String uid) throws Exception{
		return sqlSession.selectOne(usernamespace + ".getUserData", uid);
	}
	
	public int getFollowPresence(String uid, int uno) throws Exception{
		Map<String,Object> map = new HashMap<>();
		map.put("uid",uid); // 해당페이지 주인의 아이디
		map.put("uno",uno); // 로그인한 사람의 유저번호
		return sqlSession.selectOne(usernamespace + ".getFollowPresence",map);
	}
	
}
