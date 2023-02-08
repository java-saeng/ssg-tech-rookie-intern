package com.ssg.intern.dev.external.speical_review.adapter.out.persistence;

import com.ssg.intern.dev.common.FeedSearchingConditionRequest;
import com.ssg.intern.dev.external.speical_review.domain.SpecialReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpecialReviewRepositoryCustom {

    Page<SpecialReview> findBySearchingCondition(Pageable pageable, FeedSearchingConditionRequest condition);

    SpecialReview findBySpecialReviewId(long specialReviewId);
}
