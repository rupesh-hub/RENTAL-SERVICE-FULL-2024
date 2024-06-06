package com.rentme.app.resource;

import com.rentme.app.model.OrderLineResponse;
import com.rentme.app.service.OrderLineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order-lines")
@RequiredArgsConstructor
@Tag(name = "product_order_lines")
public class OrderLineResource {

    private final OrderLineService orderLineService;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderLineResponse>> findOrderLineByOrderId(
            @PathVariable String orderId
    ) {
        return ResponseEntity.ok(orderLineService.findOrderLineByOrderId(orderId));
    }

}
