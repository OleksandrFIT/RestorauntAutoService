package com.work.restorauntautoservice.controller;

import com.work.restorauntautoservice.model.Order;
import com.work.restorauntautoservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order-create")
    @ResponseStatus(HttpStatus.CREATED)
    Order createOrder() {
        return orderService.createOrder();
    }

    @PutMapping("/order-edit/{id}")
    Order refactorOrder(@PathVariable String id, @RequestBody Order order) {
        return orderService.refactorOrder(id, order);
    }

    @DeleteMapping("/order-delete/{id}")
    void deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
    }
    @GetMapping("/order-findAll")
    List<Order> findAllOrders() {
        return orderService.findAllOrders();
    }
}
