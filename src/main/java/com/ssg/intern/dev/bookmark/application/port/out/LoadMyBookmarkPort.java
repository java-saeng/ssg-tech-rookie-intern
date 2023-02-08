package com.ssg.intern.dev.bookmark.application.port.out;

import com.ssg.intern.dev.bookmark.domain.Bookmark;

import java.util.List;

public interface LoadMyBookmarkPort {

    List<Bookmark> findMyBookmarkFeeds(long accountId);
}
