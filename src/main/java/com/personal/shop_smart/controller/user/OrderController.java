package com.personal.shop_smart.controller.user;

import com.personal.shop_smart.dto.order.OrderRequestDto;
import com.personal.shop_smart.entity.Order;
import com.personal.shop_smart.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //Get an order by orderID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    //create new order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto orderRequest) {
        Order newOrder = orderService.createOrder(orderRequest.getUserId(), orderRequest.getProductIds());
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
