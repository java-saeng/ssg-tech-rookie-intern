package com.ssg.intern.dev.domain.recommend.service;

import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import com.ssg.intern.dev.domain.recommend.dao.RecommendRepository;
import com.ssg.intern.dev.domain.recommend.entity.Recommend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("RecommendCommandService Unit Test")
class RecommendCommandServiceTest {

    RecommendCommandService recommendCommandService;

    FeedRepository feedRepository;

    RecommendRepository recommendRepository;

    @BeforeEach
    void init() {
        recommendRepository = mock(RecommendRepository.class);
        feedRepository = mock(FeedRepository.class);

        recommendCommandService = new RecommendCommandService(recommendRepository, feedRepository);
    }

    @Test
    @DisplayName("addRecommendToFeedByFeedId() : 적어도 한번 추천해요 취소를 누른 feed에서 다시 추천해요를 누를 수 있습니다.")
    void test_addRecommendToFeedByFeedId_atLeastOnceRecommend() {
        //given
        final Feed feed = Feed.from(1L);
        final Recommend recommend = Recommend.of(1L, false, feed);

        //when
        when(feedRepository.findById(anyLong()))
                .thenReturn(Optional.of(feed));

        when(recommendRepository.findById(anyLong()))
                .thenReturn(Optional.of(recommend));

        final long recommendCount = recommendCommandService.
                addRecommendToFeedByFeedId(1L, 1L, Optional.of(1L));

        //then
        assertAll(
                () -> assertEquals(recommendCount, 1),
                () -> assertTrue(recommend.isRecommended())
        );
    }

    @Test
    @DisplayName("addRecommendToFeedByFeedId() : feed에 추천해요를 누를 수 있습니다.")
    void test_addRecommendToFeedByFeedId_firstRecommend() {
        //given
        final Feed feed = Feed.from(1L);

        //when
        when(feedRepository.findById(anyLong()))
                .thenReturn(Optional.of(feed));

        when(recommendRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        final long recommendCount = recommendCommandService.
                addRecommendToFeedByFeedId(1L, 1L, Optional.empty());

        //then
        assertEquals(recommendCount, 1);
    }
}