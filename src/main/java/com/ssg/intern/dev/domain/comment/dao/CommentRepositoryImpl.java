package com.ssg.intern.dev.domain.comment.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.dev.domain.comment.entity.Comment;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ssg.intern.dev.domain.comment.entity.QComment.comment;
import static com.ssg.intern.dev.domain.feed.entity.QFeed.feed;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Comment> findCommentsByFeedId(final Long feedId) {
        return queryFactory.selectFrom(comment)
                           .innerJoin(feed).fetchJoin()
                           .on(comment.feed.id.eq(feedId))
                           .fetch();
    }
}
