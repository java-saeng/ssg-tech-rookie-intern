package com.ssg.intern.dev.bookmark.application.service;

import com.ssg.intern.dev.bookmark.adapter.out.persistence.BookmarkPersistenceAdapter;
import com.ssg.intern.dev.bookmark.application.port.out.AddBookmarkPort;
import com.ssg.intern.dev.bookmark.application.port.out.LoadBookmarkPort;
import com.ssg.intern.dev.bookmark.domain.Bookmark;
import com.ssg.intern.dev.feed.adapter.out.persistence.FeedPersistenceAdapter;
import com.ssg.intern.dev.feed.application.port.out.LoadSingleFeedPort;
import com.ssg.intern.dev.feed.domain.Feed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AddBookmarkServiceTest {

    LoadBookmarkPort loadBookmarkPort;

    AddBookmarkPort addBookmarkPort;

    LoadSingleFeedPort loadSingleFeedPort;

    AddBookmarkService addBookmarkService;

    @BeforeEach
    void init() {
        loadBookmarkPort = mock(BookmarkPersistenceAdapter.class);
        loadSingleFeedPort = mock(FeedPersistenceAdapter.class);
        addBookmarkPort = mock(BookmarkPersistenceAdapter.class);

        addBookmarkService = new AddBookmarkService(loadBookmarkPort, addBookmarkPort, loadSingleFeedPort);
    }

    @Test
    @DisplayName("addBookmarkToFeed() : 적어도 한번 북마크 취소를 누른 feed에서 다시 북마크를 누를 수 있습니다.")
    void test_addBookmarkToFeed_ExistedBookmark() {
        //given
        final Feed feed = Feed.from(1L);
        final Bookmark bookmark = Bookmark.of(1L, feed, false);

        //when
        when(loadBookmarkPort.findBookmarkByFeedAndAccount(anyLong(), anyLong()))
                .thenReturn(Optional.of(bookmark));

        addBookmarkService.addBookmarkToFeed(1L, 1L);

        //then
        assertAll(
                () -> assertEquals(bookmark.getFeed().getBookmarkCount(), 1),
                () -> assertTrue(bookmark.isBookmarked())
        );
    }

    @Test
    @DisplayName("addBookmarkToFeed() : 특정 피드에 처음으로 추천해요를 추가할 수 있습니다.")
    void test_addBookmarkToFeed_notExistedBookmark() {
        //given
        final Feed feed = Feed.from(1L);

        //when
        doNothing().when(addBookmarkPort).addBookmarkToFeed(any());
        when(loadSingleFeedPort.findById(anyLong()))
                .thenReturn(Optional.of(feed));

        //북마크를 처음 생성하기 때문에 여기서 북마크 엔티티 생성
        final Bookmark bookmark = Bookmark.of(1L, feed, true);

        addBookmarkService.addBookmarkToFeed(1L, 1L);

        //then
        assertAll(
                () -> assertEquals(bookmark.getFeed().getBookmarkCount(), 1),
                () -> assertTrue(bookmark.isBookmarked())
        );
    }
}