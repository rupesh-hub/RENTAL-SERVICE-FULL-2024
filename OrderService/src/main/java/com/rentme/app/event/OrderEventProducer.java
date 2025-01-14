package com.rentme.app.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventProducer implements EventProducer<OrderConfirmationEvent> {

    private final KafkaTemplate<String, OrderConfirmationEvent> kafkaTemplate;

    @Override
    public void send(OrderConfirmationEvent data) {
        log.info("Sending order confirmation...");
        Message<OrderConfirmationEvent> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, Topics.ORDER_CONFIRMATION.name())
                .build();
        kafkaTemplate.send(message);
    }
}
