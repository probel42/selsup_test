package ru.ibelan.selsup_test.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalTime;

@Slf4j
@Configuration
public class RestTemplateConfiguration {
	@Bean
	public RestTemplate crptRestTemplate(RestTemplateBuilder builder, @Value( "${crpt.url}" ) String crptUrl) {
		return builder
				.uriTemplateHandler(new DefaultUriBuilderFactory(crptUrl))
				.interceptors((request, body, execution) -> {
					log.debug("REQUEST TIME: {}", LocalTime.now());
					ClientHttpResponse response = execution.execute(request, body);
					log.debug("RESPONSE TIME {}", LocalTime.now());
					return response;
				})
				.build();
	}
}
