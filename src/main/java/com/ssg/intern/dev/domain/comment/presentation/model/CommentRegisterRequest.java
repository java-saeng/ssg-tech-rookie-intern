package com.ssg.intern.dev.domain.comment.presentation.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentRegisterRequest {
    private String content;

    @Builder
    public CommentRegisterRequest(String content) {
        this.content = content;
    }
}
