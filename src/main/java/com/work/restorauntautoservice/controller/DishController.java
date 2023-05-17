package com.work.restorauntautoservice.controller;

import com.work.restorauntautoservice.model.Dish;
import com.work.restorauntautoservice.model.DishRequest;
import com.work.restorauntautoservice.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @GetMapping("/dish-find/{id}")
    Dish findDishById(@PathVariable String id) {
        return dishService.findDish(id);
    }

    @GetMapping("/dish-findAll")
    List<Dish> findAllDishes() {
        return dishService.findAllDishes();
    }

    @PostMapping("/dish-create")
    @ResponseStatus(HttpStatus.CREATED)
    DishRequest createDish(@RequestBody DishRequest dish) {
        return dishService.createDish(dish);
    }

    @PostMapping("/dish-create-postman")
    @ResponseStatus(HttpStatus.CREATED)
    Dish createDishPostman(@RequestBody Dish dish) {
        return dishService.createDishPostman(dish);
    }

    @PutMapping("/dish-edit/{name}")
    Dish editDish(@PathVariable String name, @RequestBody Dish dish) {
        return dishService.editDish(name, dish);
    }

    @DeleteMapping("/dish-delete/{name}")
    void deleteDish(@PathVariable String name) {
       dishService.deleteDish(name);
    }

}
