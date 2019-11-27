package com.micro.services.event.bus.annotation;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerEndpoint;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.services.event.bus.event.ProductCreated;

public class EventSubscriberMethodCallback implements ReflectionUtils.MethodCallback {

    private RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;
    private RabbitListenerContainerFactory<SimpleMessageListenerContainer> rabbitListenerContainerFactory;
    private AmqpAdmin amqpAdmin;
    private ConnectionFactory connectionFactory;
    private Object bean;

    public EventSubscriberMethodCallback(RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry,
            AmqpAdmin amqpAdmin, ConnectionFactory connectionFactory,
            RabbitListenerContainerFactory<SimpleMessageListenerContainer> rabbitListenerContainerFactory,
            Object bean) {
        this.rabbitListenerEndpointRegistry = rabbitListenerEndpointRegistry;
        this.amqpAdmin = amqpAdmin;
        this.connectionFactory = connectionFactory;
        this.rabbitListenerContainerFactory = rabbitListenerContainerFactory;
        this.bean = bean;
    }

    @Override
    public void doWith(Method method) throws IllegalArgumentException {
        if (method.isAnnotationPresent(EventSubscriber.class)) {
            TopicExchange exchange = new TopicExchange("supplierExchange");
            Queue queue = new Queue("product-content");

            amqpAdmin.declareExchange(exchange);
            amqpAdmin.declareQueue(queue);

            amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("supplier.createProduct"));

            MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(bean, method.getName());
            messageListenerAdapter.setMessageConverter(new MessageConverter() {
                @Override
                public Message toMessage(Object object, MessageProperties messageProperties)
                        throws MessageConversionException {
                    return null;
                }

                @Override
                public Object fromMessage(Message message) throws MessageConversionException {
                    String json = new String(message.getBody());
                    ObjectMapper objectMapper = new ObjectMapper();
                    
                    try {
                        return objectMapper.readValue(json, ProductCreated.class);
                    } catch (JsonParseException e) {
                        e.printStackTrace();
                    } catch (JsonMappingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
            });

            SimpleRabbitListenerEndpoint simpleRabbitListenerEndpoint = new SimpleRabbitListenerEndpoint();
            simpleRabbitListenerEndpoint.setMessageListener(messageListenerAdapter);
            simpleRabbitListenerEndpoint.setId(UUID.randomUUID().toString());

            rabbitListenerEndpointRegistry.registerListenerContainer(
                    simpleRabbitListenerEndpoint, rabbitListenerContainerFactory);

            SimpleMessageListenerContainer simpleMessageListenerContainer
                    = (SimpleMessageListenerContainer) rabbitListenerEndpointRegistry.getListenerContainer(simpleRabbitListenerEndpoint.getId());
            simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
            simpleMessageListenerContainer.setQueueNames("product-content");
            simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
        }
    }
}