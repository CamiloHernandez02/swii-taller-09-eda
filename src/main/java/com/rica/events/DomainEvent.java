package com.rica.events;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public abstract class DomainEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String eventId;
    private final LocalDateTime occurredAt;
    private final String eventType;
    private final String aggregateId;
    private final String aggregateType;

    protected DomainEvent(String aggregateId, String aggregateType) {
        this.eventId = UUID.randomUUID().toString();
        this.occurredAt = LocalDateTime.now();
        this.eventType = this.getClass().getSimpleName();
        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType;
    }

    public String getEventId() {
        return eventId;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    public String getEventType() {
        return eventType;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    @Override
    public String toString() {
        return "DomainEvent{" +
                "eventId='" + eventId + '\'' +
                ", occurredAt=" + occurredAt +
                ", eventType='" + eventType + '\'' +
                ", aggregateId='" + aggregateId + '\'' +
                ", aggregateType='" + aggregateType + '\'' +
                '}';
    }
}
