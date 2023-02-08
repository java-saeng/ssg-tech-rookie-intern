package com.ssg.intern.dev.external.speical_review.port.out;

import com.ssg.intern.dev.common.ExternalMockDataProfile;
import com.ssg.intern.dev.common.FeedSearchingConditionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadSearchingSpecialReviewPort {

    Page<ExternalMockDataProfile> findBySearchingCondition(Pageable pageable, FeedSearchingConditionRequest request);
}
