package com.tutomicroservices.product.core.controller;

import com.tutomicroservices.product.core.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getAllProducts() {
        log.info(">>> Start endpoint getAllProducts");
        String result = productService.getAllProducts();
        log.info("<<< End endpoint getAllProducts");
        return result;
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") int productId) {
        log.info(">>> Start endpoint getProductById -> productId:{}", productId);
        String result = productService.getProductsById(productId);
        log.info("<<< End endpoint getProductById");
        return result;
    }

    @GetMapping("/users/{id}")
    public String getProductsByUserId(@PathVariable("id") int userId) {
        log.info(">>> Start endpoint getProductsByUserId");
        String result = productService.getProductsByUserId(userId);
        log.info("<<< End endpoint getProductsByUserId");
        return result;
    }
}
