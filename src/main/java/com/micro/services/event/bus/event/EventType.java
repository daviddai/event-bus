package com.micro.services.event.bus.event;

import java.util.EnumSet;

public enum EventType {
    
    PRODUCT_CREATED("supplierExchange", "supplier.createProduct", ProductCreated.class),
    PRODUCT_AVAIL_UPDATED("supplierExchange", "supplier.updateAvailabilities", AvailabilitiesUpdated.class);

    private final String exchangeName;
    private final String routingKey;
    private final Class<?> eventClass;

    EventType(String exchangeName, String routingKey, Class<?> eventClass) {
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.eventClass = eventClass;
    }

    public static EventType getEventType(Event event) {
        return EnumSet.allOf(EventType.class)
            .stream()
            .filter(eventType -> eventType.getEventClass() == event.getClass())
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Event type does not exist"));
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public Class<?> getEventClass() {
        return eventClass;
    }

}