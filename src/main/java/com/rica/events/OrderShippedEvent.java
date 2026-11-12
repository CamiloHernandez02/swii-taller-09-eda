package com.rica.events;

public class OrderShippedEvent extends DomainEvent {
    private static final long serialVersionUID = 1L;

    private final String orderId;
    private final String trackingNumber;
    private final String carrier;

    public OrderShippedEvent(String orderId, String trackingNumber, String carrier) {
        super(orderId, "Order");
        this.orderId = orderId;
        this.trackingNumber = trackingNumber;
        this.carrier = carrier;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getCarrier() {
        return carrier;
    }

    @Override
    public String toString() {
        return "OrderShippedEvent{" +
                "orderId='" + orderId + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", carrier='" + carrier + '\'' +
                ", " + super.toString() +
                '}';
    }
}
