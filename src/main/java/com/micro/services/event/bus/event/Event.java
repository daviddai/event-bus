package com.micro.services.event.bus.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "eventType", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ProductCreated.class, name = "PRODUCT_CREATED")
})
public interface Event {}