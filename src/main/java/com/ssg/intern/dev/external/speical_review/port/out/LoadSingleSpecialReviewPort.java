package com.ssg.intern.dev.external.speical_review.port.out;

import com.ssg.intern.dev.common.ExternalMockDataProfile;

import java.util.List;

public interface LoadSingleSpecialReviewPort {

    ExternalMockDataProfile findBySpecialReviewId(long specialReviewId);

    List<ExternalMockDataProfile> findSpecialReviewByAccountId(long accountId);
}
