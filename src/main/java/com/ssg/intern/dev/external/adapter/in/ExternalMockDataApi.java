package com.ssg.intern.dev.external.adapter.in;

import com.ssg.intern.dev.common.ExternalMockDataProfile;
import com.ssg.intern.dev.common.FeedSearchingConditionRequest;
import com.ssg.intern.dev.common.HashTagProfile;
import com.ssg.intern.dev.external.adapter.out.MockDataPersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ExternalMockDataApi {

    private final MockDataPersistenceAdapter mockDataPersistenceAdapter;

    public ExternalMockDataProfile findBySpecialReviewId(long specialReviewId) {
        return mockDataPersistenceAdapter.findBySpecialReviewId(specialReviewId);
    }

    public Page<ExternalMockDataProfile> findBySearchingCondition(Pageable pageable,
                                                                  FeedSearchingConditionRequest request) {
        return mockDataPersistenceAdapter.findBySearchingCondition(pageable, request);
    }

    public HashTagProfile loadTop10HashTag() {
        return mockDataPersistenceAdapter.loadTop10HashTag();
    }

    public List<ExternalMockDataProfile> findSpecialReviewByAccountId(long accountId) {
        return mockDataPersistenceAdapter.findSpecialReviewByAccountId(accountId);
    }
}
