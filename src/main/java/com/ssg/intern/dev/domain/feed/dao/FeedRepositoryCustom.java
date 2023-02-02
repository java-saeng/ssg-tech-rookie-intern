package com.ssg.intern.dev.domain.feed.dao;

import com.ssg.intern.dev.global.SortingCondition;

import java.util.List;

import static com.ssg.intern.dev.domain.feed.presentation.model.MyReviewProfileResponse.*;

public interface FeedRepositoryCustom {

    List<MyReview> findMyReviews(Long accountId, SortingCondition sortingCondition);
}
