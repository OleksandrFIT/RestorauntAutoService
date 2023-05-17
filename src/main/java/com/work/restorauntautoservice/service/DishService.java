package com.work.restorauntautoservice.service;

import com.work.restorauntautoservice.model.Dish;
import com.work.restorauntautoservice.model.DishRequest;

import java.util.List;

public interface DishService {
    DishRequest createDish(DishRequest dish);
    Dish createDishPostman(Dish dish);

    Dish editDish(String name, Dish dish);
    void deleteDish(String name);
    Dish findDish(String name);
    List<Dish> findAllDishes();
}
