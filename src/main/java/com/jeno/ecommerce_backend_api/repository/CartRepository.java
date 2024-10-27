package com.jeno.ecommerce_backend_api.repository;

import com.jeno.ecommerce_backend_api.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);
}
