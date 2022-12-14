package org.electronic.arts.nfs.blacklist.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Setter
@Getter
public class DatabaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;

	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
}
