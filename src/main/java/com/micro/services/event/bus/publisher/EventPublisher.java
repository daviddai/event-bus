package com.micro.services.event.bus.publisher;

import com.micro.services.event.bus.event.Event;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publish(final Event event) throws AmqpException {
        //rabbitTemplate.convertAndSend("exchange", "routingKey", null);
    }

}