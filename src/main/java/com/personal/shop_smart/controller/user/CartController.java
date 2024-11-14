package com.personal.shop_smart.controller.user;

import com.personal.shop_smart.entity.Cart;
import com.personal.shop_smart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> getCart(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.addProductToCart(userId, productId, quantity));
    }

    //Delete a product from cart

    @DeleteMapping("/remove")
    public ResponseEntity<Cart> removeFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        return ResponseEntity.ok(cartService.removeProductFromCart(userId, productId));
    }


}
