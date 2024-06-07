package com.rentme.app.service;

import com.rentme.app.client.CustomerClient;
import com.rentme.app.client.ProductClient;
import com.rentme.app.entity.Order;
import com.rentme.app.event.OrderConfirmationEvent;
import com.rentme.app.event.OrderEventProducer;
import com.rentme.app.model.OrderLineRequest;
import com.rentme.app.model.OrderRequest;
import com.rentme.app.model.OrderResponse;
import com.rentme.app.model.PurchaseRequest;
import com.rentme.app.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderEventProducer orderEventProducer;

    public String createOrder(OrderRequest request) {

        //check the customer : OpenFeign
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow();

        //purchase the products : product ms : RestTemplate
        var purchaseProducts = productClient.purchaseProducts(
                request.products()
        );

        // persist order
        var order = orderRepository.save(toOrder(request));

        // persist order line
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.save(
                    new OrderLineRequest(
                            null,
                            order.getId().toString(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //start payment process : payment ms
        // --> event driven microservice communication using kafka
        //prepare payment request
        //prepare payment event producer
        //send message to producer: paymentRequest


        // send order confirmation : notification ms (kafka)
        orderEventProducer.sendOrderConfirmationEvent(
                new OrderConfirmationEvent(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );

        return order.getId().toString();

    }

    private static Order toOrder(OrderRequest request) {

        return Order
                .builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.customerId())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public List<OrderResponse> findAll() {

        return orderRepository.findAll()
                .stream()
                .map(order -> new OrderResponse(
                                order.getId(),
                                order.getReference(),
                                order.getTotalAmount(),
                                order.getPaymentMethod(),
                                order.getCustomerId()
                        )
                )
                .collect(Collectors.toList());

    }

    public OrderResponse findByOrderId(Long orderId) {

        return orderRepository.findById(orderId)
                .map(order -> new OrderResponse(
                        order.getId(),
                        order.getReference(),
                        order.getTotalAmount(),
                        order.getPaymentMethod(),
                        order.getCustomerId()
                ))
                .orElseThrow();

    }
}