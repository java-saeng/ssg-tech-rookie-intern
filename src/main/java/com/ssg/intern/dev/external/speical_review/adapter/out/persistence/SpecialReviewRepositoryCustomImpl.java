package com.ssg.intern.dev.external.speical_review.adapter.out.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.dev.common.FeedSearchingConditionRequest;
import com.ssg.intern.dev.external.hashtag.domain.QHashTag;
import com.ssg.intern.dev.external.image.domain.QImage;
import com.ssg.intern.dev.external.product.domain.QProduct;
import com.ssg.intern.dev.external.speical_review.domain.QSpecialReview;
import com.ssg.intern.dev.external.speical_review.domain.SpecialReview;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.ssg.intern.common.domain.account.entity.QAccount.account;
import static com.ssg.intern.dev.external.hashtag.domain.QHashTag.*;
import static com.ssg.intern.dev.external.image.domain.QImage.*;
import static com.ssg.intern.dev.external.product.domain.QProduct.*;
import static com.ssg.intern.dev.external.speical_review.domain.QSpecialReview.*;

@RequiredArgsConstructor
public class SpecialReviewRepositoryCustomImpl implements SpecialReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<SpecialReview> findBySearchingCondition(final Pageable pageable,
                                                        final FeedSearchingConditionRequest condition) {

        final List<SpecialReview> specialReviews = queryFactory.selectFrom(specialReview).distinct()
                                                               .innerJoin(account).fetchJoin()
                                                               .on(account.id.eq(specialReview.account.id))
                                                               .innerJoin(product).fetchJoin()
                                                               .on(product.id.eq(specialReview.product.id))
                                                               .innerJoin(image).fetchJoin()
                                                               .on(image.specialReview.id.eq(specialReview.id))
                                                               .where(eqCookLevel(condition), eqCookQuantity(condition),
                                                                      eqCookTime(condition), hashTagSubQuery(condition), imageSubQuery())
                                                               .offset(pageable.getOffset())
                                                               .limit(pageable.getPageSize())
                                                               .fetch();

        return PageableExecutionUtils.getPage(specialReviews, pageable, specialReviews::size);
    }

    @Override
    public SpecialReview findBySpecialReviewId(final long specialReviewId) {
        return queryFactory.selectFrom(specialReview)
                           .innerJoin(account).fetchJoin()
                           .on(account.id.eq(specialReview.account.id))
                           .innerJoin(product).fetchJoin()
                           .on(product.id.eq(specialReview.product.id))
                           .leftJoin(hashTag).fetchJoin()
                           .on(specialReview.id.eq(hashTag.specialReview.id))
                           .innerJoin(image).fetchJoin()
                           .on(image.specialReview.id.eq(specialReview.id))
                           .where(specialReview.id.eq(specialReviewId), imageSubQuery())
                           .fetchOne();
    }

    private static BooleanExpression hashTagSubQuery(final FeedSearchingConditionRequest condition) {

        return StringUtils.hasText(condition.getHashTag()) ? JPAExpressions.selectFrom(hashTag)
                                                                           .where(hashTag.specialReview.id
                                                                                          .eq(specialReview.id)
                                                                                          .and(containsHashtag(
                                                                                                  condition.getHashTag())))
                                                                           .exists() : null;
    }

    private BooleanExpression imageSubQuery() {

        return JPAExpressions.selectFrom(image)
                             .where(image.specialReview.id.eq(specialReview.id))
                             .exists();
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
}
