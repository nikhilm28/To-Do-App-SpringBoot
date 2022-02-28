package com.todo.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.spring.model.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
	
	List<ToDo> findByUserName(String user);

}
