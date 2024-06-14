package com.rentme.app.wish.resource;

import com.rentme.app.util.GlobalResponse;
import com.rentme.app.wish.model.WishRequest;
import com.rentme.app.wish.model.WishResponse;
import com.rentme.app.wish.service.IWishService;
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
@RequestMapping("/wish")
@RequiredArgsConstructor
@Tag(name = "product_wish")
public class WishResource {

    private final IWishService wishService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<GlobalResponse<Void>> add(
            @RequestBody @Valid WishRequest request,
            Principal principal) {
        wishService.add(request, principal);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/by.userId")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<GlobalResponse<List<WishResponse>>> getByUsername(
            Principal principal,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                wishService.getByUsername(page, size, principal)
        );
    }

    @GetMapping("/by.user.productId/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<GlobalResponse<WishResponse>> getByUser(
            @PathVariable String productId,
            Principal principal
    ) {
        return ResponseEntity.ok(
                wishService.getByProductIdAndUsername(productId, principal)
        );
    }

}
