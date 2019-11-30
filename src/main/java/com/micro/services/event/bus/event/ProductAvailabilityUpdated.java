package com.micro.services.event.bus.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micro.services.event.bus.event.model.ProductAvailability;

public class ProductAvailabilityUpdated extends Event {

    private ProductAvailability productAvailability;

    @JsonCreator
    public ProductAvailabilityUpdated(@JsonProperty("productAvailability") ProductAvailability productAvailability) {
        this.productAvailability = productAvailability;
    }

    public ProductAvailability productAvailability() {
        return productAvailability;
    }

}