package com.ssg.intern.dev.domain.comment.presentation.model;

import com.ssg.intern.dev.domain.comment.dao.CommentSingleDao;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CommentSelectResponse {
    private Long commentCount;
    private List<CommentSingleDao> comments;

    @Builder
    public CommentSelectResponse(Long commentCount, List<CommentSingleDao> comments) {
        this.commentCount = commentCount;
        this.comments = comments;
    }
}
