package com.todo.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest req, Exception exc)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception",exc.getLocalizedMessage());
		mv.addObject("url",req.getRequestURI());
		mv.setViewName("error");
		return mv;
	}
}
