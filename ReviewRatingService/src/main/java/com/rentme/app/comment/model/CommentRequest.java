package com.rentme.app.comment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentRequest {

    private Long reviewId;
    private String comment;

}
