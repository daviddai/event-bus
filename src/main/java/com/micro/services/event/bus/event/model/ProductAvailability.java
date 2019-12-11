package com.micro.services.event.bus.event.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductAvailability {

    private String productCode;
    private List<ProductAvailablePeriod> productAvailablePeriods;


    public ProductAvailability() {}

    @JsonCreator
    public ProductAvailability(@JsonProperty("productCode") final String productCode,
                               @JsonProperty("productAvailablePeriods") final List<ProductAvailablePeriod> productAvailablePeriods) {
        this.productCode = productCode;
        this.productAvailablePeriods = productAvailablePeriods;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public List<ProductAvailablePeriod> getProductAvailablePeriods() {
        return productAvailablePeriods;
    }

    public static class Builder {

        private final ProductAvailability productAvailability;

        public Builder() {
            productAvailability = new ProductAvailability();
        }

        public Builder withProductCode(final String productCode) {
            productAvailability.productCode = productCode;
            return this;
        }

        public Builder withProductAvailablePeriods(final List<ProductAvailablePeriod> productAvailablePeriods) {
            productAvailability.productAvailablePeriods = productAvailablePeriods;
            return this;
        }

        public ProductAvailability build() {
            return productAvailability;
        }
    }
}