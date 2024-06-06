package com.rentme.app.wish.service;

import com.rentme.app.exception.ApiException;
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

    @Override
    public GlobalResponse<Void> add(WishRequest request, Principal principal) {

        var wish = Wish
                .builder()
                .productId(request.getProductId())
                .build();

        wishRepository.save(wish);

        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<List<WishResponse>> getByUserId(int page, int size, Principal principal) {

        Page<Wish> wishPage = wishRepository.findByUserId("user.getId()", PageRequest.of(page, size));

        var response = wishPage.getContent()
                .stream()
                .map(item -> WishResponse
                        .builder()
                        .id(item.getId())
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
    public GlobalResponse<WishResponse> getByProductIdAndUSerId(String productId, Principal principal) {
        var response = wishRepository.findByProductIdAndUserId(productId, "")
                .map(item -> WishResponse
                        .builder()
                        .id(item.getId())
                        .build())
                .orElseThrow(() -> new ApiException("No item found."));

        return GlobalResponse.success(response);
    }
}
