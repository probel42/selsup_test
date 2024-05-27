package ru.ibelan.selsup_test.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.ibelan.selsup_test.exceptions.ApplicationExceptions;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс предназначен для ограничения частоты вызова метода.
 */
@Slf4j
@RequiredArgsConstructor
public class RequestFreqLimiter {
	private final TimeUnit timeUnit;
	private final int requestLimit;

	private final Queue<LocalDateTime> requestTimes = new LinkedList<>();
	private final ReentrantLock lock = new ReentrantLock(true);

	public void limit(Runnable request) {
		lock.lock(); // тут по идее нужен tryLock с таймаутом, т.к. тут начнут скапливаться потоки, но в постановке не было требования это учитывать
		try {
			waitAvailability();
			try {
				request.run();
			} finally {
				LocalDateTime now = LocalDateTime.now();
				log.debug("TIME IN QUEUE {}", now);
				requestTimes.offer(now);
			}
		} catch (Exception ex) {
			log.warn(ex.getMessage());
			ApplicationExceptions.UNEXPECTED_SERVER_ERROR.throwException(ex);
		} finally {
			lock.unlock();
		}
	}

	private void waitAvailability() throws InterruptedException {
		LocalDateTime deprecationTime = LocalDateTime.now().minus(1, timeUnit.toChronoUnit());
		while (!requestTimes.isEmpty() && requestTimes.peek().isBefore(deprecationTime)) {
			requestTimes.remove();
		}

		if (requestLimit > 0 && requestTimes.size() + 1 > requestLimit) {
			Duration duration = Duration.between(deprecationTime, requestTimes.peek());
			Thread.sleep(duration.toMillis() + 1);
		}
	}
}
