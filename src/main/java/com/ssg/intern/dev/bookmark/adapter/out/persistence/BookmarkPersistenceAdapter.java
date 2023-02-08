package com.ssg.intern.dev.bookmark.adapter.out.persistence;

import com.ssg.intern.dev.bookmark.application.port.out.AddBookmarkPort;
import com.ssg.intern.dev.bookmark.application.port.out.LoadBookmarkPort;
import com.ssg.intern.dev.bookmark.application.port.out.LoadMyBookmarkPort;
import com.ssg.intern.dev.bookmark.domain.Bookmark;
import com.ssg.intern.dev.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class BookmarkPersistenceAdapter implements LoadBookmarkPort, AddBookmarkPort, LoadMyBookmarkPort {

    private final BookmarkRepository bookmarkRepository;

    @Override
    public Optional<Bookmark> findBookmarkByFeedAndAccount(final long accountId, final long feedId) {
        return bookmarkRepository.findBookmarkByFeedAndAccount(accountId, feedId);
    }

    @Override
    public void addBookmarkToFeed(final Bookmark bookmark) {
        bookmarkRepository.save(bookmark);
    }

    @Override
    public List<Bookmark> findMyBookmarkFeeds(final long accountId) {

        return bookmarkRepository.findBookmarksByAccountId(accountId);
    }
}
