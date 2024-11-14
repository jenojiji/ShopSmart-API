package com.personal.shop_smart.repository;

import com.personal.shop_smart.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long id);
    Order findByRazorpayOrderId(String razorpayOrderId);
}
