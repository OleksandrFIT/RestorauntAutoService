package com.work.restorauntautoservice.repository;

import com.work.restorauntautoservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product getById(String id);
}
