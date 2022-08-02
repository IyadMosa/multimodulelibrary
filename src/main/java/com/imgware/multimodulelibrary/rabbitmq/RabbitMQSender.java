package com.imgware.multimodulelibrary.rabbitmq;

import details.PaymentDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQSender {

    private final Logger logger = LoggerFactory.getLogger(RabbitMQSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange:topic-exchange}")
    private String exchange;

    @Value("${rabbitmq.payment.routingKey:payment.routingKey}")
    private String paymentRoutingKey;

    public void send(Object msg) {
//        String exchange ="";
//        String routingKey = "";
        if (msg instanceof PaymentDetails) {
            rabbitTemplate.convertAndSend(exchange, paymentRoutingKey, msg);
            logger.info("Sending payment msg = {}", msg);
        }

    }
}
