package com.young.inbyul;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import com.young.inbyul.board.controller.RestBoardController;
import com.young.inbyul.config.DatabaseConfig;
import com.young.inbyul.config.RootConfig;
import com.young.inbyul.config.WebConfig;
import com.young.inbyul.config.WebSecurityConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class,WebConfig.class,DatabaseConfig.class,WebSecurityConfig.class})
@WebAppConfiguration
public class PathVariableTest {

	private static final Logger logger = LoggerFactory.getLogger(PathVariableTest.class);
	
	@Autowired
	RestBoardController restBoardController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception{
		mockMvc = MockMvcBuilders.standaloneSetup(restBoardController).build();
	}
	
	@Test
	public void test() throws Exception{
		
		
	}
	
}
