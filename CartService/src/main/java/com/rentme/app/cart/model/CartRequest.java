package com.rentme.app.cart.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequest {

    private String productId;
    private int quantity;

}
