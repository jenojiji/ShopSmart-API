package com.jeno.ecommerce_backend_api.repository;

import com.jeno.ecommerce_backend_api.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}
