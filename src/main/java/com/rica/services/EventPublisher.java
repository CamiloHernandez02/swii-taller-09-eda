package com.rica.services;

import com.rica.events.DomainEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EventPublisher {
    private static final Logger log = LoggerFactory.getLogger(EventPublisher.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public EventPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishEvent(DomainEvent event) {
        try {
            String topic = getTopicForEvent(event);
            String eventJson = objectMapper.writeValueAsString(event);
            
            kafkaTemplate.send(topic, event.getAggregateId(), eventJson)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            log.error("Error publishing event: {} to topic: {}", event.getEventType(), topic, ex);
                        } else {
                            log.info("Event published successfully: {} (ID: {}) to topic: {}",
                                    event.getEventType(), event.getEventId(), topic);
                        }
                    });
        } catch (Exception e) {
            log.error("Failed to serialize event: {}", event.getEventType(), e);
        }
    }

    private String getTopicForEvent(DomainEvent event) {
        return event.getAggregateType().toLowerCase() + "-events";
    }
}
