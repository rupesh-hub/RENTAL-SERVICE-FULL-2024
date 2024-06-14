package com.rentme.app.test;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResource {

    @GetMapping("/ping")
    public String ping(){
        return "Product service ping!!!";
    }

    @GetMapping("/demo")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String demo(){
        return "DEMO SUCCESS!!";
    }

}
