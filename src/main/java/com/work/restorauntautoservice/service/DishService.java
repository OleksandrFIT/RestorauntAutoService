package com.work.restorauntautoservice.service;

import com.work.restorauntautoservice.model.Dish;

import java.util.List;

public interface DishService {
    Dish createDish(Dish dish);
    Dish editDish(String name, Dish dish);
    void deleteDish(String name);
    Dish findDish(String name);
    List<Dish> findAllDishes();
}
