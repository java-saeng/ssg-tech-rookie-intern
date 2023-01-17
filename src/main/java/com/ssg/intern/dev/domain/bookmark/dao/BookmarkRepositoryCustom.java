package com.ssg.intern.dev.domain.bookmark.dao;

import com.ssg.intern.dev.domain.bookmark.entity.Bookmark;
import com.ssg.intern.dev.domain.mypage.presentation.model.BookmarkProfileResponse;
import com.ssg.intern.dev.global.SortingCondition;

import java.util.List;
import java.util.Optional;


public interface BookmarkRepositoryCustom {
    List<BookmarkProfileResponse.Thumbnail> findThumbnails(Long accountId, SortingCondition sortingCondition);

    Optional<Bookmark> findBookmarkByFeedAndAccount(final long feedId, final long accountId);
}
