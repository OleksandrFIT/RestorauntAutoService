package com.work.restorauntautoservice.service;

import com.work.restorauntautoservice.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);
    void deleteOrder(String id);
    Order refactorOrder(String id, Order order);
    List<Order> findAllOrders();

}
