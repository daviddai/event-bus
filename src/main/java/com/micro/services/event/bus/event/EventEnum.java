package com.micro.services.event.bus.event;

public enum EventEnum {
    
    PRODUCT_CREATED("supplierExchange", "supplier.createProduct", ProductCreated.class);

    private final String exchangeName;
    private final String routingKey;
    private final Class<?> eventClass;

    EventEnum(String exchangeName, String routingKey, Class<?> eventClass) {
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.eventClass = eventClass;
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