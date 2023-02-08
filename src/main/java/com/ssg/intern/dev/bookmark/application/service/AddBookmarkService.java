package com.ssg.intern.dev.bookmark.application.service;

import com.ssg.intern.dev.bookmark.application.port.in.AddBookmarkUseCase;
import com.ssg.intern.dev.bookmark.application.port.out.AddBookmarkPort;
import com.ssg.intern.dev.bookmark.application.port.out.LoadBookmarkPort;
import com.ssg.intern.dev.bookmark.domain.Bookmark;
import com.ssg.intern.dev.common.UseCase;
import com.ssg.intern.dev.feed.application.port.out.LoadSingleFeedPort;
import com.ssg.intern.dev.feed.domain.Feed;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
@Transactional
public class AddBookmarkService implements AddBookmarkUseCase {

    private final LoadBookmarkPort loadBookmarkPort;
    private final AddBookmarkPort addBookmarkPort;
    private final LoadSingleFeedPort loadSingleFeedPort;

    @Override
    public void addBookmarkToFeed(final long accountId, final long feedId) {

        loadBookmarkPort.findBookmarkByFeedAndAccount(accountId, feedId)
                        .ifPresentOrElse(
                                (bookmark -> {

                                    final Feed savedFeed = bookmark.getFeed();

                                    if (!bookmark.isBookmarked()) {
                                        savedFeed.increaseBookmark();
                                        bookmark.addBookmark();
                                    }

                                }),

                                () -> {
                                    final Feed savedFeed = loadSingleFeedPort.findById(feedId)
                                                                             .orElseThrow(EntityNotFoundException::new);

                                    final Bookmark bookmark = Bookmark.of(accountId, savedFeed, true);

                                    savedFeed.increaseBookmark();

                                    addBookmarkPort.addBookmarkToFeed(bookmark);
                                }
                        );
    }
}
