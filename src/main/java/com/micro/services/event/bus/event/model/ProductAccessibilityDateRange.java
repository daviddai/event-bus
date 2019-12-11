package com.micro.services.event.bus.event.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ProductAccessibilityDateRange {
    private LocalDate from;
    private LocalDate end;

    public ProductAccessibilityDateRange() {}

    @JsonCreator
    public ProductAccessibilityDateRange(LocalDate from, LocalDate end) {
        this.from = from;
        this.end = end;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getEnd() {
        return end;
    }
}