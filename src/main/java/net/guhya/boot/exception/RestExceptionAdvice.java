package net.guhya.boot.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionAdvice {
	
	private static Logger log = LoggerFactory.getLogger(RestExceptionAdvice.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorJson> handleArgumentNotValid(MethodArgumentNotValidException ex) {
		log.debug("Bad request");
		
		FieldError error = ex.getBindingResult().getFieldError();
		String field = error.getField();
		String message = error.getDefaultMessage();
		
		ErrorJson response = new ErrorJson(HttpStatus.BAD_REQUEST.value(), field, message, LocalDateTime.now());
		return new ResponseEntity<ErrorJson>(response, HttpStatus.BAD_REQUEST);
	}
	
}
