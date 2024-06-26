package com.rentme.app.event.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentme.app.client.CustomerClient;
import com.rentme.app.client.ProductClient;
import com.rentme.app.entity.Payment;
import com.rentme.app.event.producer.EventProducer;
import com.rentme.app.event.producer.PaymentConfirmationEvent;
import com.rentme.app.exception.PaymentException;
import com.rentme.app.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentRequestEventConsumer implements EventConsumer<PaymentRequestEvent> {

    private final EventProducer<PaymentConfirmationEvent> eventProducer;
    private final PaymentRepository paymentRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    @KafkaListener(topics = {"payment-request"})
    @Override
    public void consume(PaymentRequestEvent request) {

        // fetch user
        var user = customerClient.findUserByUsername(request.username())
                .orElseThrow(() -> new PaymentException("Invalid user"));

        // fetch product
        var productResponses =
        request.purchaseRequests()
                .stream()
                .map(purchaseRequest -> productClient.getById(Long.parseLong(purchaseRequest.productId()))
                            .getBody()
                            .getData()
                )
                .collect(Collectors.toList());

        // process payment
        var productJson = toJsonString(productResponses);
        var userJson = toJsonString(user);

        // save log to db
        paymentRepository.save(
                Payment.builder()
                        .paymentMethod(request.paymentMethod())
                        .product(toJsonString(request.purchaseRequests()))
                        .product(productJson)
                        .user(userJson)
                        .build()
        );

        // send notification
        eventProducer.send(
                new PaymentConfirmationEvent(
                        "Payment has been done.",
                        LocalDateTime.now(),
                        "SUCCESS",
                        BigDecimal.ZERO,
                        productJson,
                        userJson
                )
        );
    }

    private <T> String toJsonString(T data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
