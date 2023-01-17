package com.larrex.productapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Product API",version = "1.0",description = "This API is is products"))
public class ProductapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductapiApplication.class, args);
	}

}
