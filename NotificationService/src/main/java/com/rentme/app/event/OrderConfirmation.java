package com.rentme.app.event;

import com.rentme.app.enumeration.PaymentMethod;
import com.rentme.app.model.Customer;
import com.rentme.app.model.Product;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String reference,
        BigDecimal amount,
        @Enumerated(EnumType.STRING)
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
