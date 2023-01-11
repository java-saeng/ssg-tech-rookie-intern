package com.ssg.intern.mock.domain.review.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.mock.domain.review.entity.SpecialReview;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.ssg.intern.common.domain.account.entity.QAccount.account;
import static com.ssg.intern.mock.domain.product.entity.QProduct.product;
import static com.ssg.intern.mock.domain.review.entity.QSpecialReview.specialReview;
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
}