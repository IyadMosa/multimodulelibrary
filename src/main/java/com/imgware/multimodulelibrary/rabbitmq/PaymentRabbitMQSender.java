package com.imgware.multimodulelibrary.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.imgware.multimodulelibrary.rabbitmq.RabbitMQConfig.PAYMENT_EXCHANGE;


@Service
public class PaymentRabbitMQSender {
    private final Logger logger = LoggerFactory.getLogger(PaymentRabbitMQSender.class);
    @Autowired
    RabbitTemplate sender;

    public void send(Object msg) {
        logger.info("Sending payment details msg to payment = {}", msg);
        sender.convertAndSend(PAYMENT_EXCHANGE, "", msg);

    }
}
