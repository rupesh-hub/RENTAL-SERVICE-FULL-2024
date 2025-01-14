package com.rentme.app.event.consumer;

import com.rentme.app.enumeration.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record PaymentRequestEvent(
        String username,
        List<PurchaseRequest> purchaseRequests,
        PaymentMethod paymentMethod
) {
}
