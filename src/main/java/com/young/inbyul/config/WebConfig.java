package com.young.inbyul.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"com.young.inbyul"})
public class WebConfig implements WebMvcConfigurer {
	
//	@Override		//resource 
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		configurer.enable(); 
//	}
	 
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

	@Bean
	public MultipartResolver multipartResolver() {
		MultipartResolver multipartResolver = new CommonsMultipartResolver();
		return multipartResolver;
	}
	
	@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        
        return viewResolver;
    }
	
    /**

     * Exception Resolver를 설정한다.

     */

    @Bean
    public SimpleMappingExceptionResolver getExceptionResolver() {
        SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
	// 지정되지 않은 예외에 대한 기본 에러페이지 입니다.
	//smer.setDefaultErrorView("/error/error");

	// 상태코드 맵핑이 없는 예외를 위한 기본 상태값 입니다.
	//smer.setDefaultStatusCode(400);

	// 기본값이 "exception" 입니다. 예외 모돌 속성의 키값입니다. ${exception.message}
	smer.setExceptionAttribute("exception");

	return smer;
    }

	
}
