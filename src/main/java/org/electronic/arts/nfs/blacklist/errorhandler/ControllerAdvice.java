package org.electronic.arts.nfs.blacklist.errorhandler;

import java.time.LocalDateTime;

import org.electronic.arts.nfs.blacklist.exception.DatabaseException;
import org.electronic.arts.nfs.blacklist.exception.RequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(value = RequestException.class)
	public ResponseEntity<ApiError> requestInvalidExceptionHandler(RequestException ex) {
		ApiError error = ApiError.builder().code(ex.getCode()).message(ex.getMessage()).timestamp(LocalDateTime.now()).build();
		return new ResponseEntity<>(error, ex.getStatus());
	}
	
	@ExceptionHandler(value = DatabaseException.class)
	public ResponseEntity<ApiError> databaseExceptionHandler(DatabaseException ex) {
		ApiError error = ApiError.builder().message(ex.getMessage()).timestamp(LocalDateTime.now()).build();
		return new ResponseEntity<>(error, ex.getStatus());
	}
}
