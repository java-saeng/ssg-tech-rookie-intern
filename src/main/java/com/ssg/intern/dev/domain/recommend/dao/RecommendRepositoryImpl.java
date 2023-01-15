package com.ssg.intern.dev.domain.recommend.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.dev.domain.feed.entity.QFeed;
import com.ssg.intern.dev.domain.recommend.entity.QRecommend;
import com.ssg.intern.dev.domain.recommend.entity.Recommend;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.ssg.intern.dev.domain.feed.entity.QFeed.*;
import static com.ssg.intern.dev.domain.recommend.entity.QRecommend.*;

@RequiredArgsConstructor
public class RecommendRepositoryImpl implements RecommendRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Recommend> findRecommendByFeedAndAccount(final long feedId, final long accountId) {
        return Optional.ofNullable(queryFactory.selectFrom(recommend)
                                               .innerJoin(feed).fetchJoin()
                                               .on(recommend.feed.id.eq(feed.id))
                                               .where(eqFeedId(feedId).and(eqAccountId(accountId)))
                                               .fetchFirst());
    }

    private BooleanExpression eqAccountId(long accountId) {
        return recommend.accountId.eq(accountId);
    }

    private BooleanExpression eqFeedId(long feedId) {
        return recommend.feed.id.eq(feedId);
    }
}
