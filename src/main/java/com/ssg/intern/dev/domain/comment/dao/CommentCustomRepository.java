package com.ssg.intern.dev.domain.comment.dao;

import java.util.List;

public interface CommentCustomRepository {
    List<CommentSingleDao> findAllInnerFetchJoinAccount();
}
