package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.blog.exception.InvalidDataException;
import com.example.blog.response.BlogResponse;

@ControllerAdvice
public class UserExceptionController {
	@Autowired
	private BlogResponse blogResponse;
	
	@ExceptionHandler(InvalidDataException.class)
	private ResponseEntity<BlogResponse> invalidDataException(InvalidDataException dataException) {
		blogResponse.setMessage(dataException.getMessage());
		return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.NOT_FOUND);

	}
}