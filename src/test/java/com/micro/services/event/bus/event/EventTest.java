package com.micro.services.event.bus.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.services.event.bus.event.model.ProductContent;

import org.junit.Before;
import org.junit.Test;

public class EventTest {

    private ObjectMapper objectMapper;

    @Before
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testEventSerialisationAndDeserialisation()
            throws JsonGenerationException, JsonMappingException, IOException {
        ProductContent productContent = new ProductContent
            .Builder()
            .withProductCode("P1234")
            .withProductDescription("Bla...")
            .build();
        
        Event event = new ProductCreated(productContent);
        final String json = objectMapper.writeValueAsString(event);
        
        event = objectMapper.readValue(json, Event.class);

        assertTrue(event.getClass().isAssignableFrom(ProductCreated.class));

        ProductCreated productCreated = (ProductCreated) event;

        assertTrue(productCreated.getProductContent().getProductCode().equals(productContent.getProductCode()));
        assertTrue(productCreated.getProductContent().getProductDescription().equals(productContent.getProductDescription()));
    }

    @Test
    public void testGetEventTypeFromEventClass() {
        Event event = new ProductCreated(new ProductContent.Builder().build());
        EventType eventType = EventType.getEventType(event);
        assertEquals("Event type should be PRODUCT_CREATED", EventType.PRODUCT_CREATED, eventType);
    }

}