package com.ssg.intern.dev.bookmark.application.port.in;

import com.ssg.intern.dev.bookmark.domain.ThumbnailProfile;
import com.ssg.intern.dev.global.SortingCondition;

import java.util.List;

public interface GetThumbnailQuery {

    List<ThumbnailProfile> getThumbnails(long accountId, SortingCondition sortingCondition);
}
