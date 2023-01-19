package com.ssg.intern.dev.domain.feed.dao;

import com.ssg.intern.dev.domain.feed.entity.Feed;
import com.ssg.intern.dev.global.SortingCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.ssg.intern.dev.domain.feed.presentation.model.MyReviewProfileResponse.*;

public interface FeedRepositoryCustom {

    Page<Feed> findAllFeeds(Pageable pageable);

    List<MyReview> findMyReviews(Long accountId, SortingCondition sortingCondition);
}
