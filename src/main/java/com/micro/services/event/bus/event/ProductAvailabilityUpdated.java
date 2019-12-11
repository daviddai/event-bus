package com.micro.services.event.bus.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micro.services.event.bus.event.model.ProductAccessibility;

public class ProductAvailabilityUpdated extends Event {

    private ProductAccessibility productAccessibility;

    @JsonCreator
    public ProductAvailabilityUpdated(@JsonProperty("productAccessibility") ProductAccessibility productAccessibility) {
        this.productAccessibility = productAccessibility;
    }

    public ProductAccessibility getProductAccessibility() {
        return productAccessibility;
    }

}