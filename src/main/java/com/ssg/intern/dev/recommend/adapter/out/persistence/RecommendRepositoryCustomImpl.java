package com.ssg.intern.dev.recommend.adapter.out.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.dev.recommend.domain.Recommend;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.ssg.intern.dev.feed.domain.QFeed.feed;
import static com.ssg.intern.dev.recommend.domain.QRecommend.recommend;


@RequiredArgsConstructor
public class RecommendRepositoryCustomImpl implements RecommendRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Recommend> findRecommendByFeedAndAccount(final long accountId, final long feedId) {
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
