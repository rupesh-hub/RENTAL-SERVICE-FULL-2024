package com.rentme.app.category.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String modifiedBy;

}