package com.jeno.ecommerce_backend_api.controller;

import com.jeno.ecommerce_backend_api.dto.order.OrderRequestDto;
import com.jeno.ecommerce_backend_api.entity.Order;
import com.jeno.ecommerce_backend_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    //Get an order by orderID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    //create new order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto orderRequest) {
        Order newOrder = orderService.createOrder(orderRequest.getUserId(), orderRequest.getProductIds(), orderRequest.getTotalAmount(), orderRequest.getDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    //Get all orders of a user by userID
    @GetMapping("/user/{id}")
    public List<Order> getAllOrderByUserId(@PathVariable Long id) {
        return orderService.getUserOrders(id);
    }

    //Delete an order
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        try {
            // Calling the service layer to delete the order
            orderService.deleteOrder(id);
            // Return a success message if deletion is successful
            return ResponseEntity.ok("Order deleted successfully");
        } catch (RuntimeException e) {
            // Return 404 Not Found if the order is not found or other issues arise
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
