package com.ssg.intern.mock.domain.review.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.mock.domain.review.entity.CookLevel;
import com.ssg.intern.mock.domain.review.entity.CookQuantity;
import com.ssg.intern.mock.domain.review.entity.CookTime;
import com.ssg.intern.mock.domain.review.entity.SpecialReview;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.ssg.intern.common.domain.account.entity.QAccount.account;
import static com.ssg.intern.mock.domain.hashtag.entity.QHashTag.hashTag;
import static com.ssg.intern.mock.domain.product.entity.QProduct.product;
import static com.ssg.intern.mock.domain.review.entity.QSpecialReview.specialReview;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("SpecialReviewRepository Querydsl Integration Test")
class SpecialReviewRepositoryImplTest {

    @Autowired
    JPAQueryFactory queryFactory;

    @Test
    @DisplayName("findBySpecialReviewId() : SpecialReview 조회 시 account, product inner join을 할 수 있다.")
    void testFindBySpecialReviewId() {
        //when
        final SpecialReview result = queryFactory.selectFrom(specialReview)
                                                 .innerJoin(account).fetchJoin()
                                                 .on(account.id.eq(specialReview.account.id))
                                                 .innerJoin(product).fetchJoin()
                                                 .on(account.id.eq(specialReview.account.id))
                                                 .where(specialReview.id.eq(1L))
                                                 .fetchOne();

        //then
        assertAll(
                () -> assertEquals(result.getId(), 1L),
                () -> assertEquals(result.getAccount().getId(), 2L),
                () -> assertEquals(result.getProduct().getId(), 1L)
        );
    }

    @Test
    @DisplayName("findBySearchingCondition() : 원하는 조건에 맞게 SpecialReview를 조회할 수 있다.")
    void testFindBySearchingCondition() {
        final List<SpecialReview> specialReviews = queryFactory.selectFrom(specialReview).distinct()
                                                               .innerJoin(hashTag).fetchJoin()
                                                               .on(specialReview.id.eq(hashTag.specialReview.id))
                                                               .innerJoin(account).fetchJoin()
                                                               .on(account.id.eq(specialReview.account.id))
                                                               .innerJoin(product).fetchJoin()
                                                               .on(account.id.eq(specialReview.account.id))
                                                               .where(hashTag.name.eq("좋아"))
                                                               .where(specialReview.cookLevel.eq(CookLevel.EASY))
                                                               .where(specialReview.cookQuantity.eq(CookQuantity.SOLO))
                                                               .where(specialReview.cookTime.eq(CookTime.TEN))
                                                               .fetch();

        assertAll(
                () -> assertEquals(specialReviews.size(), 1),
                () -> assertThat(specialReviews.get(0).getDescription()).isEqualTo("참기름을 둘러주세요")
        );
    }
}