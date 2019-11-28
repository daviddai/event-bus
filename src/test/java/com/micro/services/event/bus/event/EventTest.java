package com.micro.services.event.bus.event;

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
    public void productCreatedDeserialisationShouldContainEventType()
            throws JsonGenerationException, JsonMappingException, IOException {
        ProductContent productContent = new ProductContent
            .Builder()
            .withProductCode("P1234")
            .withProductDescription("Bla...")
            .build();
        
        Event event = new ProductCreated(productContent);
        
        final String json = objectMapper.writeValueAsString(event);

        System.out.println(json);

        objectMapper.readValue(json, Event.class);
    }

}