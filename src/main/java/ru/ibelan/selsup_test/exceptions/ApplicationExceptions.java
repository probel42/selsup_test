package ru.ibelan.selsup_test.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApplicationExceptions {
	UNEXPECTED_SERVER_ERROR(1, "Неизвестная ошибка сервера");
	// todo

	private final Integer code;
	private final String message;

	public void throwException(Throwable cause) {
		throw new ApplicationException(message, cause, code);
	}
}
