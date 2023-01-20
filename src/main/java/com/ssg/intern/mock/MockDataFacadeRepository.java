package com.ssg.intern.mock;

import com.ssg.intern.mock.domain.hashtag.dao.HashTagRepository;
import com.ssg.intern.mock.domain.hashtag.entity.HashTag;
import com.ssg.intern.mock.domain.product.dao.ProductRepository;
import com.ssg.intern.mock.domain.review.dao.SpecialReviewRepository;
import com.ssg.intern.mock.domain.review.entity.SpecialReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MockDataFacadeRepository {

    private final HashTagRepository hashTagRepository;
    private final ProductRepository productRepository;
    private final SpecialReviewRepository specialReviewRepository;

    public SpecialReview findBySpecialReviewId(long specialReviewId) {
        return specialReviewRepository.findBySpecialReviewId(specialReviewId);
    }
}
