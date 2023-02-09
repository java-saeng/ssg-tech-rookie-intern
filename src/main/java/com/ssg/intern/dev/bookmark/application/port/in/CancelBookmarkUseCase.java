package com.ssg.intern.dev.bookmark.application.port.in;

import com.ssg.intern.dev.bookmark.domain.Bookmark;

public interface CancelBookmarkUseCase {

    void cancelBookmarkToFeed(final long accountId, final long feedId);
}
