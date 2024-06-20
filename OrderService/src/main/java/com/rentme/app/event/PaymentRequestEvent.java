package com.rentme.app.event;

import com.rentme.app.enumeration.PaymentMethod;
import com.rentme.app.model.PurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

public record PaymentRequestEvent(
        String username,
        List<PurchaseRequest> purchaseRequests,
        PaymentMethod paymentMethod
) {
}
