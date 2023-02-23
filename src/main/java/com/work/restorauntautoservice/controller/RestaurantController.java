package com.work.restorauntautoservice.controller;

import com.work.restorauntautoservice.model.Product;
import com.work.restorauntautoservice.model.Restaurant;
import com.work.restorauntautoservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/restaurant-create")
    @ResponseStatus(HttpStatus.CREATED)
    Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }

    @PutMapping("/restaurant-edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    Restaurant editRestaurant(@PathVariable String id, @RequestBody Restaurant restaurant) {
        return restaurantService.refactorRestaurant(id, restaurant);
    }

    @DeleteMapping("/restaurant-delete/{id}")
    void deleteRestaurant(@PathVariable String id) {
        restaurantService.deleteRestaurant(id);
    }
}
