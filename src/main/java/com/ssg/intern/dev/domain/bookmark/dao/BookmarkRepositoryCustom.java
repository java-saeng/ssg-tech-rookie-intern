package com.ssg.intern.dev.domain.bookmark.dao;

import com.ssg.intern.dev.domain.mypage.presentation.model.BookmarkProfileResponse;
import com.ssg.intern.dev.global.SortingCondition;

import java.util.List;

public interface BookmarkRepositoryCustom {
    List<BookmarkProfileResponse.Thumbnail> findThumbnails(Long accountId, SortingCondition sortingCondition);
}
