package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.exception.NoSuchDishException;
import com.work.restorauntautoservice.model.Dish;
import com.work.restorauntautoservice.repository.DishRepository;
import com.work.restorauntautoservice.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishRepository dishRepository;
    @Override
    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public Dish editDish(String name, Dish dish) {
        Dish dishToUpdate = findDish(name);
        dishToUpdate.setName(dish.getName());
        dishToUpdate.setWeight(dish.getWeight());
        dishToUpdate.setPrice(dish.getPrice());
        dishToUpdate.setProductList(dish.getProductList());
        return dishRepository.save(dishToUpdate);
    }

    @Override
    public void deleteDish(String name) {
        dishRepository.delete(findDish(name));
    }

    @Override
    public Dish findDish(String name) {
        if (dishRepository.findByName(name) != null) {
            return dishRepository.findByName(name);
        } else {
            throw new NoSuchDishException("No such dish exception");
        }
    }

    @Override
    public List<Dish> findAllDishes() {
        return dishRepository.findAll();
    }
}
