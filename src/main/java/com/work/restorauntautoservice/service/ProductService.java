package com.work.restorauntautoservice.service;

import com.work.restorauntautoservice.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    Product editProduct(String id, Product product);
    void deleteProduct(String id);
    Optional<Product> findProduct(String id);
    List<Product> findAllProducts();
}
