package com.rentme.app.resource;

import com.rentme.app.model.OrderRequest;
import com.rentme.app.model.OrderResponse;
import com.rentme.app.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "product_orders")
public class OrderResource {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> createOrder(
            @RequestBody @Valid final OrderRequest request,
            Principal principal
    ){
        return ResponseEntity.ok(orderService.createOrder(request, principal));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{orderId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<OrderResponse> findByOrderId(
            @PathVariable Long orderId
    ){
        return ResponseEntity.ok(orderService.findByOrderId(orderId));
    }

}
