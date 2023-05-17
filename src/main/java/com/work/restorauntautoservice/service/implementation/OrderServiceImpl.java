package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.analysisAl.CountRestAlgorithmServiceImpl;
import com.work.restorauntautoservice.model.Order;
import com.work.restorauntautoservice.model.Product;
import com.work.restorauntautoservice.model.ProductPair;
import com.work.restorauntautoservice.repository.OrderRepository;
import com.work.restorauntautoservice.service.OrderService;
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
    public Order createOrder() {
        List<Product> productList = countRestAlgorithmService.countRestProducts();
        Order newOrder = new Order(
                countRestAlgorithmService.generateUniqueCode(),
                countRestAlgorithmService.countSumOfRestProducts(productList),
                productList
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
        foundOrder.setCode(order.getCode());
        foundOrder.setPrice(order.getPrice());
        foundOrder.setProductList(order.getProductList());
        return orderRepository.save(foundOrder);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

}
