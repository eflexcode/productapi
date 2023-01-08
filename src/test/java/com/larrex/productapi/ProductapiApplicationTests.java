package com.larrex.productapi;

import com.larrex.productapi.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ProductapiApplicationTests {

	@Test
	void testGetProduct() {

		RestTemplate restTemplate = new RestTemplate();

		Product product = restTemplate.getForObject("http://localhost:8080/productapi/products/2", Product.class);



	}

}
