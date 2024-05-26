package ru.ibelan.selsup_test.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ibelan.selsup_test.model.ProductsDoc;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrptApiService implements CrptApi {
	private final RestTemplate crptRestTemplate;
	private final RestQueueWrapper crptRestQueueWrapper;

	@Override
	public void registerProducts(ProductsDoc productsDoc, String sign) {
		crptRestQueueWrapper.request(() -> crptRestTemplate.postForEntity("", productsDoc, String.class));
	}
}
