package com.young.inbyul.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class NoHandlerFoundControllerAdvice {
	 	@ExceptionHandler(NoHandlerFoundException.class)
	    public String handle(Exception ex) {
	 		System.out.println("asdsss");
	        return "/error/404";
	    }
	 
	
}
