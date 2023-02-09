package com.ssg.intern.dev.recommend.application.service;

import com.ssg.intern.dev.bookmark.domain.Bookmark;
import com.ssg.intern.dev.feed.domain.Feed;
import com.ssg.intern.dev.recommend.adapter.out.persistence.RecommendPersistenceAdapter;
import com.ssg.intern.dev.recommend.application.port.out.LoadRecommendPort;
import com.ssg.intern.dev.recommend.domain.Recommend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("cancelRecommendService Unit Test")
class CancelRecommendServiceTest {

    CancelRecommendService cancelRecommendService;

    LoadRecommendPort loadRecommendPort;

    @BeforeEach
    void init() {
        loadRecommendPort = mock(RecommendPersistenceAdapter.class);

        cancelRecommendService = new CancelRecommendService(loadRecommendPort);
    }

    @Test
    @DisplayName("cancelRecommendToFeed() : 특정 게시글에 사용자가 추천해요를 취소할 수 있다.")
    void test_cancelRecommendToFeed() {
        //given
        final Feed feed = Feed.from(1L);
        final Recommend recommend = Recommend.of(1L, true, feed);

        //when
        when(loadRecommendPort.findRecommendByFeedAndAccount(anyLong(), anyLong()))
                .thenReturn(Optional.of(recommend));

        cancelRecommendService.cancelRecommendToFeed(1L, 1L);

        //then
        assertAll(
                () -> assertEquals(recommend.getFeed().getRecommendCount(), -1),
                () -> assertFalse(recommend.isRecommended())
        );

    }
}