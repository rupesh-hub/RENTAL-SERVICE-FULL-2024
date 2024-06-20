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
public class PaymentRequestEventProducer implements EventProducer<PaymentRequestEvent> {

    private final KafkaTemplate<String, PaymentRequestEvent> kafkaTemplate;

    @Override
    public void send(PaymentRequestEvent data) {
        log.info("Sending payment request...");
        Message<PaymentRequestEvent> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "payment-request")
                .build();
        
        kafkaTemplate.send(message);
    }

}
