package com.user;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The user does not exists in the system")
	@ExceptionHandler
	public void handleUserNotFoundException(UserNotFoundException exception) {
		LOGGER.error(exception.getMessage(), exception);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		LOGGER.error(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage(), exception);
		return exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Either server or database is down")
	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
		return exception.getMessage();
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	public String handleDataIntegrityViolationException() {
		DataIntegrityViolationException exception=new DataIntegrityViolationException("Email Id Already Exist.");
		LOGGER.error(exception.getMessage(), exception);
		return exception.getMessage();
	}
}
