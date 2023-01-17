package com.larrex.productapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.larrex.productapi.model.Product;
import com.larrex.productapi.repos.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductRestControllerMvcTest {

    public static final int PRODUCT_ID = 1;
    public static final int PRODUCT_PRICE = 2000;
    public static final String PRODUCT_NAME = "Iphone";
    public static final String PRODUCT_DESCRIPTION = "Its from apple";
    public static final String CONTEXT_PATH = "/productapi";
    public static final String PRODUCTAPI_PRODUCTS = "/productapi/products/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void tesFindAllProducts() throws Exception {

        Product product = getProduct();

        List<Product> products = Arrays.asList(product);

        when(productRepository.findAll()).thenReturn(products);

        ObjectWriter objectWriter =  new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(get(PRODUCTAPI_PRODUCTS)
                .contextPath(CONTEXT_PATH))
                .andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(products)));

    }

    @Test
    public void createProduct() throws Exception {

        Product product = getProduct();
        when(productRepository.save(any())).thenReturn(product);
        ObjectWriter objectWriter =  new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(PRODUCTAPI_PRODUCTS)
                .contextPath(CONTEXT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(product)));

    }

    @Test
    public void updateProduct() throws Exception {

        Product product = getProduct();
        product.setPrice(23333);
        when(productRepository.save(any())).thenReturn(product);
        ObjectWriter objectWriter =  new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(PRODUCTAPI_PRODUCTS)
                .contextPath(CONTEXT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(product)));

    }

    @Test
    public void deleteProduct() throws Exception {
        doNothing().when(productRepository).deleteById(PRODUCT_ID);
        mockMvc.perform(delete(PRODUCTAPI_PRODUCTS+PRODUCT_ID ).contextPath(CONTEXT_PATH)).andExpect(status().isOk());
    }
    private static Product getProduct() {
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setPrice(PRODUCT_PRICE);
        product.setName(PRODUCT_NAME);
        product.setDescription(PRODUCT_DESCRIPTION);
        return product;
    }

}
