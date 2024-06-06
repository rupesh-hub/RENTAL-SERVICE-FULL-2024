package com.rentme.app.model;

import java.math.BigDecimal;

public record Product(
        String is,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
