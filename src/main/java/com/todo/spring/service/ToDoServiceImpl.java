package com.todo.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.spring.model.ToDo;
import com.todo.spring.repository.ToDoRepository;

@Service
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	private ToDoRepository todoRepository;
	
	@Override
	public List<ToDo> getToDoByUser(String user) {
		return todoRepository.findByUserName(user);
	}

	@Override
	public Optional<ToDo> getToDoById(Long id) {
		return todoRepository.findById(id);
	}

	@Override
	public void updateToDo(ToDo todo) {
		todoRepository.save(todo);
	}

	@Override
	public void addToDo(String name, String desc, Date targetDate, boolean isDone) {
		todoRepository.save(new ToDo(name,desc,targetDate,isDone));
	}

	@Override
	public void deleteToDo(long id) {
		Optional<ToDo> todo = todoRepository.findById(id);
		if(todo.isPresent())
		{
			todoRepository.delete(todo.get());
		}
	}

	@Override
	public void saveToDo(ToDo todo) {
		todoRepository.save(todo);
	}

}
