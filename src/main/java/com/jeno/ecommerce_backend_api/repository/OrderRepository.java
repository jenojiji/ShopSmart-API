package com.jeno.ecommerce_backend_api.repository;

import com.jeno.ecommerce_backend_api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long id);
}
