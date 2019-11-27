package com.micro.services.event.bus.event;

public enum EventEnum {
    
    PRODUCT_CREATED("supplierExchange", "supplier.createProduct");

    private final String exchangeName;
    private final String routingKey;

    EventEnum(String exchangeName, String routingKey) {
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

}