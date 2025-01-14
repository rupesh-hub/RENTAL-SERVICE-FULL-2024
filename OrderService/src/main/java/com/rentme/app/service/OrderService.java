package com.rentme.app.service;

import com.rentme.app.client.CustomerClient;
import com.rentme.app.client.ProductClient;
import com.rentme.app.entity.Order;
import com.rentme.app.enumeration.PaymentMethod;
import com.rentme.app.event.EventProducer;
import com.rentme.app.event.OrderConfirmationEvent;
import com.rentme.app.event.PaymentRequestEvent;
import com.rentme.app.model.OrderLineRequest;
import com.rentme.app.model.OrderRequest;
import com.rentme.app.model.OrderResponse;
import com.rentme.app.model.PurchaseRequest;
import com.rentme.app.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final EventProducer<OrderConfirmationEvent> eventProducer;
    private final EventProducer<PaymentRequestEvent> paymentEventProducer;

    public String createOrder(OrderRequest request, Principal principal) {

        //check the customer : OpenFeign
        var customer = customerClient.findUserByUsername(principal.getName())
                .orElseThrow();

        //purchase the products : product ms : RestTemplate
        var purchaseProducts = productClient.purchaseProducts(
                request.products()
        );

        // persist order
        var order = orderRepository.save(toOrder(request, principal.getName()));

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
        paymentEventProducer.send(
                new PaymentRequestEvent(
                        principal.getName(),
                        request.products(),
                        PaymentMethod.CASH_ON_DELIVERY
                )
        );


        // send order confirmation : notification ms (kafka)
        eventProducer.send(
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

    private static Order toOrder(OrderRequest request, String username) {

        return Order
                .builder()
                .id(request.id())
                .customerId(username)
                .reference(username)
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
