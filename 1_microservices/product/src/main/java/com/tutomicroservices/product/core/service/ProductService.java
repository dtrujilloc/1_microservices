package com.tutomicroservices.product.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {

    public String getAllProducts() {
        log.info(">>> Start method getAllUsers");
        String result = "Se esta consultando la informacion de todos los productos";
        log.info("<<< End method getAllUsers");
        return result;
    }

    public String getProductsById(int productId) {
        log.info(">>> Start method getProductsById");
        String result = String.format("Se esta consultando la informacion del producto con el id:%d", productId);
        log.info("<<< End method getProductsById");
        return result;
    }

    public String getProductsByUserId(int userId) {
        log.info(">>> Start method getProductsByUserId");
        String result = String.format("Se esta consultando los PRODUCTOS que pertenecen al usuario con el id:%d", userId);
        log.info("<<< End method getProductsByUserId");
        return result;
    }
}
