package com.micro.services.event.bus.publisher;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.services.event.bus.event.Event;
import com.micro.services.event.bus.event.EventType;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    private AmqpAdmin amqpAdmin;
    private RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public EventPublisher(AmqpAdmin amqpAdmin, RabbitTemplate rabbitTemplate) {
        this.amqpAdmin = amqpAdmin;
        this.rabbitTemplate = rabbitTemplate;
        objectMapper = new ObjectMapper();
    }

    public void publish(final Event event) throws AmqpException, JsonGenerationException, JsonMappingException, IOException {
        final String json = objectMapper.writeValueAsString(event);
        EventType eventType = EventType.getEventType(event);
        amqpAdmin.declareExchange(new TopicExchange(eventType.getExchangeName()));
        rabbitTemplate.convertAndSend(eventType.getExchangeName(), eventType.getRoutingKey(), json);
    }

}