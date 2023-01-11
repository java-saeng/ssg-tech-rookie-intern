package com.ssg.intern.dev.domain.comment.presentation.model;

import com.ssg.intern.dev.domain.comment.dao.CommentSingleDao;

import java.util.List;

public class CommentSelectResponse {
    private Long commentCount;
    private List<CommentSingleDao> comments;

}
