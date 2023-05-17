package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.exception.NoSuchPurchaseException;
import com.work.restorauntautoservice.model.Dish;
import com.work.restorauntautoservice.model.DishPair;
import com.work.restorauntautoservice.model.Purchase;
import com.work.restorauntautoservice.model.PurchasePostRequest;
import com.work.restorauntautoservice.repository.PurchaseRepository;
import com.work.restorauntautoservice.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private DishServiceImpl dishService;

    @Override
    public PurchasePostRequest createPurchase(PurchasePostRequest purchaseRequest) {
        List<DishPair> dishPairList = new LinkedList<>();
        List<PurchasePostRequest.NestedDish> purchasePostRequestReturnList = new LinkedList<>();

        List<PurchasePostRequest.NestedDish> nestedDishList = purchaseRequest.getDishList();
        if (nestedDishList != null) {
            nestedDishList.forEach(nestedDish -> {
                DishPair dishPair = new DishPair();
                dishPair.setDish(dishService.findDish(nestedDish.name));
                dishPair.setQuantity(nestedDish.count);
                dishPairList.add(dishPair);

                purchasePostRequestReturnList.add(
                        new PurchasePostRequest.NestedDish(
                                dishPair.getDish().getName(),
                                dishPair.getQuantity()
                        )
                );
            });
        }

        Purchase purchase = new Purchase();
        purchase.setPrice(purchaseRequest.getPrice());
        purchase.setWaitressName(purchaseRequest.getWaitressName());
        purchase.setDishList(dishPairList);

        // виклик аглгоритму оновлення нової наявної к-ті використаних для замовлення продуктів в бд
        productService.saveProductAfterPurchase(purchase);

        Purchase savedPurchase = purchaseRepository.save(purchase);

        PurchasePostRequest purchasePostRequestForResponse = new PurchasePostRequest();
        purchasePostRequestForResponse.setWaitressName(savedPurchase.getWaitressName());
        purchasePostRequestForResponse.setPrice(savedPurchase.getPrice());
        purchasePostRequestForResponse.setDishList(purchasePostRequestReturnList);

        return purchasePostRequestForResponse;

    }

    @Override
    public void deletePurchase(String id) {
        purchaseRepository.deleteById(id);
    }

    @Override
    public Purchase findPurchase(String id) {
        if (purchaseRepository.findById(id).isPresent()) {
            return purchaseRepository.getById(id);
        } else {
            throw new NoSuchPurchaseException("No such purchase exception");
        }
    }
}
