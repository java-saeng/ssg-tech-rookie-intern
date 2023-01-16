package com.ssg.intern.dev.domain.mypage.service;

import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("MypageCommandService Unit Test")
class MypageCommandServiceTest {

    MypageCommandService mypageCommandService;

    FeedRepository feedRepository;

    @BeforeEach
    void init() {
        feedRepository = mock(FeedRepository.class);

        mypageCommandService = new MypageCommandService(feedRepository);
    }

    @Test
    @DisplayName("changeCommentBlocked() : 댓글 막기 옵션을 변경할 수 있다.")
    void test_changeCommentBlocked() {
        //given
        final Feed feed = Feed.from(1L);

        //when & then
        when(feedRepository.findById(anyLong()))
                .thenReturn(Optional.of(feed));

        mypageCommandService.changeCommentBlocked(1L);
        assertTrue(feed.isCommentBlocked());

        mypageCommandService.changeCommentBlocked(1L);
        assertFalse(feed.isCommentBlocked());

        mypageCommandService.changeCommentBlocked(1L);
        assertTrue(feed.isCommentBlocked());

        mypageCommandService.changeCommentBlocked(1L);
        assertFalse(feed.isCommentBlocked());
    }

}