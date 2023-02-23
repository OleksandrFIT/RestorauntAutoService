package com.work.restorauntautoservice.repository;

import com.work.restorauntautoservice.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    Restaurant getById(String id);
}
