package com.micro.services.event.bus.event.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductAvailability {

    private String productCode;
    private List<Date> availabilities;
    private List<Date> unavailabilities;


    public ProductAvailability() {}

    @JsonCreator
    public ProductAvailability(@JsonProperty("productCode") String productCode,
                               @JsonProperty("availabilities") List<Date> availabilities, 
                               @JsonProperty("unavailabilities") List<Date> unavailabilities) {
        this.productCode = productCode;
        this.availabilities = availabilities;
        this.unavailabilities = unavailabilities;
    }

    public String getProductCode() {
        return this.productCode;
    }


    public List<Date> getAvailabilities() {
        return this.availabilities;
    }


    public List<Date> getUnavailabilities() {
        return this.unavailabilities;
    }

    public static class Builder {

        private ProductAvailability productAvailability;

        public Builder() {
            productAvailability = new ProductAvailability();
        }

        public Builder withProductCode(String productCode) {
            productAvailability.productCode = productCode;
            return this;
        }

        public Builder withAvailabilities(List<Date> availabilities) {
            productAvailability.availabilities = availabilities;
            return this;
        }

        public Builder withUnavailabilities(List<Date> unavailabilities) {
            productAvailability.unavailabilities = unavailabilities;
            return this;
        }

        public ProductAvailability build() {
            return productAvailability;
        }
    }
}