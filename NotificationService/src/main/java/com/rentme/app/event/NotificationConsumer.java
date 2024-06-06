package com.rentme.app.event;

import com.rentme.app.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    //private final EmailService emailService;

    @KafkaListener(topics = {"payment-confirmation"})
    public void paymentSuccessNotification(PaymentConfirmation paymentConfirmation){
        log.info(String.format("Consuming message from "));

        //save notification to repository
        //notificationRepository.save();

        //todo: send email

    }

    @KafkaListener(topics = {"order-confirmation"})
    public void orderConfirmationNotification(OrderConfirmation orderConfirmation){
        log.info(String.format("Consuming message from "));

        //save notification to repository
        //notificationRepository.save();

        //todo: send email

    }

}
