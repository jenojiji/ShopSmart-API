package com.personal.shop_smart.controller.user;

import com.personal.shop_smart.entity.Order;
import com.personal.shop_smart.repository.OrderRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final OrderRepository orderRepository;

    @PostMapping("/verification")
    public ResponseEntity<String> handlePaymentWebhook(HttpServletRequest request, @RequestBody Map<String, Object> webhookData) {
        try {
            // to-do : signature verification
            String event = (String) webhookData.get("event");
            if ("order.paid".equals(event)) {
                Map<String, Object> payload = (Map<String, Object>) webhookData.get("payload");
                Map<String, Object> payment = (Map<String, Object>) payload.get("payment");
                Map<String, Object> paymentEntity = (Map<String, Object>) payment.get("entity");

                String paymentId = (String) paymentEntity.get("id");
                String orderId = (String) paymentEntity.get("order_id");
                Order order = orderRepository.findByRazorpayOrderId(orderId);
                if (order != null) {
                    order.setPaymentId(paymentId);
                    order.setPaymentStatus("PAID");
                    orderRepository.save(order);
                }
            }
            return ResponseEntity.ok("Webhook processed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Webhook processing failed");
        }
    }
}
