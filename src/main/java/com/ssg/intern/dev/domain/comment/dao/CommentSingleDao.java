package com.ssg.intern.dev.domain.comment.dao;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentSingleDao {
    private String email;
    private String content;

    private LocalDateTime createdAt;

    @QueryProjection
    public CommentSingleDao(String email, String content, LocalDateTime createdAt) {
        this.email = email;
        this.content = content;
        this.createdAt = createdAt;
    }
}
