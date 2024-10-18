package com.jeno.ecommerce_backend_api.repository;

import com.jeno.ecommerce_backend_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
