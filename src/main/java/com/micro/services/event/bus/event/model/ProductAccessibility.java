package com.micro.services.event.bus.event.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductAccessibility {

    private String productCode;
    private List<ProductAccessibilityDateRange> accessibleDateRanges;


    public ProductAccessibility() {}

    @JsonCreator
    public ProductAccessibility(@JsonProperty("productCode") final String productCode,
                               @JsonProperty("accessibleDateRanges") final List<ProductAccessibilityDateRange> accessibleDateRanges) {
        this.productCode = productCode;
        this.accessibleDateRanges = accessibleDateRanges;
    }

    public String getProductCode() {
        return this.productCode;
    }


    public List<ProductAccessibilityDateRange> gAccessibilityDateRanges() {
        return this.accessibleDateRanges;
    }

    public static class Builder {

        private final ProductAccessibility productAccessibility;

        public Builder() {
            productAccessibility = new ProductAccessibility();
        }

        public Builder withProductCode(final String productCode) {
            productAccessibility.productCode = productCode;
            return this;
        }

        public Builder withProductAccessibilityDataRanges(final List<ProductAccessibilityDateRange> accessibleDateRanges) {
            productAccessibility.accessibleDateRanges = accessibleDateRanges;
            return this;
        }

        public ProductAccessibility build() {
            return productAccessibility;
        }
    }
}