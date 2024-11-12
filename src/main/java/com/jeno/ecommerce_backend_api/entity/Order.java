package com.jeno.ecommerce_backend_api.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String razorpayOrderId;
    private String orderStatus;
    private String paymentId;
    private String paymentStatus;

    @ManyToMany
    @JoinTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    public Order() {
    }

    public Order(User user, List<Product> products, Double totalAmount, LocalDate date) {
        this.user = user;
        this.products = products;
        this.date = date;
        this.totalAmount = totalAmount;
    }


    public Order(LocalDate date, Double totalAmount, User user, String razorpayOrderId,
                 String orderStatus, String paymentId, String paymentStatus,
                 List<Product> products) {
        this.date = date;
        this.totalAmount = totalAmount;
        this.user = user;
        this.razorpayOrderId = razorpayOrderId;
        this.orderStatus = orderStatus;
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getOrderDate() {
        return date;
    }

    public void setOrderDate(LocalDate date) {
        this.date = date;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }
}
