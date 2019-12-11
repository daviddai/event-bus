package com.micro.services.event.bus.event.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductAvailablePeriod {
    private LocalDate from;
    private LocalDate to;

    public ProductAvailablePeriod() {}

    @JsonCreator
    public ProductAvailablePeriod(@JsonProperty("from")LocalDate from, @JsonProperty("to")LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }
}