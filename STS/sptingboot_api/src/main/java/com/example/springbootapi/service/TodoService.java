package com.example.springbootapi.service;

import javax.validation.ConstraintViolationException;

import com.example.springbootapi.exception.TodoException;
import com.virtusa.springboot.model.TodoDTO;

public interface TodoService {
	public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoException;
	
	
}