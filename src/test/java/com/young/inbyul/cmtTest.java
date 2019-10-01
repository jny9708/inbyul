package com.young.inbyul;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.young.inbyul.comment.model.CommentVO;
import com.young.inbyul.comment.service.CommentService;
import com.young.inbyul.config.DatabaseConfig;
import com.young.inbyul.config.RootConfig;
import com.young.inbyul.config.WebConfig;
import com.young.inbyul.config.WebSecurityConfig;
import com.young.inbyul.user.model.CustomUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class,WebConfig.class,DatabaseConfig.class,WebSecurityConfig.class})
@WebAppConfiguration
public class cmtTest {
	@Autowired
	CommentService commentService;
	
	private static final Logger logger = LoggerFactory.getLogger(cmtTest.class);
	
	//@Test
	public void insertcmt() throws Exception {
		CommentVO commentVO = new CommentVO();
		CustomUser user  = new CustomUser();
		commentVO.setBno(14);
		commentVO.setCcontent("asdasdasd");
		user.setUno(11);
		commentVO.setUser(user);
		commentService.insertcmt(commentVO);
		logger.info("asdasd1");
	}
	
	//@Test
	public void getcmtlist() throws Exception{
		List<CommentVO> commentVOList = commentService.getcmtlist(14);
		for(CommentVO commentVO : commentVOList ) {
			logger.info(commentVO.getCno()+"");
			logger.info(commentVO.getUser().getUno()+"");
			logger.info(commentVO.getCcontent()+"");
		}
		
		logger.info("asdasd2");
	}
	
	@Test
	public void deletecmt() throws Exception{
		
	}
}
