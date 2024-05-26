package ru.ibelan.selsup_test.service;

import ru.ibelan.selsup_test.model.ProductsDoc;

public interface CrptApi {
	void registerProducts(ProductsDoc productsDoc, String sign);
}
