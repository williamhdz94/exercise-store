package com.whernandez.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.whernandez.exceptions.BadRequestException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
		
		Map<String, Object> bodyMap = new LinkedHashMap<>();
		bodyMap.put("message", ex.getMessage());
		bodyMap.put("Error", HttpStatus.BAD_REQUEST.toString());
		
		return new ResponseEntity<>(bodyMap, HttpStatus.BAD_REQUEST);
		
	}
	
}
