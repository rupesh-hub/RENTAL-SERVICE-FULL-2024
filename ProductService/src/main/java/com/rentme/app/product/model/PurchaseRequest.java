package com.rentme.app.product.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message="Product is mandatory.")
        String productId,

        @Positive(message = "Quantity is mandatory,")
        double quantity

) {
}
