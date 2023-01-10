package com.larrex.productapi.controllers;

import com.larrex.productapi.model.Product;
import com.larrex.productapi.repos.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/products/", method = RequestMethod.GET)
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    @Cacheable(value = "product-cache")
    public Product getProduct(@PathVariable("id") int id) {
        LOGGER.info("Finding all:"+id);
        return productRepository.findById(id).get();
    }

    @RequestMapping(value = "/products/", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @RequestMapping(value = "/products/", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    @CacheEvict(value = "product-cache")
    public void deleteProduct(@PathVariable("id") int id) {
         productRepository.deleteById(id);
    }
}






















