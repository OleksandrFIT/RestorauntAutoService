package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.analysisAl.ABCanalysisServiceImpl;
import com.work.restorauntautoservice.analysisAl.CountRestAlgorithmServiceImpl;
import com.work.restorauntautoservice.model.Order;
import com.work.restorauntautoservice.model.Product;
import com.work.restorauntautoservice.repository.OrderRepository;
import com.work.restorauntautoservice.repository.ProductRepository;
import com.work.restorauntautoservice.service.OrderService;
import kotlin.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    CountRestAlgorithmServiceImpl countRestAlgorithmService;

    @Override
    public Order createOrder(Order order) {
        Order newOrder = new Order(
                order.getId(),
                order.getName(),
                order.getCode(),
                order.getPrice(),
                countRestAlgorithmService.countRestProducts(),
                order.getQuantity()
        );
        return orderRepository.save(newOrder);
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order refactorOrder(String id, Order order) {
        Order foundOrder = orderRepository.getById(id);
        foundOrder.setName(order.getName());
        foundOrder.setCode(order.getCode());
        foundOrder.setPrice(order.getPrice());
        foundOrder.setQuantity(order.getQuantity());
        return orderRepository.save(foundOrder);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

}
