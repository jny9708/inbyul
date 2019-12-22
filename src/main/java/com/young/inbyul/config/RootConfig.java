package com.young.inbyul.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
//@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.young.inbyul"})
public class RootConfig {
	
	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl mailsender = new JavaMailSenderImpl();
		mailsender.setHost("smtp.gmail.com");
		mailsender.setPort(587);
		mailsender.setUsername("jny9708@gmail.com");
		mailsender.setPassword("비밀번호비공개");
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail.transport.protocol", "smtp");
		javaMailProperties.setProperty("mail.smtp.auth", "true");
		javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
		javaMailProperties.setProperty("mail.debug", "true");
		mailsender.setJavaMailProperties(javaMailProperties);
		return mailsender; 
	}
}
