package com.mihaimadan.entero.rest;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {EnteroRestController.class})
public class PatientRestExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(PatientNotFoundException exc) {
		
		ErrorResponse error = new ErrorResponse(
				HttpStatus.NOT_FOUND.value(), exc.getMessage(), LocalTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception exc) {
		
		ErrorResponse error = new ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), exc.getMessage(), LocalTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	

}
