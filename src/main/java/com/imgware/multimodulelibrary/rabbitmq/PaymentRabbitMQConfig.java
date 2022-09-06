package com.imgware.multimodulelibrary.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class PaymentRabbitMQConfig {

    public static final String PAYMENT_STREAM = "payment.stream";
    public static final String PAYMENT_EXCHANGE = "payment.exchange";

    @Bean
    Queue paymentStream() {
        HashMap<String, Object> args = new HashMap();
        args.put("x-queue-type", "stream");
        args.put("x-stream-offset", "next");
        Queue build = new Queue(PAYMENT_STREAM, true, false, false, args);
        return build;
    }

    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange(PAYMENT_EXCHANGE, true, false);
    }

    @Bean
    Binding paymentBinding() {
        return new Binding(PAYMENT_STREAM, Binding.DestinationType.QUEUE, PAYMENT_EXCHANGE, "", null);
    }

    @Bean
    public RabbitTemplate paymentRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        final Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        template.setMessageConverter(messageConverter);
        template.setExchange(PAYMENT_EXCHANGE);
        return template;
    }

}