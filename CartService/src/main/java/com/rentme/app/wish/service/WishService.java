package com.rentme.app.wish.service;

import com.rentme.app.client.ProductClient;
import com.rentme.app.exception.CartServiceException;
import com.rentme.app.util.GlobalResponse;
import com.rentme.app.util.Paging;
import com.rentme.app.wish.entity.Wish;
import com.rentme.app.wish.model.WishRequest;
import com.rentme.app.wish.model.WishResponse;
import com.rentme.app.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService implements IWishService {

    private final WishRepository wishRepository;
    private final ProductClient productClient;

    @Override
    public GlobalResponse<Void> add(WishRequest request, Principal principal) {

        var wish = Wish
                .builder()
                .productId(request.getProductId())
                .username(principal.getName())
                .build();

        wishRepository.save(wish);

        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<List<WishResponse>> getByUsername(int page, int size, Principal principal) {

        Page<Wish> wishPage = wishRepository.findByUserId(principal.getName(), PageRequest.of(page, size));

        var response = wishPage.getContent()
                .stream()
                .map(item -> WishResponse
                        .builder()
                        .id(item.getId())
                        .createdAt(item.getCreatedAt())
                        .updatedAt(item.getModifiedOn())
                        .product(
                                productClient.getById(item.getId())
                                        .getData()
                        )
                        .build())
                .toList();

        return GlobalResponse.success(response,
                Paging.builder()
                        .page(page)
                        .size(size)
                        .totalElement(wishPage.getTotalElements())
                        .totalPage(wishPage.getTotalPages())
                        .first(wishPage.isFirst())
                        .last(wishPage.isLast())
                        .build()
        );
    }

    @Override
    public GlobalResponse<WishResponse> getByProductIdAndUsername(String productId, Principal principal) {

        var response = wishRepository.findByProductIdAndUsername(productId, principal.getName())
                .map(item ->
                        WishResponse
                                .builder()
                                .id(item.getId())
                                .createdAt(item.getCreatedAt())
                                .updatedAt(item.getModifiedOn())
                                .product(
                                        productClient.getById(item.getId())
                                                .getData()
                                )
                                .build()
                )
                .orElseThrow(() -> new CartServiceException("No item found with product id : " + productId));

        return GlobalResponse.success(response);
    }
}
