package com.ssg.intern.mock.domain.review.dao;

import com.ssg.intern.mock.domain.review.entity.SpecialReview;

public interface SpecialReviewRepositoryCustom {

    SpecialReview findBySpecialReviewId(long specialReviewId);
}
