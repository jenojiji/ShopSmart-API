package com.jeno.ecommerce_backend_api.service;

import com.jeno.ecommerce_backend_api.entity.Order;
import com.jeno.ecommerce_backend_api.entity.Product;
import com.jeno.ecommerce_backend_api.entity.User;
import com.jeno.ecommerce_backend_api.repository.OrderRepository;
import com.jeno.ecommerce_backend_api.repository.ProductRepository;
import com.jeno.ecommerce_backend_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    //create new order
    public Order createOrder(Long userId, List<Long> productIds) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        List<Product> products = productRepository.findAllById(productIds);
        double totalAmount = 0.0;
        for (Product product : products) {
            totalAmount = totalAmount + product.getPrice();
            System.out.println("Product ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice());
        }

        Order order = new Order(user, products, totalAmount, LocalDate.now());
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
