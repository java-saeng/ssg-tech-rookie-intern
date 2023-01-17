package com.ssg.intern.dev.domain.bookmark.service;

import com.ssg.intern.dev.domain.bookmark.dao.BookmarkRepository;
import com.ssg.intern.dev.domain.bookmark.entity.Bookmark;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@DisplayName("BookmarkCommandService Unit Test")
class BookmarkCommandServiceTest {

    BookmarkCommandService bookmarkCommandService;

    FeedRepository feedRepository;

    BookmarkRepository bookmarkRepository;

    @BeforeEach
    void init() {
        bookmarkRepository = mock(BookmarkRepository.class);
        feedRepository = mock(FeedRepository.class);

        bookmarkCommandService = new BookmarkCommandService(bookmarkRepository, feedRepository);
    }

    @Test
    @DisplayName("addBookmarkToFeedByFeedId() : 적어도 한번 북마크 취소를 누른 feed에서 다시 북마크를 누를 수 있습니다.")
    void test_addBookmarkToFeedByFeedId_atLeastOnceBookmark() {
        //given
        final Feed feed = Feed.from(1L);
        final Bookmark bookmark = Bookmark.of(1L, feed, false);

        //when
        when(bookmarkRepository.findBookmarkByFeedAndAccount(anyLong(), anyLong()))
                .thenReturn(Optional.of(bookmark));

        bookmarkCommandService.addBookmarkToFeedByFeedId(1L, 1L);

        //then
        assertAll(
                () -> assertEquals(feed.getBookmarkCount(), 1),
                () -> assertTrue(bookmark.isBookmarked())
        );
    }

    @Test
    @DisplayName("addBookmarkToFeedByFeedId() : feed에 북마크를 누를 수 있습니다.")
    void test_addBookmarkToFeedByFeedId_firstRecommend() {
        //given
        final Feed feed = Feed.from(1L);

        //when
        when(bookmarkRepository.findBookmarkByFeedAndAccount(anyLong(), anyLong()))
                .thenReturn(Optional.empty());

        when(feedRepository.findById(anyLong()))
                .thenReturn(Optional.of(feed));

        bookmarkCommandService.addBookmarkToFeedByFeedId(1L, 1L);

        //then
        assertEquals(feed.getBookmarkCount(), 1);
        verify(bookmarkRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("cancelBookmarkToFeedByFeedId() : feed에 북마크를 취소할 수 있습니다.")
    void test_cancelBookmarkToFeedByFeedId() {
        //given
        final Feed feed = Feed.from(1L);
        final Bookmark bookmark = Bookmark.of(1L, feed, true);

        feed.increaseBookmark();
        feed.increaseBookmark();
        feed.increaseBookmark();

        //when
        when(bookmarkRepository.findBookmarkByFeedAndAccount(anyLong(), anyLong()))
                .thenReturn(Optional.of(bookmark));

        bookmarkCommandService.cancelBookmarkToFeedByFeedId(1L, 1L);

        //then
        assertAll(
                () -> assertEquals(feed.getBookmarkCount(), 2),
                () -> assertFalse(bookmark.isBookmarked())
        );
    }
}