package com.young.inbyul;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.young.inbyul.config.DatabaseConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DatabaseConfig.class)
public class JsonTest {

	private static final Logger logger = LoggerFactory.getLogger(MybatisTest.class);
	
	@Test
	public void jsontest() throws Exception{
		
		
		
	}
	
}
