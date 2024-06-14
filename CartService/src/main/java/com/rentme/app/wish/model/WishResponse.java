package com.rentme.app.wish.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rentme.app.client.ProductResponse;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WishResponse {

    private Long id;
    private ProductResponse product;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
