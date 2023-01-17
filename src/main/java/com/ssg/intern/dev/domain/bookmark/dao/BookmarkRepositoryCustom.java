package com.ssg.intern.dev.domain.bookmark.dao;

import com.ssg.intern.dev.domain.bookmark.entity.Bookmark;

import java.util.Optional;

public interface BookmarkRepositoryCustom {

    Optional<Bookmark> findBookmarkByFeedAndAccount(final long feedId, final long accountId);
}
