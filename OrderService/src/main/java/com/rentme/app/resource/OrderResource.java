package com.rentme.app.resource;

import com.rentme.app.model.OrderRequest;
import com.rentme.app.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "product_orders")
public class OrderResource {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(
            @RequestBody @Valid final OrderRequest request
    ){
        return ResponseEntity.ok(orderService.createOrder(request));
    }

}
