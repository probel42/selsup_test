package ru.ibelan.selsup_test.service;

import lombok.RequiredArgsConstructor;
import ru.ibelan.selsup_test.exceptions.ApplicationExceptions;

import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс предназначен для проверки доступности отправки сообщения,
 * т.к. некоторые API ограничивают кол-во допустимых вызовов.
 */
@RequiredArgsConstructor
public class RestQueue {
	private final TimeUnit timeUnit;
	private final int requestLimit;

	private final Queue<LocalTime> requestTimes = new LinkedList<>();
	private final ReentrantLock lock = new ReentrantLock(true);

	public void waitAvailability() {
		lock.lock();
		try {
			LocalTime deprecationTime = LocalTime.now().minus(1, timeUnit.toChronoUnit());
			while (!requestTimes.isEmpty() && requestTimes.peek().isBefore(deprecationTime)) {
				requestTimes.remove();
			}

			if (requestLimit > 0 && requestTimes.size() + 1 > requestLimit) {
				Duration duration = Duration.between(deprecationTime, requestTimes.peek());
				Thread.sleep(duration.toMillis() + 1);
			}

			requestTimes.offer(LocalTime.now()); // нарушаем SRP, но так проще
		} catch (InterruptedException ex) {
			ApplicationExceptions.UNEXPECTED_SERVER_ERROR.throwException(ex);
		} finally {
			lock.unlock();
		}
	}
}
