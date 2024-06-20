package com.rentme.app.event.consumer;

import com.rentme.app.enumeration.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequestEvent(
    String username,
    String productId,
    BigDecimal amount,
    int quantity,
    PaymentMethod paymentMethod
) {
}
