package com.jeno.ecommerce_backend_api.repository;

import com.jeno.ecommerce_backend_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
