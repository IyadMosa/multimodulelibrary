package com.imgware.multimodulelibrary.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.payment.queue:paymentQueue}")
    private String paymentQueueName;

    @Value("${rabbitmq.exchange:topic-exchange}")
    private String exchange;

    @Value("${rabbitmq.payment.routingKey:payment.routingKey}")
    private String paymentRoutingKey;

    @Bean
    Queue paymentQueue() {
        return new Queue(paymentQueueName, false);
    }

    @Bean
    Queue allQueue() {
        return new Queue("allQueue", false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    Binding paymentBinding(Queue paymentQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(paymentQueue).to(topicExchange).with(paymentRoutingKey);
    }

    @Bean
    Binding allBinding(Queue allQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(allQueue).to(topicExchange).with("queue.*");
    }
}