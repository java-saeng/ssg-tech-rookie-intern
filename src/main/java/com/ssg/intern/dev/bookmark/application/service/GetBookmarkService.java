package com.ssg.intern.dev.bookmark.application.service;

import com.ssg.intern.dev.bookmark.application.port.in.GetBookmarkQuery;
import com.ssg.intern.dev.bookmark.application.port.out.LoadBookmarkPort;
import com.ssg.intern.dev.bookmark.domain.Bookmark;
import com.ssg.intern.dev.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetBookmarkService implements GetBookmarkQuery {

    private final LoadBookmarkPort loadBookmarkPort;


    @Override
    public boolean isAccountBookmarkFeed(final long accountId, final long feedId) {
        final Optional<Bookmark> savedBookmark = loadBookmarkPort.findBookmarkByFeedAndAccount(accountId, feedId);

        if (savedBookmark.isEmpty()) {
            return false;
        }

        final Bookmark bookmark = savedBookmark.get();

        return bookmark.isBookmarked();
    }
}
