package com.ssg.intern.dev.domain.comment.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.dev.domain.comment.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssg.intern.common.domain.account.entity.QAccount.account;
import static com.ssg.intern.dev.domain.comment.entity.QComment.comment;
import static com.ssg.intern.dev.domain.feed.entity.QFeed.feed;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommentSingleDao> findAllInnerFetchJoinAccount(Long feedId) {
        return jpaQueryFactory
                .select(new QCommentSingleDao(account.email, comment.content, comment.createdAt))
                .from(comment)
                .innerJoin(account).fetchJoin()
                .on(comment.accountId.eq(account.id))
                .where(comment.feed.id.eq(feedId))
                .orderBy(comment.createdAt.desc())
                .fetch();
    }

    @Override
    public List<Comment> findCommentsByFeedId(final Long feedId) {
        return jpaQueryFactory.selectFrom(comment)
                              .innerJoin(feed).fetchJoin()
                              .on(comment.feed.id.eq(feedId))
                              .fetch();
    }
}
