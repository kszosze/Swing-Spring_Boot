package com.dev.backend.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BackendExceptionHandler {

	@ExceptionHandler(BackendException.class)
	@ResponseBody
	public BackendError errorResponse(BackendException exception) {
		return new BackendError(exception.getErrorCode().name(), exception.getMessage());
	}

}
