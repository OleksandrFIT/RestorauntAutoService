package com.work.restorauntautoservice.repository;

import com.work.restorauntautoservice.model.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {
    Purchase getById(String id);
}
