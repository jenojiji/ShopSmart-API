package com.jeno.ecommerce_backend_api.service;

import com.jeno.ecommerce_backend_api.entity.Order;
import com.jeno.ecommerce_backend_api.entity.Product;
import com.jeno.ecommerce_backend_api.entity.User;
import com.jeno.ecommerce_backend_api.repository.OrderRepository;
import com.jeno.ecommerce_backend_api.repository.ProductRepository;
import com.jeno.ecommerce_backend_api.repository.UserRepository;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    //    @Value("${rzp_key_id}")
    private static final String keyId = "rzp_test_aUIHKeoVctKWq2";

    //    @Value("${rzp_key_secret}")
    private static final String secret = "KULZQEvuzaBnPpDE11rQJKVi";

    static RazorpayClient razorpayClient;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) throws RazorpayException {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        razorpayClient = new RazorpayClient(keyId, secret);
    }

    //create new order
    public Order createOrder(Long userId, List<Long> productIds) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Product> products = productRepository.findAllById(productIds);
        double totalAmount = 0.0;
        for (Product product : products) {
            totalAmount = totalAmount + product.getPrice();
        }
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", totalAmount);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "order_receipt_11");
        orderRequest.put("payment_capture", 1);
        System.out.println(orderRequest);
        com.razorpay.Order createdOrder;
        try {
            createdOrder = razorpayClient.orders.create(orderRequest);
        } catch (RazorpayException e) {
            throw new RuntimeException(e.getMessage());
        }
        Order order = new Order(LocalDate.now(), totalAmount, user, createdOrder.get("id"), createdOrder.get("status"), null, "pending", products);
        return orderRepository.save(order);
    }

    //get all orders placed by a user
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    //get order details by order id
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    //get all orders in the system
    public Page<Order> getAllOrders(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return orderRepository.findAll(pageable);
    }

    //update an order
    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        order.setTotalAmount(updatedOrder.getTotalAmount());
        order.setOrderDate(updatedOrder.getOrderDate());

        return orderRepository.save(order);
    }

    //delete  an order
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        orderRepository.delete(order);

    }


}
