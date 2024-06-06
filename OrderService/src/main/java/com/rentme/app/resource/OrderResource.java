package com.rentme.app.resource;

import com.rentme.app.model.OrderRequest;
import com.rentme.app.model.OrderResponse;
import com.rentme.app.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findByOrderId(
            @PathVariable Long orderId
    ){
        return ResponseEntity.ok(orderService.findByOrderId(orderId));
    }

}
