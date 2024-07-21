package com.sethanimesh.rest.webservices.restful_web_services.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sethanimesh.rest.webservices.restful_web_services.user.UserNotFoundException;

@ControllerAdvice
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) 
			throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), 
				ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) 
			throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), 
				ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), 
				ex.getFieldError().getDefaultMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
}

