package com.ncs.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ncs.exception.TodoIDMismatchException;
import com.ncs.model.Todo;
import com.ncs.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
	@Autowired
	TodoService todoService;

	@GetMapping
	public ResponseEntity<List<Todo>> getTodos() {
		return new ResponseEntity<List<Todo>>(todoService.getTodos(), HttpStatus.OK);
	}

	@GetMapping("/users/{user}")
	public ResponseEntity<List<Todo>> getTodosByUser(@PathVariable String user) {
		return new ResponseEntity<List<Todo>>(todoService.getTodosByUser(user), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Todo> getTodosById(@PathVariable int id) {
		return new ResponseEntity<Todo>(todoService.getTodosById(id), HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Todo> addTodo(@Valid @RequestBody Todo todo) {

		Todo todoResult = todoService.addTodo(todo);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todoResult.getId())
				.toUri();
		return ResponseEntity.created(location).body(todo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Todo> updateTodo(@Valid @RequestBody Todo todo, @PathVariable int id) {
		if (todo.getId() != id) {
			throw new TodoIDMismatchException();
		}

		Todo todoResult = todoService.updateTodo(todo);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todoResult.getId())
				.toUri();
		return ResponseEntity.created(location).body(todo);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTodoById(@PathVariable int id) {
		if (todoService.deleteTodo(id))
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.badRequest().body("Something unexpected");
	}

	@GetMapping("/pendingbyuser")
	public ResponseEntity<List<Todo>> pendingByUser(@RequestParam String user,
			@RequestParam(defaultValue = "2022-02-10") LocalDate date) {
		return new ResponseEntity<List<Todo>>(todoService.getTodosPendingByUser(date, user), HttpStatus.OK);
	}
	
	@GetMapping("/asc/user")
	public ResponseEntity<List<Todo>> getTodosByUserAsc() {
		return new ResponseEntity<List<Todo>>(todoService.getTodosByUserAsc(), HttpStatus.OK);
	}
}
