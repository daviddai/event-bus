package com.micro.services.event.bus.event.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductAvailability {

    private String productCode;
    private List<ProductAvailabilityRule> availabilityRules;
    private List<ProductAvailabilityRule> unavailabilityRules;


    public ProductAvailability() {}

    @JsonCreator
    public ProductAvailability(@JsonProperty("productCode") final String productCode,
                               @JsonProperty("availabilityRules") final List<ProductAvailabilityRule> availabilityRules, 
                               @JsonProperty("unavailabilityRules") final List<ProductAvailabilityRule> unavailabilityRules) {
        this.productCode = productCode;
        this.availabilityRules = availabilityRules;
        this.unavailabilityRules = unavailabilityRules;
    }

    public String getProductCode() {
        return this.productCode;
    }


    public List<ProductAvailabilityRule> getAvailabilityRules() {
        return this.availabilityRules;
    }


    public List<ProductAvailabilityRule> getUnavailabilityRules() {
        return this.unavailabilityRules;
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

        public Builder withAvailabilityRules(final List<ProductAvailabilityRule> availabilities) {
            productAvailability.availabilityRules = availabilities;
            return this;
        }

        public Builder withUnavailabilityRules(final List<ProductAvailabilityRule> unavailabilities) {
            productAvailability.unavailabilityRules = unavailabilities;
            return this;
        }

        public ProductAvailability build() {
            return productAvailability;
        }
    }
}