package com.micro.services.event.bus.event.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;

public class ProductAvailabilityPeriodTest {

    private ObjectMapper objectMapper;

    @Before
    public void init() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void testProductAvailabilityPeriodDeserialisationFromSimpleDateFormat() throws IOException {
        final String json = "{\"from\": \"2020-01-01\", \"to\": \"2020-01-31\"}";
        ProductAvailablePeriod productAvailablePeriod = objectMapper.readValue(json, ProductAvailablePeriod.class);
        assertNotNull(productAvailablePeriod);
        assertNotNull(productAvailablePeriod.getFrom());
        assertNotNull(productAvailablePeriod.getTo());
        assertEquals(2020, productAvailablePeriod.getFrom().getYear());
        assertEquals(1, productAvailablePeriod.getFrom().getMonthValue());
        assertEquals(1, productAvailablePeriod.getFrom().getDayOfMonth());
    }

    @Test
    public void testProductAvailabilityPeriodSerialisation() throws IOException {
        final String json = objectMapper.writeValueAsString(getProductAvailabilityPeriod());
        ProductAvailablePeriod productAvailablePeriod = objectMapper.readValue(json, ProductAvailablePeriod.class);
        assertNotNull(productAvailablePeriod);
        assertNotNull(productAvailablePeriod.getFrom());
        assertNotNull(productAvailablePeriod.getTo());
        assertEquals(2020, productAvailablePeriod.getFrom().getYear());
        assertEquals(1, productAvailablePeriod.getFrom().getMonthValue());
        assertEquals(1, productAvailablePeriod.getFrom().getDayOfMonth());
    }

    private ProductAvailablePeriod getProductAvailabilityPeriod() {
        return new ProductAvailablePeriod(
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 31)
        );
    }

}
