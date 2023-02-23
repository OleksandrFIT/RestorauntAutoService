package com.work.restorauntautoservice.service;

import com.work.restorauntautoservice.model.Restaurant;

public interface RestaurantService {

    Restaurant createRestaurant(Restaurant restaurant);
    void deleteRestaurant(String id);
    Restaurant refactorRestaurant(String id, Restaurant restaurant);

}
