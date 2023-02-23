package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.model.Restaurant;
import com.work.restorauntautoservice.repository.RestaurantRepository;
import com.work.restorauntautoservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(String id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant refactorRestaurant(String id, Restaurant restaurant) {
        Restaurant restaurantToUpdate = restaurantRepository.getById(id);
        restaurantToUpdate.setName(restaurant.getName());
        restaurantToUpdate.setMenu(restaurant.getName());
        restaurantToUpdate.setSittingSpots(restaurant.getSittingSpots());
        return restaurantRepository.save(restaurantToUpdate);
    }
}
