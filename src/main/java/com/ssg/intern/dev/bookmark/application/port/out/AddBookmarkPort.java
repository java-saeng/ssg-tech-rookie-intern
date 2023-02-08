package com.ssg.intern.dev.bookmark.application.port.out;

import com.ssg.intern.dev.bookmark.domain.Bookmark;

public interface AddBookmarkPort {

    void addBookmarkToFeed(Bookmark bookmark);
}
