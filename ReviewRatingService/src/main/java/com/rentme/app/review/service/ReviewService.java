package com.rentme.app.review.service;

import com.rentme.app.comment.entity.Comment;
import com.rentme.app.comment.mapper.CommentMapper;
import com.rentme.app.exception.ApiException;
import com.rentme.app.review.entity.Review;
import com.rentme.app.review.model.ReviewRequest;
import com.rentme.app.review.model.ReviewResponse;
import com.rentme.app.review.repository.ReviewRepository;
import com.rentme.app.util.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public GlobalResponse<Void> create(ReviewRequest request, Principal principal) {

        var username = principal.getName();
//        var user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new RentMeException("User not found with email: " + username));

        var review = Review
                .builder()
                .productId(request.getProductId())
                .rating(request.getRating())
                .userId(user.getId())
                .comments(Arrays.asList(
                        Comment
                                .builder()
                                .comment(request.getComment())
                                .build()
                ))
                .build();
        reviewRepository.save(review);
        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<ReviewResponse> findById(Long id) {
        var review = reviewRepository.findById(id)
                .orElseThrow(() -> new ApiException("Review not found with id: " + id));

        var productId = review.getProductId();

        return GlobalResponse.success(
                ReviewResponse
                        .builder()
                        .id(review.getId())
                        .rating(review.getRating())
                        .comments(review.getComments().stream().map(CommentMapper::toResponse).toList())
                        .build()
        );
    }

    @Override
    public GlobalResponse<Void> update(ReviewRequest request) {
        return null;
    }

    @Override
    public GlobalResponse<Void> delete(Long id) {
        return null;
    }

    @Override
    public GlobalResponse<List<ReviewResponse>> findAll(int page, int size) {

        Page<Review> reviewPage = reviewRepository.findAll(PageRequest.of(page, size));
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for (Review review : reviewPage.getContent()) {

            //find user and product : OPENFEIGN

            var productId = review.getProductId();

            var comments = review.getComments()
                    .stream()
                    .map(CommentMapper::toResponse)
                    .toList();

            reviewResponses
                    .add(
                            ReviewResponse
                                    .builder()
                                    .id(review.getId())
                                    .rating(review.getRating())
                                    .comments(comments)
                                    .build()
                    );

        }

//        var paging = Paging
//                .builder()
//                .page(page)
//                .size(size)
//                .totalPage(reviewPage.getTotalPages())
//                .totalElement(reviewPage.getTotalElements())
//                .first(reviewPage.isFirst())
//                .last(reviewPage.isLast())
//                .build();

        return GlobalResponse.success(reviewResponses);
    }

    @Override
    public GlobalResponse<List<ReviewResponse>> findByProductId(Long productId) {
        return null;
    }

    @Override
    public GlobalResponse<List<ReviewResponse>> findByUserId(Long userId) {
        return null;
    }

    @Override
    public GlobalResponse<List<ReviewResponse>> findByProductIdAndUserId(Long productId, Long userId) {
        return null;
    }
}