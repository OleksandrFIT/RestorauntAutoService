package com.work.restorauntautoservice.controller;

import com.work.restorauntautoservice.model.Purchase;
import com.work.restorauntautoservice.model.PurchasePostRequest;
import com.work.restorauntautoservice.service.implementation.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseServiceImpl purchaseService;

    @PostMapping(path = "/purchase-create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<PurchasePostRequest> createPurchase(@RequestBody PurchasePostRequest purchase) {
        PurchasePostRequest response = purchaseService.createPurchase(purchase);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/purchase-delete/{id}")
    public void editPurchase(@PathVariable String id) {
        purchaseService.deletePurchase(id);
    }

    @GetMapping("/purchase-find/{id}")
    public Purchase findPurchase(@PathVariable String id) {
        return purchaseService.findPurchase(id);
    }
}
