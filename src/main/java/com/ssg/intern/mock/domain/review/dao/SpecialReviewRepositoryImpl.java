package com.ssg.intern.mock.domain.review.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.mock.domain.review.entity.SpecialReview;
import lombok.RequiredArgsConstructor;

import static com.ssg.intern.common.domain.account.entity.QAccount.account;
import static com.ssg.intern.mock.domain.product.entity.QProduct.product;
import static com.ssg.intern.mock.domain.review.entity.QSpecialReview.specialReview;

@RequiredArgsConstructor
public class SpecialReviewRepositoryImpl implements SpecialReviewRepositoryCustom {

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
}
