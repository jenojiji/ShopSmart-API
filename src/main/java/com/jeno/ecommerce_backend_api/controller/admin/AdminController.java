package com.jeno.ecommerce_backend_api.controller.admin;

import com.jeno.ecommerce_backend_api.entity.Order;
import com.jeno.ecommerce_backend_api.entity.Product;
import com.jeno.ecommerce_backend_api.entity.User;
import com.jeno.ecommerce_backend_api.service.OrderService;
import com.jeno.ecommerce_backend_api.service.ProductService;
import com.jeno.ecommerce_backend_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    //get all users
    @GetMapping("/users")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        Page<User> userPage = userService.getAllUsers(pageNo, pageSize);
//        List<UserResponseDto> users = userPage.getContent().stream().map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getMobile(), user.getRole())).collect(Collectors.toList());
        return ResponseEntity.ok(userPage);
    }

    //Get all products
    @GetMapping("/products")
    public ResponseEntity<org.springframework.data.domain.Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        Page<Product> products = productService.getAllProducts(pageNo, pageSize);
        return ResponseEntity.of(Optional.ofNullable(products));
    }

    //Get a product by ID
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(product);
        }
    }

    //create new product
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(201).body(createdProduct);
    }

    //update a product by ID
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    //delete a product by its ID
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Get all orders
    @GetMapping("/orders")
    public ResponseEntity<Page<Order>> getAllOrders(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        Page<Order> orders = orderService.getAllOrders(pageNo, pageSize);
        return ResponseEntity.of(Optional.ofNullable(orders));
    }


}
