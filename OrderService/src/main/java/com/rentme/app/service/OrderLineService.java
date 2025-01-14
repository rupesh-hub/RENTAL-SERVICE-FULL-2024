package com.rentme.app.service;

import com.rentme.app.entity.Order;
import com.rentme.app.entity.OrderLine;
import com.rentme.app.model.OrderLineRequest;
import com.rentme.app.model.OrderLineResponse;
import com.rentme.app.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;

    public Long save(OrderLineRequest orderLineRequest) {
        var saved = orderLineRepository.save(
                OrderLine
                        .builder()
                        .id(orderLineRequest.id())
                        .order(Order
                                .builder()
                                .id(Long.parseLong(orderLineRequest.orderId()))
                                .build()
                        )
                        .quantity(orderLineRequest.quantity())
                        .productId(orderLineRequest.productId())
                        .build()
        );

        return saved.getId();
    }

    public List<OrderLineResponse> findOrderLineByOrderId(String orderId) {
        return orderLineRepository.findByOrderId(orderId)
                .stream()
                .map(orderLine->new OrderLineResponse(
                        orderLine.getId(),
                        orderLine.getQuantity()
                ))
                .toList();


    }
}
