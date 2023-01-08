package com.larrex.productapi;

import com.larrex.productapi.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

@SpringBootTest
class ProductapiApplicationTests {

    @Test
    void testGetProduct() {

        RestTemplate restTemplate = new RestTemplate();

        Product product = restTemplate.getForObject("http://localhost:8080/productapi/products/2", Product.class);

        assertNotNull(product);
        assertEquals("Hp laptop", product.getName());

    }

    @Test
    void testCrateProduct() {

        RestTemplate restTemplate = new RestTemplate();
        Product product = new Product();
        product.setName("Yandex car");
        product.setDescription("A self driving car from yandex");
        product.setPrice(5000);
        Product newProduct = restTemplate.postForObject("http://localhost:8080/productapi/products/", product, Product.class);
        assertNotNull(newProduct);
        assertNotNull(newProduct.getId());
        assertEquals("Yandex car",newProduct.getName());

    }


    @Test
    void testUpdateProduct() {

        RestTemplate restTemplate = new RestTemplate();

        Product product = restTemplate.getForObject("http://localhost:8080/productapi/products/2", Product.class);
        product.setPrice(2999);

        restTemplate.put("http://localhost:8080/productapi/products/2",product);
        

    }
}
