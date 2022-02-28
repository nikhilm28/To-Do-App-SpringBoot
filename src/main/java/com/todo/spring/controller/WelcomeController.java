package com.todo.spring.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping("/")
	public String showWelcomePage(ModelMap model)
	{
		model.put("name", getLoggedinUserName());
		return "welcome";
	}
	
	private String getLoggedinUserName()
	{
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(object instanceof UserDetails)
		{
			return ((UserDetails)object).getUsername();
		}
		return object.toString();
	} 

}

