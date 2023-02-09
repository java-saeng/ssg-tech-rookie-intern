package com.ssg.intern.dev.recommend.application.service;

import com.ssg.intern.dev.bookmark.domain.Bookmark;
import com.ssg.intern.dev.feed.adapter.out.persistence.FeedPersistenceAdapter;
import com.ssg.intern.dev.feed.application.port.out.LoadSingleFeedPort;
import com.ssg.intern.dev.feed.domain.Feed;
import com.ssg.intern.dev.recommend.adapter.out.persistence.RecommendPersistenceAdapter;
import com.ssg.intern.dev.recommend.application.port.out.AddRecommendPort;
import com.ssg.intern.dev.recommend.application.port.out.LoadRecommendPort;
import com.ssg.intern.dev.recommend.domain.Recommend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@DisplayName("addRecommendService Unit Test")
class AddRecommendServiceTest {

    LoadRecommendPort loadRecommendPort;

    AddRecommendPort addRecommendPort;

    LoadSingleFeedPort loadSingleFeedPort;

    AddRecommendService addRecommendService;

    @BeforeEach
    void init() {
        loadRecommendPort = mock(RecommendPersistenceAdapter.class);
        loadSingleFeedPort = mock(FeedPersistenceAdapter.class);
        addRecommendPort = mock(RecommendPersistenceAdapter.class);

        addRecommendService = new AddRecommendService(loadRecommendPort, addRecommendPort, loadSingleFeedPort);
    }

    @Test
    @DisplayName("addRecommendToFeed() : 적어도 한번 추천해요 취소를 누른 feed에서 다시 추천해요를 누를 수 있습니다.")
    void test_addRecommendToFeed_ExistedRecommend() {
        //given
        final Feed feed = Feed.from(1L);
        final Recommend recommend = Recommend.of(1L, false, feed);

        //when
        when(loadRecommendPort.findRecommendByFeedAndAccount(anyLong(), anyLong()))
                .thenReturn(Optional.of(recommend));

        addRecommendService.addRecommendToFeed(1L, 1L);

        //then
        assertAll(
                () -> assertEquals(recommend.getFeed().getRecommendCount(), 1),
                () -> assertTrue(recommend.isRecommended())
        );
    }

    @Test
    @DisplayName("addRecommendToFeed() : 특정 피드에 처음으로 추천해요를 추가할 수 있습니다.")
    void test_addRecommendToFeed_notExistedRecommend() {
        //given
        final Feed feed = Feed.from(1L);

        //when
        doNothing().when(addRecommendPort).addRecommendToFeed(any());
        when(loadSingleFeedPort.findById(anyLong()))
                .thenReturn(Optional.of(feed));

        //추천해요를 처음 생성하기 때문에 여기서 추천해요 엔티티 생성
        final Recommend recommend = Recommend.of(1L, true, feed);

        addRecommendService.addRecommendToFeed(1L, 1L);

        //then
        assertAll(
                () -> assertEquals(recommend.getFeed().getRecommendCount(), 1),
                () -> assertTrue(recommend.isRecommended())
        );
    }
}