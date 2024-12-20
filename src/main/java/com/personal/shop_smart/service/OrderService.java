package com.personal.shop_smart.service;

import com.personal.shop_smart.entity.Order;
import com.personal.shop_smart.entity.Product;
import com.personal.shop_smart.entity.User;
import com.personal.shop_smart.repository.OrderRepository;
import com.personal.shop_smart.repository.ProductRepository;
import com.personal.shop_smart.repository.UserRepository;
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
        orderRequest.put("amount", totalAmount * 100);
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
        Order order = Order.builder()
                .date(LocalDate.now())
                .totalAmount(totalAmount)
                .user(user)
                .razorpayOrderId(createdOrder.get("id"))
                .orderStatus(createdOrder.get("status"))
                .paymentId(null)
                .paymentStatus("pending")
                .products(products)
                .build();

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
        order.setDate(updatedOrder.getDate());

        return orderRepository.save(order);
    }

    //delete  an order
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        orderRepository.delete(order);

    }
}
