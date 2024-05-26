package ru.ibelan.selsup_test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ibelan.selsup_test.model.ProductsDoc;

@Service
@RequiredArgsConstructor
public class CrptApiService implements CrptApi {
	private final RestTemplate crptRestTemplate;
	private final RestQueueWrapper crptRestQueueWrapper;

	@Override
	public void registerProducts(ProductsDoc productsDoc, String sign) {
		crptRestQueueWrapper.request(() -> crptRestTemplate.postForEntity("", productsDoc, String.class));
	}
}
