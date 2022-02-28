package com.todo.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.todo.spring.model.ToDo;

public interface ToDoService {
	
	List<ToDo> getToDoByUser(String user);
	
	Optional<ToDo> getToDoById(Long id);
	
	void updateToDo(ToDo todo);
	
	void addToDo(String name, String desc, Date targetDate, boolean isDone);
	
	void deleteToDo(long id);
	
	void saveToDo(ToDo todo);

}
