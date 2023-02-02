package com.ssg.intern.mock.domain.review.dao;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.dev.domain.feed.presentation.model.FeedSearchingConditionRequest;
import com.ssg.intern.dev.global.SortingCondition;
import com.ssg.intern.mock.domain.review.entity.CookLevel;
import com.ssg.intern.mock.domain.review.entity.CookQuantity;
import com.ssg.intern.mock.domain.review.entity.CookTime;
import com.ssg.intern.mock.domain.review.entity.SpecialReview;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.ssg.intern.common.domain.account.entity.QAccount.account;
import static com.ssg.intern.dev.domain.feed.entity.QFeed.feed;
import static com.ssg.intern.mock.domain.hashtag.entity.QHashTag.*;
import static com.ssg.intern.mock.domain.product.entity.QProduct.product;
import static com.ssg.intern.mock.domain.review.entity.QSpecialReview.specialReview;

@RequiredArgsConstructor
public class SpecialReviewRepositoryCustomImpl implements SpecialReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public SpecialReview findBySpecialReviewId(final long specialReviewId) {
        return queryFactory.selectFrom(specialReview)
                           .innerJoin(account).fetchJoin()
                           .on(account.id.eq(specialReview.account.id))
                           .innerJoin(product).fetchJoin()
                           .on(product.id.eq(specialReview.product.id))
                           .leftJoin(hashTag).fetchJoin()
                           .on(specialReview.id.eq(hashTag.specialReview.id))
                           .where(specialReview.id.eq(specialReviewId))
                           .fetchOne();
    }

    @Override
    public Page<SpecialReview> findBySearchingCondition(final Pageable pageable,
                                                        final FeedSearchingConditionRequest condition) {

        final List<SpecialReview> specialReviews = queryFactory.selectFrom(specialReview)
                                                               .innerJoin(account).fetchJoin()
                                                               .on(account.id.eq(specialReview.account.id))
                                                               .innerJoin(product).fetchJoin()
                                                               .on(product.id.eq(specialReview.product.id))
                                                               .innerJoin(feed)
                                                               .on(feed.specialReviewId.eq(specialReview.id))
                                                               .where(eqCookLevel(condition), eqCookQuantity(condition),
                                                                      eqCookTime(condition), hashTagSubQuery(condition))
                                                               .offset(pageable.getOffset())
                                                               .limit(pageable.getPageSize())
                                                               .orderBy(sort(pageable.getSort()))
                                                               .fetch();

        return PageableExecutionUtils.getPage(specialReviews, pageable, specialReviews::size);
    }

    private static BooleanExpression hashTagSubQuery(final FeedSearchingConditionRequest condition) {

        return StringUtils.hasText(condition.getHashTag()) ? JPAExpressions.selectFrom(hashTag)
                                                                          .where(hashTag.specialReview.id
                                                                                          .eq(specialReview.id)
                                                                                          .and(containsHashtag(
                                                                                                  condition.getHashTag())))
                                                                           .exists() : null;
    }

    private static BooleanExpression eqCookTime(final FeedSearchingConditionRequest condition) {
        return condition.getCookTime() == null ? null : specialReview.cookTime.eq(condition.getCookTime());
    }

    private static BooleanExpression eqCookQuantity(final FeedSearchingConditionRequest condition) {
        return condition.getCookQuantity() == null ? null : specialReview.cookQuantity.eq(condition.getCookQuantity());
    }

    private static BooleanExpression eqCookLevel(final FeedSearchingConditionRequest condition) {
        return condition.getCookLevel() == null ? null : specialReview.cookLevel.eq(condition.getCookLevel());
    }

    private static BooleanExpression containsHashtag(final String keyword) {
        return StringUtils.hasText(keyword) ? hashTag.name.contains(keyword) : null;
    }

    private OrderSpecifier<?> sort(final Sort sort) {
        Order direction = Order.DESC;

        for (final Sort.Order order : sort) {
            if (order.getProperty().equals(SortingCondition.RECOMMENDATION.toString())) {
                return new OrderSpecifier<>(direction, feed.recommendCount);
            }
        }

        return new OrderSpecifier<>(direction, feed.createdAt);
    }
}
