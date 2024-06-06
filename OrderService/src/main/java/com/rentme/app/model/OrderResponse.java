package com.rentme.app.model;

import com.rentme.app.enumeration.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
