package com.example.springbootapi.exception;

public class TodoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TodoException(String message) {
		super(message);
	}
	
	public static String NotfoundException(String id) {
		return "Todo with " + id + "not found";
	}
	
	public static String TodoAlreadyExists() {
		return "todo with given name already exists";
	}

}
