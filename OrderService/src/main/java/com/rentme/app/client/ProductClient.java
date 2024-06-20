package com.rentme.app.client;

import com.rentme.app.model.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    /*
     * Just to confirm if there is product available
     * Also to check available quantity
     * */
    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(
                requestBody,
                headers
        );

        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate
                .exchange(
                        String.format("%s/products/purchase", productUrl),
                        HttpMethod.POST,
                        requestEntity,
                        responseType
                );

        if (responseEntity.getStatusCode().isError())
            throw new RuntimeException("An error occurred while processing purchase products.");

        return responseEntity.getBody();
    }


}
