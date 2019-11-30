package com.micro.services.event.bus.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micro.services.event.bus.event.model.ProductAvailability;

public class AvailabilitiesUpdated extends Event {

    private ProductAvailability productAvailability;

    @JsonCreator
    public AvailabilitiesUpdated(@JsonProperty("productAvailability") ProductAvailability productAvailability) {
        this.productAvailability = productAvailability;
    }

    public ProductAvailability productAvailability() {
        return productAvailability;
    }

}