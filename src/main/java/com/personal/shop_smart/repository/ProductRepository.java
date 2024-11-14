package com.personal.shop_smart.repository;

import com.personal.shop_smart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
