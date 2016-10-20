package com.ek;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		LOGGER.error(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage(), exception);
		return exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	}

	
	@ResponseStatus
	@ExceptionHandler(value = HttpServerErrorException.class)
	public String handleHttpServerErrorException(HttpServerErrorException exception) {
		LOGGER.error(exception.getResponseBodyAsString(), exception);	
		return exception.getResponseBodyAsString();
	}
	
	@ResponseStatus
	@ExceptionHandler(value = HttpClientErrorException.class)
	public String handleHttpClientErrorException(HttpClientErrorException exception) {
		LOGGER.error(exception.getResponseBodyAsString(), exception);	
		return exception.getResponseBodyAsString();
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Either server or database is down")
	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
		return exception.getMessage();
	}
	
	@ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT, reason = "Either server or database is down")
	@ExceptionHandler(value = ResourceAccessException.class)
	public String handleResourceAccessException(ResourceAccessException exception) {
		LOGGER.error(exception.getMessage(), exception);
		return exception.getMessage();
	}
	
	
}
