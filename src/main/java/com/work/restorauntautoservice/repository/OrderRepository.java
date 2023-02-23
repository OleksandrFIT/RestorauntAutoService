package com.work.restorauntautoservice.repository;

import com.work.restorauntautoservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

    Order getById(String id);
}
