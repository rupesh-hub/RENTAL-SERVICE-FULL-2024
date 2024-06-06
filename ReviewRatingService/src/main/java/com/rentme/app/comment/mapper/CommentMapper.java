package com.rentme.app.comment.mapper;

import com.rentme.app.comment.entity.Comment;
import com.rentme.app.comment.model.CommentRequest;
import com.rentme.app.comment.model.CommentResponse;

public final class CommentMapper {

    private CommentMapper() {}

    public static Comment toEntity(CommentRequest comment) {
        return Comment.builder().comment(comment.getComment()).build();
    }

    public static CommentResponse toResponse(Comment comment) {
        return CommentResponse
                .builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .build();
    }

}
