package com.ssg.intern.dev.domain.comment.dao;

import com.ssg.intern.dev.domain.comment.entity.Comment;
import java.util.List;

public interface CommentRepositoryCustom {

    List<CommentSingleDao> findAllInnerFetchJoinAccount(Long feedId);

    List<Comment> findCommentsByFeedId(Long feedId);
    
}
