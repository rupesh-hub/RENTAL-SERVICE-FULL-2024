package com.rentme.app.event;

import com.rentme.app.client.CustomerResponse;
import com.rentme.app.client.PurchaseResponse;
import com.rentme.app.enumeration.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmationEvent(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
