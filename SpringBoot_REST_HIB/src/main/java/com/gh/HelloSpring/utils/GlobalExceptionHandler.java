package com.gh.HelloSpring.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The user does not exists in the system")
	@ExceptionHandler
	public void handleUserNotFoundException(EmployeeNotFoundException exception) {
		LOGGER.error(exception.getMessage(), exception);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		LOGGER.error(exception.getMessage(), exception);
		return exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Either server or database is down")
	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
		return exception.getMessage();
	}
}


