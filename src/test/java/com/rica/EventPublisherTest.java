package com.rica;

import com.rica.events.OrderCreatedEvent;
import com.rica.services.EventPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class EventPublisherTest {

    private EventPublisher eventPublisher;
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        kafkaTemplate = mock(KafkaTemplate.class);
        objectMapper = new ObjectMapper();
        eventPublisher = new EventPublisher(kafkaTemplate, objectMapper);
    }

    @Test
    public void testPublishOrderCreatedEvent() {
        OrderCreatedEvent event = new OrderCreatedEvent(
                "ORD-001",
                "CUST-001",
                new BigDecimal("99.99"),
                "CREATED"
        );

        eventPublisher.publishEvent(event);

        verify(kafkaTemplate, timeout(1000)).send(anyString(), anyString(), anyString());
    }

    @Test
    public void testEventIdIsGenerated() {
        OrderCreatedEvent event = new OrderCreatedEvent(
                "ORD-002",
                "CUST-002",
                new BigDecimal("149.99"),
                "CREATED"
        );

        assert event.getEventId() != null;
        assert !event.getEventId().isEmpty();
    }
}
