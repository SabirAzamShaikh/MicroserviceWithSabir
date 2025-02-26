package com.example.Hotel.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> InternalException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ex.getMessage());
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<Map<String, String>> handleDatabaseException(DataAccessException ex) 
	{
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", "Database error occurred");
		errorResponse.put("message", ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage());
		errorResponse.put("hint", "Ensure your data meets all the required constraints.");
		return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String, String>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", "HTTP method not supported");
		errorResponse.put("message", ex.getMessage());
		errorResponse.put("hint", "Check the allowed HTTP methods for this endpoint.");
		return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> InvalidDataValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", "Invalid Data Entered");
		ex.getBindingResult().getFieldErrors().forEach(
				Error->errorResponse.put(Error.getField(),Error.getDefaultMessage())
		);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}
//	@ExceptionHandler(UserAlreadyExistsException.class)
//	public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("email", ex.getMessage()));
//	}
//	@ExceptionHandler(UserNotFoundException.class)
//	public ResponseEntity<ErrorResponse> UserNotFoundException(UserNotFoundException ex) {
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("USER", ex.getMessage()));
//	}
	

}
