package com.rentme.app.wish.service;

import com.rentme.app.util.GlobalResponse;
import com.rentme.app.wish.model.WishRequest;
import com.rentme.app.wish.model.WishResponse;

import java.security.Principal;
import java.util.List;

public interface IWishService {
    GlobalResponse<Void> add(WishRequest request, Principal principal);

    GlobalResponse<List<WishResponse>> getByUserId(
            int page,
            int size,
            Principal principal);

    GlobalResponse<WishResponse> getByProductIdAndUSerId(String productId, Principal principal);
}
