package com.jeno.ecommerce_backend_api.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @PostMapping("/verification")
    public ResponseEntity<String> handlePaymentWebhook(@RequestBody Map<String, Object> webhookData) {
        System.out.println("*****************************************");
        return null;
    }

}
