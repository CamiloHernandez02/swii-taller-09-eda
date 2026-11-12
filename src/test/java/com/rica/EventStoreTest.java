package com.rica;

import com.rica.events.OrderCreatedEvent;
import com.rica.events.DomainEvent;
import com.rica.services.EventStore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventStoreTest {

    private EventStore eventStore;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        eventStore = new EventStore(objectMapper);
    }

    @Test
    public void testStoreAndRetrieveEvent() {
        OrderCreatedEvent event = new OrderCreatedEvent(
                "ORD-001",
                "CUST-001",
                new BigDecimal("99.99"),
                "CREATED"
        );

        eventStore.store(event);
        List<DomainEvent> events = eventStore.getEventsForAggregate("ORD-001");

        assertNotNull(events);
        assertEquals(1, events.size());
    }

    @Test
    public void testGetAllEvents() {
        OrderCreatedEvent event1 = new OrderCreatedEvent(
                "ORD-001",
                "CUST-001",
                new BigDecimal("99.99"),
                "CREATED"
        );
        OrderCreatedEvent event2 = new OrderCreatedEvent(
                "ORD-002",
                "CUST-002",
                new BigDecimal("149.99"),
                "CREATED"
        );

        eventStore.store(event1);
        eventStore.store(event2);

        List<DomainEvent> allEvents = eventStore.getAllEvents();
        assertEquals(2, allEvents.size());
    }

    @Test
    public void testEventEventualConsistency() {
        OrderCreatedEvent event = new OrderCreatedEvent(
                "ORD-003",
                "CUST-003",
                new BigDecimal("199.99"),
                "CREATED"
        );

        eventStore.store(event);
        
        List<DomainEvent> events = eventStore.getEventsForAggregate("ORD-003");
        assertTrue(events.stream().anyMatch(e -> e.getEventType().equals("OrderCreatedEvent")));
    }
}
