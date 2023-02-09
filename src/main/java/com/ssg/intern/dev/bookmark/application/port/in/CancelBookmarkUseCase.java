package com.ssg.intern.dev.bookmark.application.port.in;

public interface CancelBookmarkUseCase {

    void cancelBookmarkToFeed(final long accountId, final long feedId);
}
