package com.rica.handlers;

import com.rica.events.OrderCreatedEvent;
import com.rica.events.OrderConfirmedEvent;
import com.rica.events.OrderShippedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderEventHandler {
    private static final Logger log = LoggerFactory.getLogger(OrderEventHandler.class);

    @KafkaListener(topics = "order-events", groupId = "order-service-group")
    public void handleOrderEvents(String message) {
        try {
            log.info("Received order event: {}", message);
            // Parse and handle the event
            processOrderEvent(message);
        } catch (Exception e) {
            log.error("Error handling order event", e);
        }
    }

    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("Processing OrderCreatedEvent: orderId={}, customerId={}, amount={}",
                event.getOrderId(), event.getCustomerId(), event.getTotalAmount());
        // Trigger downstream processing: inventory reservation, payment processing, etc.
    }

    public void handleOrderConfirmed(OrderConfirmedEvent event) {
        log.info("Processing OrderConfirmedEvent: orderId={}, confirmation={}",
                event.getOrderId(), event.getConfirmationNumber());
        // Trigger fulfillment process
    }

    public void handleOrderShipped(OrderShippedEvent event) {
        log.info("Processing OrderShippedEvent: orderId={}, tracking={}, carrier={}",
                event.getOrderId(), event.getTrackingNumber(), event.getCarrier());
        // Send notification to customer
    }

    private void processOrderEvent(String message) {
        // Parse JSON and determine event type
        // Route to appropriate handler
    }
}
