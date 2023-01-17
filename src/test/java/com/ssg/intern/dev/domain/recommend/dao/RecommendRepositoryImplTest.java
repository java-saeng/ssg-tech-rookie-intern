package com.ssg.intern.dev.domain.recommend.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import com.ssg.intern.dev.domain.recommend.entity.QRecommend;
import com.ssg.intern.dev.domain.recommend.entity.Recommend;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.ssg.intern.dev.domain.feed.entity.QFeed.feed;
import static com.ssg.intern.dev.domain.recommend.entity.QRecommend.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("RecommendRepository Querydsl Integration Test")
class RecommendRepositoryImplTest {

    @Autowired
    JPAQueryFactory queryFactory;

    @Autowired
    RecommendRepository recommendRepository;

    @Autowired
    FeedRepository feedRepository;

    @Test
    @DisplayName("querydsl를 통해 존재하는 Recommend를 조회할 수 있다.")
    void test_findRecommendByFeedAndAccount_existedRecommend() {
        //given
        long feedId = 1L;
        long accountId = 1L;

        final Feed savedFeed = feedRepository.findById(feedId).get();
        final Recommend recommend = recommendRepository.save(Recommend.of(1L, true, savedFeed));

        //when
        final Optional<Recommend> savedRecommend = Optional.ofNullable(queryFactory.selectFrom(QRecommend.recommend)
                                                                                   .innerJoin(feed).fetchJoin()
                                                                                   .on(QRecommend.recommend.feed.id.eq(feed.id))
                                                                                   .where(feed.id.eq(feedId)
                                                                                                 .and(QRecommend.recommend.accountId.eq(
                                                                                                         accountId)))
                                                                                   .fetchFirst());
        //then
        assertAll(
                () -> assertTrue(savedRecommend.isPresent()),
                () -> assertEquals(savedRecommend.get().getId(), recommend.getId())
        );
    }

    @Test
    @DisplayName("querydsl를 통해 Recommend가 존재하지 않는 것을 알 수 있다.")
    void test_findRecommendByFeedAndAccount_notExistedRecommend() {
        //given
        long feedId = 1L;
        long accountId = 1L;

        //when
        final Optional<Recommend> savedRecommend = Optional.ofNullable(queryFactory.selectFrom(recommend)
                                                                                   .innerJoin(feed).fetchJoin()
                                                                                   .on(recommend.feed.id.eq(feed.id))
                                                                                   .where(feed.id.eq(feedId)
                                                                                                 .and(recommend.accountId.eq(
                                                                                                         accountId)))
                                                                                   .fetchFirst());
        //then
        assertFalse(savedRecommend.isPresent());
    }
}