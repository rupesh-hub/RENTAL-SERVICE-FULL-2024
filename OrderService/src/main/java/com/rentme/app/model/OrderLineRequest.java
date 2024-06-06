package com.rentme.app.model;

public record OrderLineRequest(
        Long id,
        String orderId,
        String productId,
        double quantity
) {
}
