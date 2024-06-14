package com.rentme.app.wish.service;

import com.rentme.app.util.GlobalResponse;
import com.rentme.app.wish.model.WishRequest;
import com.rentme.app.wish.model.WishResponse;

import java.security.Principal;
import java.util.List;

public interface IWishService {
    GlobalResponse<Void> add(WishRequest request, Principal principal);

    GlobalResponse<List<WishResponse>> getByUsername(
            int page,
            int size,
            Principal principal);

    GlobalResponse<WishResponse> getByProductIdAndUsername(String productId, Principal principal);
}
