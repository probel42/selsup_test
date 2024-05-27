package ru.ibelan.selsup_test.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ibelan.selsup_test.service.RequestQueue;

import java.util.concurrent.TimeUnit;

@Configuration
public class RestQueueConfiguration {
	@Bean
	public RequestQueue crptRequestQueue(@Value( "${crpt.queue.timeUnit}" ) TimeUnit timeUnit,
										 @Value( "${crpt.queue.requestLimit}") int requestLimit) {
		return new RequestQueue(timeUnit, requestLimit);
	}
}
