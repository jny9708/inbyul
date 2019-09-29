package com.young.inbyul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = {RequestMethod.GET,RequestMethod.POST})
	public String home(@RequestParam(required = false , defaultValue ="0") int message, 
						@RequestParam(required=false,defaultValue="") String origin ,Model model) {
		logger.info("Welcome home");
		
		model.addAttribute("origin", origin);
		model.addAttribute("message", message);
		
		return "user/index";
	}
	
	
	
	
}
