package com.micro.services.event.bus.event.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ProductAvailabilityRule {
    private LocalDate startDate;
    private LocalDate endDate;

    public ProductAvailabilityRule() {}

    @JsonCreator
    public ProductAvailabilityRule(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}