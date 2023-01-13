package com.ssg.intern.dev.domain.comment.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssg.intern.common.domain.account.entity.QAccount.account;
import static com.ssg.intern.dev.domain.comment.entity.QComment.comment;

@Repository
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {

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
}
