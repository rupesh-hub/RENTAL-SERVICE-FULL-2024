package com.rentme.app.category.mapper;

import com.rentme.app.category.entity.Category;
import com.rentme.app.category.model.CategoryRequest;
import com.rentme.app.category.model.CategoryResponse;

public final class CategoryMapper {

    private CategoryMapper() {
    }

    public static Category toEntity(CategoryRequest request) {
        return Category
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public static CategoryResponse toResponse(Category category) {
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

}


