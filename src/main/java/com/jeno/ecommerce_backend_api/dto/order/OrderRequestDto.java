package com.jeno.ecommerce_backend_api.dto.order;

import java.time.LocalDate;
import java.util.List;

public class OrderRequestDto {
    private Long userId;
    private List<Long> productIds;
    private Double totalAmount;
    private LocalDate date;

    public OrderRequestDto() {
    }

    public OrderRequestDto(Long userId, List<Long> productIds, Double totalAmount,LocalDate date) {
        this.userId = userId;
        this.productIds = productIds;
        this.totalAmount = totalAmount;
        this.date = LocalDate.now();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
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
}
