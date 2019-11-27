package com.micro.services.event.bus.event.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductContent {

    private String productCode;
    private String productDescription;

    @JsonCreator
    public ProductContent(@JsonProperty("productCode") String productCode,
                          @JsonProperty("productDescription") String productDescription) {
        this.productCode = productCode;
        this.productDescription = productDescription;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductDescription() {
        return productDescription;
    }
}
