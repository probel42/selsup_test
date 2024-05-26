package ru.ibelan.selsup_test.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ibelan.selsup_test.model.ProductsDoc;
import ru.ibelan.selsup_test.service.CrptApi;

@RestController
@RequiredArgsConstructor
public class MainController {
	private final CrptApi crptApi;

	@PostMapping("products/register")
	public void registerProducts(@RequestBody ProductsDoc productsDoc) {
		crptApi.registerProducts(productsDoc, ""); // из описания не понятно что за подпись
	}
}
