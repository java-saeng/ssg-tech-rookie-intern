package com.ssg.intern.dev.domain.bookmark.service;

import com.ssg.intern.dev.domain.bookmark.dao.BookmarkRepository;
import com.ssg.intern.dev.domain.bookmark.entity.Bookmark;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkCommandService {

    private final BookmarkRepository bookmarkRepository;
    private final FeedRepository feedRepository;

    public void addBookmarkToFeedByFeedId(final long accountId, final long feedId) {

        bookmarkRepository.findBookmarkByFeedAndAccount(feedId, accountId)
                          .ifPresentOrElse(
                                  (bookmark -> {

                                      final Feed savedFeed = bookmark.getFeed();

                                      if (!bookmark.isBookmarked()) {
                                          savedFeed.increaseBookmark();
                                          bookmark.addBookmark();
                                      }

                                  }),

                                  () -> {
                                      final Feed savedFeed = feedRepository.findById(feedId)
                                                                           .orElseThrow(EntityNotFoundException::new);

                                      final Bookmark bookmark = Bookmark.of(accountId, savedFeed, true);

                                      bookmarkRepository.save(bookmark);
                                      savedFeed.increaseBookmark();
                                  }
                          );
    }

    public void cancelBookmarkToFeedByFeedId(final long accountId, final long feedId) {

        bookmarkRepository.findBookmarkByFeedAndAccount(feedId, accountId)
                          .ifPresent(
                                  (bookmark -> {

                                      if (bookmark.isBookmarked()) {
                                          bookmark.cancelBookmark();
                                          bookmark.getFeed().decreaseBookmark();
                                      }
                                  })
                          );
    }
}
