package com.rica.events;

import java.math.BigDecimal;

public class OrderCreatedEvent extends DomainEvent {
    private static final long serialVersionUID = 1L;

    private final String orderId;
    private final String customerId;
    private final BigDecimal totalAmount;
    private final String status;

    public OrderCreatedEvent(String orderId, String customerId, BigDecimal totalAmount, String status) {
        super(orderId, "Order");
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", " + super.toString() +
                '}';
    }
}
