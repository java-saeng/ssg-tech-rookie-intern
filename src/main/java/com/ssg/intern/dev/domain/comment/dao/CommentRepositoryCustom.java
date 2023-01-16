package com.ssg.intern.dev.domain.comment.dao;

import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentSingleDao> findAllInnerFetchJoinAccount(Long feedId);
}
