package com.todo.spring.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todo.spring.model.ToDo;
import com.todo.spring.service.ToDoService;

@Controller
public class ToDoController {
	
	@Autowired
	private ToDoService todoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(date, false));
	}
	
	@GetMapping("/list-todos")
	public String showTodos(ModelMap model)
	{
		String name = getLoggedInUserName(model);
		model.put("todos", todoService.getToDoByUser(name));
		return "list-todos";
	}
	
	private String getLoggedInUserName(ModelMap map)
	{
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(object instanceof UserDetails)
		{
			return ((UserDetails)object).getUsername();
		}
		return object.toString();
	}
	
	@GetMapping("/add-todo")
	public String showAddTodoPage(ModelMap model)
	{
		model.addAttribute("todo",new ToDo());
		return "todo";
	}
	
	@GetMapping("/delete-todo")
	public String deleteTodo(@RequestParam long id)
	{
		todoService.deleteToDo(id);
		return "redirect:/list-todos";
	}
	
	@GetMapping("/update-todo")
	public String showUpdateTodoPage(@RequestParam long id,ModelMap model)
	{
		ToDo todo = todoService.getToDoById(id).get();
		model.put("todo", todo);
		return "todo";
	}
	
	@PostMapping("/update-todo")
	public String updateTodo(ModelMap model, @Valid ToDo todo, BindingResult result)
	{
		if(result.hasErrors())
		{
			return "todo";
		}
		
		todo.setUserName(getLoggedInUserName(model));
		todoService.updateToDo(todo);
		return "redirect:/list-todos";
	}
	
	@PostMapping("/add-todo")
	public String addTodo(ModelMap model, @Valid ToDo todo, BindingResult result)
	{
		if(result.hasErrors())
		{
			return "todo";
		}
		
		todo.setUserName(getLoggedInUserName(model));
		todoService.saveToDo(todo);
		return "redirect:/list-todos";
	}
}
