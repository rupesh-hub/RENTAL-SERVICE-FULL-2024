package com.rentme.app.product.mapper;

import com.rentme.app.product.entity.Product;
import com.rentme.app.product.model.ProductRequest;
import com.rentme.app.product.model.ProductResponse;

public final class ProductMapper {

    private ProductMapper() {
    }

    public static Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        return product;
    }

    public static ProductResponse toResponse(Product product) {
        return ProductResponse
                .builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .category(product.getCategory().getName())
                .build();
    }
}