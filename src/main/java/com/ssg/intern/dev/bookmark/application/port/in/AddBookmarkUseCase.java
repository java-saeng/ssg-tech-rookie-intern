package com.ssg.intern.dev.bookmark.application.port.in;

public interface AddBookmarkUseCase {

    void addBookmarkToFeed(final long accountId, final long feedId);
}
