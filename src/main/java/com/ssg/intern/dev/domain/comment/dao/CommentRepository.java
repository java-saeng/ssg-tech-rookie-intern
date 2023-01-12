package com.ssg.intern.dev.domain.comment.dao;

import com.ssg.intern.dev.domain.comment.entity.Comment;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    Boolean existsDistinctByIdAndAccountId(Long id, Long accountId);

    Long countByFeed(Feed feed);

}
