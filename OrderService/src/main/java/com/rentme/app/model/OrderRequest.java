package com.rentme.app.model;

import com.rentme.app.enumeration.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Long id,
        String reference,
        @Positive(message = "Order amount should be positive.")
        BigDecimal amount,

        @NotNull(message = "Payment method should not be null.")
        PaymentMethod paymentMethod,

        @NotEmpty(message="You should at least purchase one product.")
        List<PurchaseRequest> products
) {

}
