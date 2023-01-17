package com.ssg.intern.mock.domain.review.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.dev.domain.feed.presentation.model.FeedSearchingConditionRequest;
import com.ssg.intern.mock.domain.review.entity.CookLevel;
import com.ssg.intern.mock.domain.review.entity.CookQuantity;
import com.ssg.intern.mock.domain.review.entity.CookTime;
import com.ssg.intern.mock.domain.review.entity.SpecialReview;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.ssg.intern.common.domain.account.entity.QAccount.account;
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
                           .on(account.id.eq(specialReview.account.id))
                           .where(specialReview.id.eq(specialReviewId))
                           .fetchOne();
    }

    @Override
    public Page<SpecialReview> findBySearchingCondition(final Pageable pageable,
                                                        final FeedSearchingConditionRequest condition) {

        final List<SpecialReview> specialReviews = queryFactory.selectFrom(specialReview).distinct()
                                                               .innerJoin(hashTag).fetchJoin()
                                                               .on(specialReview.id.eq(hashTag.specialReview.id))
                                                               .innerJoin(account).fetchJoin()
                                                               .on(account.id.eq(specialReview.account.id))
                                                               .innerJoin(product).fetchJoin()
                                                               .on(product.id.eq(specialReview.product.id))
                                                               .where(containsHashtag(condition.getHashTag())
                                                                              .and(eqCookLevel(
                                                                                      condition.getCookLevel()))
                                                                              .and(eqCookQuantity(
                                                                                      condition.getCookQuantity()))
                                                                              .and(eqCookTime(condition.getCookTime())))
                                                               .offset(pageable.getOffset())
                                                               .limit(pageable.getPageSize())
                                                               .fetch();

        return PageableExecutionUtils.getPage(specialReviews, pageable, specialReviews::size);
    }

    private static BooleanExpression eqCookTime(final CookTime cookTime) {
        return cookTime == null ? null : specialReview.cookTime.eq(cookTime);
    }

    private static BooleanExpression eqCookQuantity(final CookQuantity cookQuantity) {
        return cookQuantity == null ? null : specialReview.cookQuantity.eq(cookQuantity);
    }

    private static BooleanExpression eqCookLevel(final CookLevel cookLevel) {
        return cookLevel == null ? null : specialReview.cookLevel.eq(cookLevel);
    }

    private static BooleanExpression containsHashtag(final String keyword) {
        return StringUtils.hasText(keyword) ? hashTag.name.contains(keyword) : null;
    }
}
