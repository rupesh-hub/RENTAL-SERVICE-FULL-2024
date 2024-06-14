package com.rentme.app.cart.service;

import com.rentme.app.cart.model.CartRequest;
import com.rentme.app.cart.model.CartResponse;
import com.rentme.app.util.GlobalResponse;

import java.security.Principal;
import java.util.List;

public interface ICartService {

    GlobalResponse<Void> add(CartRequest request, Principal principal);

    GlobalResponse<List<CartResponse>> getByUser(
            int page,
            int size,
            Principal principal);

    GlobalResponse<CartResponse> getByProductIdAndUSerId(String productId, Principal principal);

}
