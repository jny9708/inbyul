package com.young.inbyul.config;

import javax.sql.DataSource;

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
import org.springframework.web.filter.CharacterEncodingFilter;

import com.young.inbyul.user.service.CustomUserDetailsService;



@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.young.inbyul"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	
	 @Autowired 
	 private CustomUserDetailsService customUserDetailsService;
	 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserDetailsService)
			.passwordEncoder(passwordEncoder());

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
			.authorizeRequests()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/**").permitAll()
				//.antMatchers("/admin/**").hasRole("ADMIN")
				//.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
				.and()
			.formLogin()
				.usernameParameter("loginid")
				.passwordParameter("password")
				.loginPage("/")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/home")
				.and()
			.logout()
				.logoutSuccessUrl("/");			
	} 
}
