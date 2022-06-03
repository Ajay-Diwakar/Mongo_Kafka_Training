package com.example.springbootapi.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootapi.exception.TodoException;
import com.example.springbootapi.repository.TodoRepository;
import com.example.springbootapi.service.TodoService;
import com.virtusa.springboot.model.TodoDTO;

@RestController
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepo;
	
	@Autowired
	private TodoService todoService;
	
	
	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos() {
		List<TodoDTO> todos = todoRepo.findAll();
		if (todos.size() > 0) {
			return new ResponseEntity<List<TodoDTO>>(todos, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("No todos available",HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo){
		try {
			todoService.createTodo(todo);
			return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
		}catch(ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}catch(TodoException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getSingletodo(@PathVariable("id") String id) {
		Optional<TodoDTO> todoOptional = todoRepo.findById(id);
		if(todoOptional.isPresent()) {
			return new ResponseEntity<>(todoOptional.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Todo not found" + id,HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/todos/{id}")
	public ResponseEntity<?> updateById(
			@PathVariable("id") String id,
			@RequestBody TodoDTO todo){
		Optional<TodoDTO> todoOptional = todoRepo.findById(id);
		if(todoOptional.isPresent()) {
			TodoDTO todoToSave = todoOptional.get();
			todoToSave.setCompleted(
					todo.getCompleted() != null ?
							todo.getCompleted() : 
								todoToSave.getCompleted()
					);
			todoToSave.setTodo(
					todo.getTodo() != null ?
							todo.getTodo() :
								todoToSave.getTodo()
					);
			todoToSave.setDescription(
					todo.getDescription() != null ?
							todo.getDescription() :
								todoToSave.getDescription()
					);
			todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
			todoRepo.save(todoToSave);
			return new ResponseEntity<>(todoToSave, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Todo not found" + id,HttpStatus.NOT_FOUND);
		}
	}

	
}
