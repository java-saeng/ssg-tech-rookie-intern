package com.ssg.intern.mock.domain.review.dao;

import com.ssg.intern.dev.domain.feed.presentation.model.FeedSearchingConditionRequest;
import com.ssg.intern.mock.domain.review.entity.SpecialReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpecialReviewRepositoryCustom {

    SpecialReview findBySpecialReviewId(long specialReviewId);

    Page<SpecialReview> findBySearchingCondition(Pageable pageable, FeedSearchingConditionRequest condition);
}
