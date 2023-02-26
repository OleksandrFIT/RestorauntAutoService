package com.work.restorauntautoservice.repository;

import com.work.restorauntautoservice.model.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DishRepository extends MongoRepository<Dish, String> {
    Dish findByName(String name);
}
