package com.jeno.ecommerce_backend_api.service;

import com.jeno.ecommerce_backend_api.entity.Order;
import com.jeno.ecommerce_backend_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //create new order
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    //get all orders placed by a user
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    //get order details by order id
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    //get all orders in the system
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //update an order
    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        order.setTotalAmount(updatedOrder.getTotalAmount());
        order.setOrderDate(updatedOrder.getOrderDate());

        return orderRepository.save(order);
    }

    //delete  an order
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        orderRepository.delete(order);

    }




}
