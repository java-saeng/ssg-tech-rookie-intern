package com.ssg.intern.dev.bookmark.application.port.in;

public interface GetBookmarkQuery {

    boolean isAccountBookmarkFeed(final long accountId, final long feedId);
}
