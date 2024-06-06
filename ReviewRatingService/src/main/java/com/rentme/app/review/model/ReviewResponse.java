package com.rentme.app.review.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rentme.app.comment.model.CommentResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewResponse {

    private Long id;
    private int rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> comments;
//    private UserResponse user;
//    private ProductResponse product;

}