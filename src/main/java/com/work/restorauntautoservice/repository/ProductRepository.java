package com.work.restorauntautoservice.repository;

import com.work.restorauntautoservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product getById(String id);
    Optional<Product> getProductByName(String name);
}
