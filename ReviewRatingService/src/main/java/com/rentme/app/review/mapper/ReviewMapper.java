package com.rentme.app.review.mapper;

import com.rentme.app.review.entity.Review;
import com.rentme.app.review.model.ReviewRequest;
import com.rentme.app.review.model.ReviewResponse;

public final class ReviewMapper {

    private ReviewMapper() {}

    public static Review toEntity(ReviewRequest request){
        return Review
                .builder()
                .productId(request.getProductId())
                .rating(request.getRating())
                .build();
    }

    public static ReviewResponse toResponse(Review review){
        return null;
    }

}