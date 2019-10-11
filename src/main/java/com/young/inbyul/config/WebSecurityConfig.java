package com.young.inbyul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.young.inbyul.security.handler.CustomAuthenticationFailureHandler;
import com.young.inbyul.user.service.CustomUserDetailsService;



@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.young.inbyul"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	
	 @Autowired 
	 private CustomUserDetailsService customUserDetailsService;
	 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserDetailsService)
			.passwordEncoder(passwordEncoder());

	}	
	
	@Bean
	public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");

        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);
        
		http.httpBasic()
				.and()
			.authorizeRequests() //먼저 선언된게 우선순위가 높다.
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/user/**","/","/restuser/**").permitAll()
				.antMatchers("/**").hasRole("USER")
				//.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
				.and()
			.formLogin()
				.usernameParameter("loginid")
				.passwordParameter("password")
				.loginPage("/")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/home")
				.failureHandler(customAuthenticationFailureHandler())
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/");
//				.and()
//			.sessionManagement()
//				.invalidSessionUrl("/");
	} 
}
