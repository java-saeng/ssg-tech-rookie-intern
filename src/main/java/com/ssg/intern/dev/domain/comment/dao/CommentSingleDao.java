package com.ssg.intern.dev.domain.comment.dao;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Data
@NoArgsConstructor
public class CommentSingleDao {
    private String email;
    private String content;

    @QueryProjection
    public CommentSingleDao(String email, String content) {
        this.email = email;
        this.content = content;
    }
}
