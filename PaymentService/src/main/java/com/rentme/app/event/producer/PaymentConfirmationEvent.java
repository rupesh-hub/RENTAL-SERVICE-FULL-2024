package com.rentme.app.event.producer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentConfirmationEvent(
        String message,
        LocalDateTime timestamp,
        String status,
        BigDecimal amount,
        String product,
        String user
) {
}
