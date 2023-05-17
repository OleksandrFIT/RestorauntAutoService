package com.work.restorauntautoservice.service;

import com.work.restorauntautoservice.model.Product;
import com.work.restorauntautoservice.model.Purchase;
import kotlin.Triple;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    Product editProduct(String id, Product product);
    void deleteProduct(String id);
    void saveProductAfterPurchase(Purchase purchase);
    Optional<Product> findProduct(String id);
    Optional<Product> findProductByName(String name);
    List<Product> findAllProducts();
    List<Product> countRestProductsResult();
}
