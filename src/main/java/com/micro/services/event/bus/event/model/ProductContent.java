package com.micro.services.event.bus.event.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductContent {

    private String productCode;
    private String productName;
    private String productDescription;

    public ProductContent() {}

    @JsonCreator
    public ProductContent(@JsonProperty("productCode") String productCode,
                          @JsonProperty("productName") String productName,
                          @JsonProperty("productDescription") String productDescription) {
        this.productCode = productCode;
        this.productName = productName;
        this.productDescription = productDescription;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public static class Builder {

        private ProductContent productContent;

        public Builder() {
            productContent = new ProductContent();
        }

        public Builder withProductCode(String productCode) {
            productContent.productCode = productCode;
            return this;
        }

        public Builder withProductName(String productName) {
            productContent.productName = productName;
            return this;
        }

        public Builder withProductDescription(String productDescription) {
            productContent.productDescription = productDescription;
            return this;
        }

        public ProductContent build() {
            return productContent;
        }

    }
}
