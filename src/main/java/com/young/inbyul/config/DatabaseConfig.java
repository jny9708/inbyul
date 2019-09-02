package com.young.inbyul.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:datasource.properties")
public class DatabaseConfig {
	@Value("${jdbc.driverclassname}")
	 private String driverclassname;
	
	 @Value("${jdbc.url}")
	 private String url;
	    
	 @Value("${jdbc.username}")
	 private String username;
	    
	 @Value("${jdbc.password}")
	 private String password;
	 
	 @Bean
	    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	        return new PropertySourcesPlaceholderConfigurer();
	    }
	 
	 @Bean 
	 public DataSource dataSource() { 
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
		 dataSource.setDriverClassName(driverclassname);
		 dataSource.setUrl(url); 
		 dataSource.setUsername(username); 
		 dataSource.setPassword(password); 
		 
		 return dataSource; 
	}
	  
	 @Bean 
	 public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		 SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		 sqlSessionFactory.setDataSource(dataSource());
	  
		 PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		 Resource[] resources = resolver.getResources("classpath*:mapper/**/*-mapper.xml");
		 sqlSessionFactory.setMapperLocations(resources);
		 return sqlSessionFactory; 
	 }
	  
	 @Bean(destroyMethod = "clearCache") 
	 public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) { 
		 return new SqlSessionTemplate(sqlSessionFactory); 
	 }
	 
}
