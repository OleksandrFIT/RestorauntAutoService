package com.work.restorauntautoservice.service;

import com.work.restorauntautoservice.model.Purchase;
import com.work.restorauntautoservice.model.PurchasePostRequest;

public interface PurchaseService {
    PurchasePostRequest createPurchase(PurchasePostRequest purchase);

    void deletePurchase(String id);

    Purchase findPurchase(String id);
}
