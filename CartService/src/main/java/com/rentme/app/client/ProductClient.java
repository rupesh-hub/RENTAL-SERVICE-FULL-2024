package com.rentme.app.client;

import com.rentme.app.exception.CartServiceException;
import com.rentme.app.util.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    public GlobalResponse<ProductResponse> getById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<GlobalResponse<ProductResponse>> responseType = new ParameterizedTypeReference<>() {
        };

        ResponseEntity<GlobalResponse<ProductResponse>> responseEntity = null;

        try {
            responseEntity = restTemplate
                    .exchange(
                            String.format("%s/products/by.id/%s", productUrl, id),
                            HttpMethod.GET,
                            requestEntity,
                            responseType
                    );
        } catch (Exception e) {
            throw new CartServiceException("Error occur while fetching product with id " + id);
        }

        if (responseEntity.getStatusCode().isError()) {
            throw new CartServiceException("An error occurred while fetching product with id " + id);
        }

        if (responseEntity.getBody() == null) {
            throw new CartServiceException("Error occur while fetching product with id " + id);
        }

        return responseEntity.getBody();
    }

}
