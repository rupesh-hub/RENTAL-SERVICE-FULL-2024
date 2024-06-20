package com.rentme.app.product.model;

import java.math.BigDecimal;

public record PurchaseResponse(
        String productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}