package com.ssg.intern.dev.bookmark.adapter.out.persistence;

import com.ssg.intern.dev.bookmark.domain.Bookmark;

import java.util.Optional;


public interface BookmarkRepositoryCustom {

    Optional<Bookmark> findBookmarkByFeedAndAccount(final long accountId, final long feedId);
}
