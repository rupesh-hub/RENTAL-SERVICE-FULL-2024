package com.rentme.app.cart.resource;

import com.rentme.app.cart.model.CartRequest;
import com.rentme.app.cart.model.CartResponse;
import com.rentme.app.cart.service.ICartService;
import com.rentme.app.util.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@Tag(name = "product_carts")
public class CartResource {

    private final ICartService cartService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<GlobalResponse<Void>> add(
            @RequestBody @Valid CartRequest request,
            Principal principal) {
        cartService.add(request, principal);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/by.user")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<GlobalResponse<List<CartResponse>>> getByUser(
            Principal principal,
            @RequestParam(name="page", defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                cartService.getByUser(page, size, principal)
        );
    }

    @GetMapping("/by.userId/productId/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<GlobalResponse<CartResponse>> getByUserId(
            @PathVariable String productId,
            Principal principal
    ) {
        return ResponseEntity.ok(
                cartService.getByProductIdAndUSerId(productId, principal)
        );
    }

    //delete cart item

    //update cart item quantity

    //clear cart

}
