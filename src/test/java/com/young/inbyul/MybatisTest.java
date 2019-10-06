package com.young.inbyul;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.young.inbyul.board.model.Board;
import com.young.inbyul.config.DatabaseConfig;
import com.young.inbyul.user.model.CustomUser;
import com.young.inbyul.util.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DatabaseConfig.class)
public class MybatisTest {

	private static final Logger logger = LoggerFactory.getLogger(MybatisTest.class);
	
	@Inject 
	private SqlSessionTemplate sqlSession;
	
	
	//@Test	
	public void test() throws Exception{
		List<Board> boardList = sqlSession.selectList("com.young.inbyul.test.getList");
		
		for(Board board : boardList) {
			logger.info("board uid : " + board.getUser().getUid());
			logger.info("board uid : " + board.getUser().getUicon());
//			for(int i=0; i<board.getFile_path().size(); i++) {
//				logger.info("board file_path : " + board.getFile_path().get(i));
//			}
			
		}
		
	}
	
	//@Test
	public void test2() throws Exception{
		List<CustomUser> userList = sqlSession.selectList("com.young.inbyul.test.getReUserList");
		for(CustomUser user : userList) {
			logger.info("user uid : " + user.getUid());
			logger.info("user uname : " + user.getUname());
		}
		
	}
	
	//@Test
	public void test3() throws Exception{
		Map<String,Object> map = new HashMap<>();
		Criteria criteria = new Criteria();
		map.put("criteria", criteria);
		map.put("uno", 11);
		List<Board> list = sqlSession.selectList("com.young.inbyul.test.getBoardList",map);
		for(Board board : list) {
			logger.info(board.getHeart()+"");
		}
	}
	
	@Test
	public void test4() throws Exception{
		Map<String,Object> map = new HashMap<>();
		map.put("bno", 12);
		map.put("uno", 11);
		Board board = sqlSession.selectOne("com.young.inbyul.test.getBoard",map);
		logger.info(board.getHeart()+"");
	}
	
	
	/*
	 * @Test public void test3() throws Exception{ List<Board> boardList =
	 * sqlSession.selectList("com.young.inbyul.test.getList"); for(Board board :
	 * boardList) { logger.info("board uid : " + board.getUser().getUid());
	 * 
	 * 
	 * for(int i = 0; i<board.getFilevo().size(); i++) {
	 * System.out.println("board file_path " +
	 * board.getFilevo().get(i).getFile_path());
	 * 
	 * }
	 * 
	 * } }
	 */

	
}
