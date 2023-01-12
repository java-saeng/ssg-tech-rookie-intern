package com.ssg.intern.dev.domain.comment.presentation.model;

import com.ssg.intern.dev.domain.comment.dao.CommentSingleDao;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CommentSelectResponse {
    private Long commentCount;
    private List<CommentSingleDao> comments;
}
