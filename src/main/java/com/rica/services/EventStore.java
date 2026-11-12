package com.rica.services;

import com.rica.events.DomainEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventStore {
    private static final Logger log = LoggerFactory.getLogger(EventStore.class);

    private final Map<String, List<String>> eventsByAggregate = new HashMap<>();
    private final ObjectMapper objectMapper;

    public EventStore(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void store(DomainEvent event) {
        try {
            String aggregateId = event.getAggregateId();
            String eventJson = objectMapper.writeValueAsString(event);
            
            eventsByAggregate.computeIfAbsent(aggregateId, k -> new ArrayList<>())
                    .add(eventJson);
            
            log.info("Event stored: {} for aggregate: {}", event.getEventType(), aggregateId);
        } catch (Exception e) {
            log.error("Failed to store event", e);
            throw new RuntimeException("Failed to store event", e);
        }
    }

    public List<DomainEvent> getEventsForAggregate(String aggregateId) {
        return eventsByAggregate.getOrDefault(aggregateId, Collections.emptyList())
                .stream()
                .map(json -> {
                    try {
                        return objectMapper.readValue(json, DomainEvent.class);
                    } catch (Exception e) {
                        log.error("Failed to deserialize event", e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Page<DomainEvent> getEventsForAggregate(String aggregateId, Pageable pageable) {
        List<DomainEvent> allEvents = getEventsForAggregate(aggregateId);
        int fromIndex = (int) pageable.getOffset();
        int toIndex = Math.min(fromIndex + pageable.getPageSize(), allEvents.size());
        
        List<DomainEvent> pageContent = allEvents.subList(fromIndex, toIndex);
        return new PageImpl<>(pageContent, pageable, allEvents.size());
    }

    public List<DomainEvent> getAllEvents() {
        return eventsByAggregate.values()
                .stream()
                .flatMap(List::stream)
                .map(json -> {
                    try {
                        return objectMapper.readValue(json, DomainEvent.class);
                    } catch (Exception e) {
                        log.error("Failed to deserialize event", e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
