package com.rentme.app.client;

import com.rentme.app.util.GlobalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-url}"
)
public interface ProductClient {

    @GetMapping("/products/by.id/{id}")
    ResponseEntity<GlobalResponse<ProductResponse>> getById(@PathVariable Long id);

}
