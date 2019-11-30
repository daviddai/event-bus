package com.micro.services.event.bus.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, 
    include = JsonTypeInfo.As.PROPERTY, 
    property = "type"
)
@JsonSubTypes({ 
    @Type(value = ProductCreated.class, name = "PRODUCT_CREATED"),
    @Type(value = ProductAvailabilityUpdated.class, name = "PRODUCT_AVAIL_UPDATED")
})
public abstract class Event {}