package com.micro.services.event.bus.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micro.services.event.bus.event.model.ProductContent;

public class ProductCreated extends Event {

    private final ProductContent productContent;

    @JsonCreator
    public ProductCreated(@JsonProperty("productContent") ProductContent productContent) {
        this.productContent = productContent;
    }

    public ProductContent getProductContent() {
        return productContent;
    }
}
