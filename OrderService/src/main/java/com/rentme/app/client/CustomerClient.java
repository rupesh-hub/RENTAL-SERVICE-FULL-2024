package com.rentme.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.authorization-url}"
)
public interface CustomerClient {

    @GetMapping("/users/by.username/(username}")
    Optional<CustomerResponse> findUserByUsername(
            @PathVariable("username") String username
    );

}
