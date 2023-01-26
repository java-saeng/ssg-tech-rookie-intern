package com.ssg.intern.dev.domain.bookmark.service;

import com.ssg.intern.dev.domain.bookmark.dao.BookmarkRepository;
import com.ssg.intern.dev.domain.bookmark.entity.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkQueryService {

    private final BookmarkRepository bookmarkRepository;

    public boolean isAccountBookmarkFeed(long accountId, long feedId) {

        final Optional<Bookmark> savedBookmark = bookmarkRepository.findBookmarkByFeedAndAccount(feedId, accountId);

        if (savedBookmark.isEmpty()) {
            return false;
        }

        final Bookmark bookmark = savedBookmark.get();

        return bookmark.isBookmarked();
    }
}
