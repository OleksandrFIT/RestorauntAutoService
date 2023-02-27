package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.exception.NoSuchPurchaseException;
import com.work.restorauntautoservice.model.Purchase;
import com.work.restorauntautoservice.repository.PurchaseRepository;
import com.work.restorauntautoservice.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductServiceImpl productService;

    @Override
    public Purchase createPurchase(Purchase purchase) {
        productService.saveProductAfterPurchase(purchase);
        return purchaseRepository.save(purchase);
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
