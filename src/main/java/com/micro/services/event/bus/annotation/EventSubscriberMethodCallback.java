package com.micro.services.event.bus.annotation;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerEndpoint;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Method;
import java.util.UUID;
import com.micro.services.event.bus.event.EventEnum;
import com.micro.services.event.bus.event.ProductCreated;
import com.micro.services.event.bus.event.converter.EventObjectConverter;;

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
        if (method.isAnnotationPresent(EventSubscriber.class) && method.getParameterCount() == 1) {
            final EventEnum eventEnum = getEventEnum(method.getParameterTypes()[0]);

            if (eventEnum != null) {
                TopicExchange exchange = new TopicExchange(eventEnum.getExchangeName());
                amqpAdmin.declareExchange(exchange);
                
                Queue queue = amqpAdmin.declareQueue();
                amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(eventEnum.getRoutingKey()));

                MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(bean, method.getName());
                messageListenerAdapter.setMessageConverter(new EventObjectConverter(eventEnum.getEventClass()));

                SimpleRabbitListenerEndpoint simpleRabbitListenerEndpoint = new SimpleRabbitListenerEndpoint();
                simpleRabbitListenerEndpoint.setMessageListener(messageListenerAdapter);
                simpleRabbitListenerEndpoint.setId(UUID.randomUUID().toString());

                rabbitListenerEndpointRegistry.registerListenerContainer(
                        simpleRabbitListenerEndpoint, rabbitListenerContainerFactory);

                SimpleMessageListenerContainer simpleMessageListenerContainer
                        = (SimpleMessageListenerContainer) rabbitListenerEndpointRegistry.getListenerContainer(simpleRabbitListenerEndpoint.getId());
                simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
                simpleMessageListenerContainer.setQueueNames(queue.getName());
                simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
            }
        }
    }

    private EventEnum getEventEnum(Class<?> argClass) {
        if (ProductCreated.class.isAssignableFrom(argClass)) {
            return EventEnum.PRODUCT_CREATED;
        } else {
            return null;
        }
    }

}