package com.rica.events;

public class OrderConfirmedEvent extends DomainEvent {
    private static final long serialVersionUID = 1L;

    private final String orderId;
    private final String confirmationNumber;

    public OrderConfirmedEvent(String orderId, String confirmationNumber) {
        super(orderId, "Order");
        this.orderId = orderId;
        this.confirmationNumber = confirmationNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    @Override
    public String toString() {
        return "OrderConfirmedEvent{" +
                "orderId='" + orderId + '\'' +
                ", confirmationNumber='" + confirmationNumber + '\'' +
                ", " + super.toString() +
                '}';
    }
}
