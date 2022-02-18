package ncs.controller.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ncs.exception.ErrorResponse;
import com.ncs.exception.TodoIDMismatchException;
import com.ncs.exception.TodoNotFoundException;

@RestControllerAdvice
public class TodoControllerAdvice {
	@ExceptionHandler(TodoNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleTodoNotFound(TodoNotFoundException e) {
		ErrorResponse er = new ErrorResponse("TODO-404", "Todo with the specified ID not Found!", new Date());
		ResponseEntity <ErrorResponse> error = new ResponseEntity<ErrorResponse> (er, HttpStatus.NOT_FOUND);
		return error;
	}
	
	@ExceptionHandler(TodoIDMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleTodoIDMismatch(TodoIDMismatchException e) {
		ErrorResponse err = new ErrorResponse("TODO-400", "ID mismatch!", new Date());
		ResponseEntity<ErrorResponse> error = new ResponseEntity<ErrorResponse> (err, HttpStatus.BAD_REQUEST);
		return error;
	}
}
