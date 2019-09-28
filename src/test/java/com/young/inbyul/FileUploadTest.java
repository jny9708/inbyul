package com.young.inbyul;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.young.inbyul.board.service.BoardService;
import com.young.inbyul.config.DatabaseConfig;
import com.young.inbyul.config.RootConfig;
import com.young.inbyul.config.WebConfig;
import com.young.inbyul.config.WebSecurityConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class,WebConfig.class,DatabaseConfig.class,WebSecurityConfig.class})
public class FileUploadTest {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadTest.class);
	
	public class testclass1{
		
		@Autowired
		BoardService boardService;
		@Test
		public void vovov1() throws Exception{
			
			logger.info("testt");
			boardService.vovovo();
		}
		

				
	}
	
	public class testclass2{
		@Autowired
		BoardService boardService;
		
		@Test
		public void vovov2() throws Exception{
			logger.info("testt2");
			boardService.vovovo();
		}
		

		
	} 
	
	
	
	
	
}
