package com.rentme.app.event.producer;

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
public class PaymentConfirmationEventProducer implements EventProducer<PaymentConfirmationEvent> {


    private final KafkaTemplate<String, PaymentConfirmationEvent> kafkaTemplate;

    @Override
    public void send(PaymentConfirmationEvent data) {
        log.info("Sending payment confirmation...");
        Message<PaymentConfirmationEvent> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "payment-confirmation")
                .build();
        kafkaTemplate.send(message);
    }

}
