package ru.ibelan.selsup_test.exceptions;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
	private final Integer code;

	public ApplicationException(String message, Throwable cause, Integer code) {
		super(message, cause);
		this.code = code;
	}
}
