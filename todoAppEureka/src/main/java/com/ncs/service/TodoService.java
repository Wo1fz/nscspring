package com.ncs.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.exception.TodoNotFoundException;
import com.ncs.model.Todo;
import com.ncs.repository.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	TodoRepository todoRepository;

	public List<Todo> getTodos() {
		return todoRepository.findAll();
	}

	public List<Todo> getTodosByUser(String user) {
		
		return todoRepository.findByUser(user);
	}

	public Todo getTodosById(int id) {
		
		return todoRepository.findById(id).orElseThrow(TodoNotFoundException::new);
	}

	public Todo addTodo(Todo todo) {
		return todoRepository.saveAndFlush(todo);
	}

	public Todo updateTodo(Todo todo) {
		Todo found = getTodosById(todo.getId());
		return todoRepository.saveAndFlush(found);
	}

	public boolean deleteTodo(int id) {
		Todo found = getTodosById(id);
		todoRepository.delete(found);
		return true;
	}
	
	public List<Todo> getTodosPendingByUser(LocalDate date, String user) {
		return todoRepository.findByTargetDateAfterAndUserAndDoneFalse(date,user);
	}

	public List<Todo> getTodosByUserAsc() {
		return todoRepository.findByOrderByUserAsc();
	}

}
