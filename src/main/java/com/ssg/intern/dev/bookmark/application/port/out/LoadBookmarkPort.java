package com.ssg.intern.dev.bookmark.application.port.out;

import com.ssg.intern.dev.bookmark.domain.Bookmark;

import java.util.Optional;

public interface LoadBookmarkPort {

    Optional<Bookmark> findBookmarkByFeedAndAccount(final long accountId, final long feedId);
}
