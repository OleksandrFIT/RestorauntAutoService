package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.analysisAl.ABCanalysisServiceImpl;
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
    private ProductRepository productRepository;
    @Autowired
    ABCanalysisServiceImpl abCanalysisService;

    @Override
    public Order createOrder(Order order) {
        List<Product> allProducts = productRepository.findAll();
        Triple<List<Product>, List<Product>, List<Product>> ABCList = abCanalysisService.abcAnalysis(allProducts, 0.8, 0.95);
        Order newOrder = new Order(
                order.getId(),
                order.getName(),
                order.getCode(),
                order.getPrice(),
                ABCList.component1(),
                order.getQuantity()
        );
        return createOrder(newOrder);
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
