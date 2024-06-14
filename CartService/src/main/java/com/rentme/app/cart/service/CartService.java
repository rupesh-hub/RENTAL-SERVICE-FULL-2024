package com.rentme.app.cart.service;

import com.rentme.app.cart.entity.Cart;
import com.rentme.app.cart.model.CartRequest;
import com.rentme.app.cart.model.CartResponse;
import com.rentme.app.cart.repository.CartRepository;
import com.rentme.app.client.ProductClient;
import com.rentme.app.exception.CartServiceException;
import com.rentme.app.util.GlobalResponse;
import com.rentme.app.util.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final ProductClient productClient;

    @Override
    public GlobalResponse<Void> add(CartRequest request, Principal principal) {
        String username = principal.getName();

        cartRepository.save(
                Cart
                        .builder()
                        .username(username)
                        .productId(request.getProductId())
                        .quantity(request.getQuantity())
                        .build()
        );

        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<List<CartResponse>> getByUser(
            int page,
            int size,
            Principal principal
    ) {
        String username = principal.getName();
        Page<Cart> cartPage = cartRepository.findByUsername(username, PageRequest.of(page, size));

        var response = cartPage.getContent()
                .stream()
                .map(item -> CartResponse
                        .builder()
                        .id(item.getId())
                        .createdAt(item.getCreatedAt())
                        .updatedAt(item.getModifiedOn())
                        .quantity(item.getQuantity())
                        .product(
                                productClient
                                        .getById(Long.parseLong(item.getProductId()))
                                        .getData()
                        )
                        .build())
                .toList();

        return GlobalResponse.success(response,
                Paging.builder()
                        .page(page)
                        .size(size)
                        .totalElement(cartPage.getTotalElements())
                        .totalPage(cartPage.getTotalPages())
                        .first(cartPage.isFirst())
                        .last(cartPage.isLast())
                        .build()
        );
    }

    @Override
    public GlobalResponse<CartResponse> getByProductIdAndUSerId(String productId, Principal principal) {

        String username = principal.getName();
//        var user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new RentMeException("user not exists with username " + username));

        var response = cartRepository.findByProductIdAndUserId(productId, "")
                .map(item -> CartResponse
                        .builder()
                        .id(item.getId())
//                        .createdAt(item.getCreatedDate())
//                        .updatedAt(item.getLastModifiedDate())
                        .quantity(item.getQuantity())
//                        .product(
//                                productRepository
//                                        .findById(item.getProductId())
//                                        .map(ProductMapper::toResponse)
//                                        .orElseThrow(() -> new RentMeException("No product exists with id " + item.getProductId()))
//                        )
                        .build())
                .orElseThrow(() -> new CartServiceException("No item found."));

        return GlobalResponse.success(response);
    }
}
