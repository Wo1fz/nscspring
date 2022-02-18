package com.ncs.controller.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ncs.exception.AccountNotFoundException;
import com.ncs.exception.AccountTypeNotFoundException;
import com.ncs.exception.BankNotFoundException;
import com.ncs.exception.ErrorResponse;

@RestControllerAdvice
public class AccountControllerAdvice {
	@ExceptionHandler(AccountNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleAccountNotFound(AccountNotFoundException e) {
		ErrorResponse er = new ErrorResponse("TODO-404", "Account with the specified ID not Found!", new Date());
		ResponseEntity <ErrorResponse> error = new ResponseEntity<ErrorResponse> (er, HttpStatus.NOT_FOUND);
		return error;
	}
	
	@ExceptionHandler(AccountTypeNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleAccountTypeNotFound(AccountTypeNotFoundException e) {
		ErrorResponse er = new ErrorResponse("TODO-404", "Account with the specified type not Found!", new Date());
		ResponseEntity <ErrorResponse> error = new ResponseEntity<ErrorResponse> (er, HttpStatus.NOT_FOUND);
		return error;
	}
	
	@ExceptionHandler(BankNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleBankNotFound(BankNotFoundException e) {
		ErrorResponse er = new ErrorResponse("TODO-404", "Account from the specified bank not Found!", new Date());
		ResponseEntity <ErrorResponse> error = new ResponseEntity<ErrorResponse> (er, HttpStatus.NOT_FOUND);
		return error;
	}
}
