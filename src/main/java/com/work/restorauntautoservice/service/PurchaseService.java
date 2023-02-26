package com.work.restorauntautoservice.service;

import com.work.restorauntautoservice.model.Purchase;

public interface PurchaseService {
    Purchase createPurchase(Purchase purchase);
    void deletePurchase(String id);
    Purchase findPurchase(String id);
}
