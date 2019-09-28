package com.young.inbyul;


import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.young.inbyul.config.DatabaseConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DatabaseConfig.class)
public class timeStringTest {

private static final Logger logger = LoggerFactory.getLogger(timeStringTest.class);
	
		public static final int SEC = 60;
		
		public static final int MIN = 60;
		
		public static final int HOUR = 24;
		
		public static final int DAY = 30;
		
		public static final int MONTH = 12;

	@Inject 
	private SqlSessionTemplate sqlSession;
	
	@Test
	public void testtimeString() throws Exception{
		Date tempDate = sqlSession.selectOne("com.young.inbyul.test.gettime");
		
		long curTime = System.currentTimeMillis();

		long regTime = tempDate.getTime();

		long diffTime = (curTime - regTime) / 1000;



		String msg = null;

		if (diffTime < SEC) {

			// sec

			msg = "방금 전";

		} else if ((diffTime /= SEC) < MIN) {

			// min

			msg = diffTime + "분 전";

		} else if ((diffTime /= MIN) < HOUR) {

			// hour

			msg = (diffTime) + "시간 전";

		} else if ((diffTime /= HOUR) < DAY) {

			// day

			msg = (diffTime) + "일 전";

		} else if ((diffTime /= DAY) < MONTH) {

			// day

			msg = (diffTime) + "달 전";

		} else {

			msg = (diffTime) + "년 전";

		}



		logger.info(msg);
		
	}
}
