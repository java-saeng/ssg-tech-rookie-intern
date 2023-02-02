package com.ssg.intern.dev.domain.bookmark.service;

import com.ssg.intern.dev.domain.bookmark.dao.BookmarkRepository;
import com.ssg.intern.dev.domain.bookmark.entity.Bookmark;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import com.ssg.intern.dev.global.buffer.BufferStatus;
import com.ssg.intern.dev.global.buffer.ConcurrentMapBuffer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class BookmarkCommandService {

    private final BookmarkRepository bookmarkRepository;
    private final FeedRepository feedRepository;
    private final BookmarkQueryService bookmarkQueryService;
    private final ConcurrentMapBuffer concurrentMapBuffer;

    public BookmarkCommandService(final BookmarkRepository bookmarkRepository, final FeedRepository feedRepository,
                                  final BookmarkQueryService bookmarkQueryService) {
        this.bookmarkRepository = bookmarkRepository;
        this.feedRepository = feedRepository;
        this.bookmarkQueryService = bookmarkQueryService;
        concurrentMapBuffer = new ConcurrentMapBuffer();
    }

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

                                      final Bookmark savedBookmark = bookmarkRepository.save(bookmark);
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

    public void bufferFlush() {

        final List<BufferStatus> flushCandidate = concurrentMapBuffer.flush();

        for (final BufferStatus bufferStatus : flushCandidate) {

            if (bufferStatus.isDeleteFlag()) {
                cancelBookmarkToFeedByFeedId(bufferStatus.getAccountId(), bufferStatus.getFeedId());
            } else {
                addBookmarkToFeedByFeedId(bufferStatus.getAccountId(), bufferStatus.getFeedId());
            }
        }
    }

    public void bufferCaching(final long accountId, final long feedId) {

        if (concurrentMapBuffer.isBufferActivityInBuffer(feedId, accountId)) {
            concurrentMapBuffer.put(feedId, accountId, false);
            return;
        }

        bookmarkQueryService.findBookmarkByAccountIdAndFeedId(accountId, feedId)
                            .ifPresentOrElse((bookmark) -> {
                                concurrentMapBuffer.put(feedId, accountId, bookmark.isBookmarked());
                            }, () -> {
                                concurrentMapBuffer.put(feedId, accountId, false);
                            });
    }
}
