package com.rica.handlers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class NotificationEventHandler {
    private static final Logger log = LoggerFactory.getLogger(NotificationEventHandler.class);

    @KafkaListener(topics = "order-events", groupId = "notification-service-group")
    public void handleOrderNotifications(String message) {
        try {
            log.info("Notification service received event: {}", message);
            // Send emails, push notifications, etc.
            sendNotification(message);
        } catch (Exception e) {
            log.error("Error handling notification event", e);
        }
    }

    private void sendNotification(String message) {
        log.info("Sending notification for event: {}", message);
        // Implementation: integrate with email/SMS service
    }
}
