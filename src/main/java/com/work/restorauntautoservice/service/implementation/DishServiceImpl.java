package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.exception.NoSuchDishException;
import com.work.restorauntautoservice.model.Dish;
import com.work.restorauntautoservice.model.DishRequest;
import com.work.restorauntautoservice.model.Product;
import com.work.restorauntautoservice.model.ProductPair;
import com.work.restorauntautoservice.repository.DishRepository;
import com.work.restorauntautoservice.service.DishService;
import com.work.restorauntautoservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private ProductService productService;
    @Override
    public DishRequest createDish(DishRequest dishRequest) {

        List<ProductPair> dishProductPairList = new LinkedList<>();
        List<DishRequest.NestedProduct> productNameListToReturn = new LinkedList<>();

        for (DishRequest.NestedProduct nestedProduct : dishRequest.getProducts()) {
            Optional<Product> optProd = productService.findProductByName(nestedProduct.getName());
            if (optProd.isPresent()) {
                ProductPair productPair = new ProductPair();
                productPair.setProduct(optProd.get());
                productPair.setWeight(nestedProduct.nestedProdWeight);
                dishProductPairList.add(productPair);

                productNameListToReturn.add(new DishRequest.NestedProduct(
                        optProd.get().getName(),
                        nestedProduct.nestedProdWeight
                ));
            }
        }

        Dish dish = new Dish();
        dish.setName(dishRequest.getName());
        dish.setPrice(dishRequest.getPrice());
        dish.setWeight(dishRequest.getWeight());
        dish.setProductList(dishProductPairList);

        Dish savedDish = dishRepository.save(dish);

        return new DishRequest(
                savedDish.getName(),
                savedDish.getPrice(),
                savedDish.getWeight(),
                productNameListToReturn
        );

    }

    @Override
    public Dish createDishPostman(Dish dish) {
        dish.getProductList().forEach(item -> {
            Optional<Product> optProd = productService.findProductByName(item.getProduct().getName());
            if (optProd.isEmpty()) {
                productService.createProduct(item.getProduct());
            }
        });
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
