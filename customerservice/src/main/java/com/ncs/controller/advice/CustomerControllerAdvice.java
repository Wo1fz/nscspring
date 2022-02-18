package com.ncs.controller.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ncs.exception.CustomerNotFoundException;
import com.ncs.exception.ErrorResponse;

@RestControllerAdvice
public class CustomerControllerAdvice {
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleAccountNotFound(CustomerNotFoundException e) {
		ErrorResponse er = new ErrorResponse("TODO-404", "Customer with the specified ID not Found!", new Date());
		ResponseEntity <ErrorResponse> error = new ResponseEntity<ErrorResponse> (er, HttpStatus.NOT_FOUND);
		return error;
	}
}
