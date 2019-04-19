package com.rabobank.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author vinesh
 *
 */

@ControllerAdvice
public class RaboExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(FileFormatException.class)
	public ResponseEntity<ErrorResponse> fileTypeUnSupported(Exception ex, WebRequest request) {

		ErrorResponse error = new ErrorResponse();
		error.setError("Incorrect File Format");
		error.setDescription("UnSupported File Format , File format should be CSV or XML");

		return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

}
